package ru.anyfon.web.auth.data

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.web.auth.domain.user.User
import ru.anyfon.web.auth.domain.user.UserRepository

class R2dbcUserRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : UserRepository {
    override suspend fun add(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun findByEmail(email: Email): User? {
        val query = Query.query(
            Criteria.where("email").`is`(email.toString())
        )

        return entityTemplate
            .selectOne(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.awaitSingle()
    }

    override suspend fun findByPhone(number: PhoneNumber.External): User? {
        val query = Query.query(
            Criteria.where("phone_number").`is`(number.toString())
        )

        return entityTemplate
            .selectOne(query, UserEntity::class.java)
            .map {
                it.toUser()
            }.awaitSingle()
    }

}
