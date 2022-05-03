package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber

interface UserRepository {
    suspend fun add(user: User)

    suspend fun findByEmail(email: Email) : User?

    suspend fun findByPhone(number: PhoneNumber.External) : User?

}
