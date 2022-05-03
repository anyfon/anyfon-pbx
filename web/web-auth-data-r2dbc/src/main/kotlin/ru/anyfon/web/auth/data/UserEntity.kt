package ru.anyfon.web.auth.data

import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.NamePart
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.web.auth.domain.user.User
import ru.anyfon.web.auth.domain.user.UserRole

@Table("usr")
class UserEntity(
    private val id: String = "",
    private val firstName: String = "",
    private val middleName: String = "",
    private val lastName: String = "",
    private val email: String = "",
    private val phoneNumber: String = "",
    private val role: String = "",
    private val password: String = "",
    private val enabled: Boolean = false
) {
    fun toUser(): User =

        User(
            User.ID(id),
            NamePart(firstName),
            ConvertUtils.tryOrNull { NamePart(middleName) },
            NamePart(lastName),
            UserRole(role),
            Email(email),
            PhoneNumber.External(phoneNumber),
            password,
            enabled
        )
}
