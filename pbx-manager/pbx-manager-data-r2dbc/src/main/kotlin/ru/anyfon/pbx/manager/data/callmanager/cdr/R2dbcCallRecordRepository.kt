package ru.anyfon.pbx.manager.data.callmanager.cdr

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.manager.domain.callmanager.CallDirection
import ru.anyfon.pbx.manager.domain.callmanager.CallID
import ru.anyfon.pbx.manager.domain.callmanager.cdr.*

open class R2dbcCallRecordRepository(
    private val entityTemplate: R2dbcEntityTemplate,
    private val objectMapper: ObjectMapper
) : CallRecordRepository {

    override suspend fun findByFilters(params: CallRecordParams.Filter): List<CallRecord> {

        val query = CallRecordSqlQuery(params)

        return entityTemplate.databaseClient.sql(query.getQuery()).map { row ->
            CallRecordEntity.ofRow(row)
        }.all().collectList().flatMap { records ->
            val operationsQuery = Query.query(Criteria.where("call_id").`in`(records.map { it.callId }))

            retrieveOperations(operationsQuery).map {
                CallRecordsMapper(it.t1, it.t2, objectMapper).map(records)
            }

        }.awaitSingle()
    }

    override suspend fun findById(callID: CallID): CallRecord? {

        val recordQuery = Query.query(Criteria.where("call_id").`is`(callID.toString()))

        return entityTemplate.selectOne(recordQuery, CallRecordEntity::class.java).flatMap { record ->

            val operationsQuery = Query.query(Criteria.where("call_id").`is`(record.callId ?: 0L))

            retrieveOperations(operationsQuery).map {
                CallRecordsMapper(it.t1, it.t2, objectMapper).map(record)
            }

        }.awaitSingle()
    }

    private fun retrieveOperations(query: Query): Mono<Tuple2<List<CallOperationEntity>, List<CommandReplyEntity>>> {
        return entityTemplate.select(query, CallOperationEntity::class.java)
            .collectList().flatMap { operations ->
                val repliesQuery = Query.query(Criteria.where("operation_id").`in`(operations.map { it.id }))
                Mono.zip(
                    Mono.just(operations),
                    entityTemplate.select(repliesQuery, CommandReplyEntity::class.java).collectList()
                )
            }
    }


    @Transactional
    override suspend fun add(params: CallRecordParams.Data) {
        val record = CallRecordEntity(
            null,
            params.timestamp,
            CallDirection.of(params.fromNumber, params.toNumber).toString(),
            params.fromNumber.toString(),
            params.toNumber.toString(),
            params.tenantId.toString(),
            params.subscriberUuid.toString(),
            params.sourceIpV4?.toString(),
            params.sourceIpV6?.toString()
        )

        entityTemplate.insert(record).flatMap {
            createOperations(it.callId ?: 0L, params.apps).collectList()
        }.awaitSingle()
    }

    private fun createOperations(callID: Long, apps: Iterable<CallApp>): Flux<CallOperationEntity> {

        var lastId = 0L

        return Flux.fromIterable(apps).flatMap {

            val appName = it.name.toString()
            var isRoot = true

            Flux.fromIterable(it.operations).flatMap { op ->
                val extra = ConvertUtils.tryOrNull {
                    objectMapper.writeValueAsString(op.extra)
                } ?: ""

                val callOperation = CallOperationEntity(
                    0,
                    callID,
                    op.timestamp,
                    isRoot,
                    appName,
                    op.commandName.toString(),
                    op.dest,
                    lastId,
                    extra
                )

                return@flatMap entityTemplate.insert(callOperation).flatMap { new ->
                    lastId = new.id ?: 0L
                    isRoot = false
                    createReplies(lastId, op.replies).collectList().map { new }
                }
            }
        }
    }

    private fun createReplies(
        operationId: Long,
        replies: Iterable<CallOperation.Reply>
    ): Flux<CommandReplyEntity> =

        Flux.fromIterable(replies).flatMap {

            val extra = ConvertUtils.tryOrNull {
                objectMapper.writeValueAsString(it.extra)
            } ?: ""

            val reply = CommandReplyEntity(
                null,
                operationId,
                it.timestamp,
                it.status.toString(),
                it.response,
                extra
            )

            entityTemplate.insert(reply)
        }
}
