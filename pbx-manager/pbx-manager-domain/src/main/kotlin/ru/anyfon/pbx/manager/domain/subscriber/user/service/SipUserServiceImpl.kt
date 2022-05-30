package ru.anyfon.pbx.manager.domain.subscriber.user.service

import ru.anyfon.pbx.common.domain.DomainException
import ru.anyfon.pbx.common.domain.service.PasswordEncoder
import ru.anyfon.pbx.common.domain.service.request.FieldMapper
import ru.anyfon.pbx.common.domain.service.request.Permission
import ru.anyfon.pbx.common.domain.service.request.Request
import ru.anyfon.pbx.common.domain.service.request.RequestContext
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUserRepository
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUserRequest

class SipUserServiceImpl(
    private val subscriberRepository: SubscriberRepository,
    private val sipUserRepository: SipUserRepository,
    private val passwordEncoder: PasswordEncoder
) : SipUserService {
    override suspend fun findByFilter(
        request: SipUserRequest.Filter,
        context: RequestContext
    ): List<SipUserDetails> {
        checkPermission(context, SipUserDetails::class, Permission.ReadList)

        val validRequest = validRequest(request, context)

        val users = sipUserRepository.findByFilters(validRequest)

        val subscribers = subscriberRepository
            .findAllById(Request.Valid(users.map { it.subscriberUuid }))
            .associateBy { it.uuid }

        return users.map {
            //TODO rewrite arg [ registered = true ]
            SipUserDetails(it, subscribers[it.subscriberUuid]!!, context.tenant.prefixNumber, true)
        }
    }

    override suspend fun findById(uuidRaw: String, context: RequestContext): SipUserDetails {
        val validUuid = validParam("uuid", uuidRaw, context.requestId) { Subscriber.Uuid(uuidRaw) }

        checkPermission(
            context,
            SipUserDetails::class,
            Permission.ReadEntity(validUuid.value)
        )

        val request = SipUserRequest.Filter(listOf(validUuid.toString()))

        return findByFilter(request, context).firstOrNull()
            ?: throw DomainException.EntityNotFound(Subscriber::class, validUuid.value, context.requestId)
    }

    override suspend fun create(request: SipUserRequest.Data, context: RequestContext): Subscriber.Uuid {
        checkPermission(context, SipUserDetails::class, Permission.Create)

        val validRequest = validRequest(request, context)


        if (!validRequest.value.subscriberUuid.isEmpty())
            throw DomainException.BadRequestParam(
                "uuid",
                validRequest.value.subscriberUuid,
                context.requestId
            )

        val uuid = Subscriber.Username(
            validRequest.value.email ?: context.user.email,
            context.tenant.uniqueName
        ).let { subscriberRepository.add(Request.Valid(it)) }

        return sipUserRepository.create(
            Request.Valid(validRequest.value.withUuid(uuid))
        )

    }

    override suspend fun delete(uuidRaw: String, context: RequestContext) {
        val validUuid = validParam("uuid", uuidRaw, context.requestId) { Subscriber.Uuid(uuidRaw) }

        checkPermission(context, SipUserDetails::class, Permission.Delete(validUuid.value))

        deleteAll(listOf(validUuid.toString()), context)
    }

    override suspend fun deleteAll(uuidsRaw: Iterable<String>, context: RequestContext) {
        val validUuids = validIterableParam("uuid", uuidsRaw, context) {
            FieldMapper.Result("Must be uuid") {
                Subscriber.Uuid(it)
            }
        }

        checkPermissions(
            context,
            SipUserDetails::class,
            validUuids.value.map { Permission.Delete(it) }
        )

        subscriberRepository.deleteAllById(validUuids)
        sipUserRepository.deleteAllById(validUuids)
    }

    override suspend fun enable(
        uuidRaw: String,
        context: RequestContext
    ) = setStatus(uuidRaw, context, true)

    override suspend fun disable(
        uuidRaw: String,
        context: RequestContext
    ) = setStatus(uuidRaw, context, false)

    private suspend fun setStatus(
        uuidRaw: String,
        context: RequestContext,
        status: Boolean
    ) {
        val validUuid = validParam("uuid", uuidRaw, context.requestId) {
            Subscriber.Uuid(uuidRaw)
        }

        checkPermission(
            context,
            SipUserDetails::class,
            Permission.Update(validUuid.value)
        )

        subscriberRepository.findById(validUuid)?.let {
            subscriberRepository.update(Request.Valid(it.uuid), null, status)
            return@let
        } ?: throw DomainException.EntityNotFound(Subscriber::class, validUuid.value)
    }

    override suspend fun updatePassword(request: SipUserRequest.UpdatePassword, context: RequestContext) {
        val validRequest = validRequest(request, context)
        val validUuid = Request.Valid(validRequest.value.subscriberUuid)

        checkPermission(
            context,
            SipUserDetails::class,
            Permission.Update(validUuid.value)
        )


        subscriberRepository.findById(validUuid)?.let {
            val passwordHash = passwordEncoder.encode(validRequest.value.password.toString())
            subscriberRepository.update(
                Request.Valid(it.uuid),
                null,
                null,
                passwordHash
            )
            return@let
        } ?: throw DomainException.EntityNotFound(Subscriber::class, validUuid.value)
    }

    override suspend fun update(request: SipUserRequest.Data, context: RequestContext) {
        val validRequest = validRequest(request, context)

        checkPermission(
            context,
            SipUserDetails::class,
            Permission.Update(validRequest.value.subscriberUuid)
        )

        sipUserRepository.update(validRequest)
    }
}
