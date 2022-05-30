package ru.anyfon.web.auth.data

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.web.auth.domain.user.service.UserData
import ru.anyfon.web.auth.domain.user.UserRepository

class R2dbcUserRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : UserRepository {
    override suspend fun add(user: UserData) {
        val entity = UserEntity(
            null,
            user.firstName.toString(),
            user.middleName?.toString(),
            user.lastName.toString(),
            user.email.toString(),
            user.phoneNumber.toString(),
            user.role.toString(),
            user.password,
            user.enabled
        )
        entityTemplate.insert(entity).awaitSingle()
    }

    override suspend fun findById(id: UserData.ID): UserData? {
        val query = Query.query(
            Criteria.where("id").`is`(id.toString())
        )

        return entityTemplate
            .selectOne(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.awaitSingle()
    }

    override suspend fun findByEmail(email: Email): UserData? {
        val query = Query.query(
            Criteria.where("email").`is`(email.toString())
        )

        return entityTemplate
            .selectOne(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.awaitSingle()
    }

    override suspend fun findByPhone(number: PhoneNumber.External): UserData? {
        val query = Query.query(
            Criteria.where("phone_number").`is`(number.toString())
        )

        return entityTemplate
            .selectOne(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.awaitSingle()
    }

    override suspend fun findAll(limit: Int, offset: Long): List<UserData> {

        val query = Query.empty().sort(Sort.by("lastName")).limit(limit).offset(offset)

        return entityTemplate
            .select(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.collectList().awaitSingle()
    }


}
