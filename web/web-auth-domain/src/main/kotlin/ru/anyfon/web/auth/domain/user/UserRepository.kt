package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.User
import ru.anyfon.web.auth.domain.user.service.UserData

interface UserRepository {
    suspend fun add(user: UserData)

    suspend fun findById(id: User.Uuid) : UserData?
    suspend fun findByEmail(email: Email) : UserData?

    suspend fun findByPhone(number: PhoneNumber.External) : UserData?
    suspend fun findAll(limit: Int = 50, offset: Long = 0) : List<UserData>

}
