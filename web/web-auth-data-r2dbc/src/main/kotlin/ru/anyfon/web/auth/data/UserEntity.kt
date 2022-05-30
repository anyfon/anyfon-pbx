package ru.anyfon.web.auth.data

import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.NamePart
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.web.auth.domain.user.service.UserData
import ru.anyfon.web.auth.domain.user.UserRole

@Table("usr")
class UserEntity(
    private val id: String? = null,
    private val firstName: String = "",
    private val middleName: String? = null,
    private val lastName: String = "",
    private val email: String = "",
    private val phoneNumber: String = "",
    private val role: String = "",
    private val password: String = "",
    private val enabled: Boolean = false
) {
    fun toUser(): UserData = id?.let {
        UserData(
            UserData.ID(id),
            NamePart(firstName),
            ConvertUtils.tryOrNull {
                middleName?.let {
                    NamePart(middleName)
                } },
            NamePart(lastName),
            UserRole(role),
            Email(email),
            PhoneNumber.External(phoneNumber),
            password,
            enabled
        )
    } ?: throw IllegalStateException(
        "Cannot be used for a new entity with an id value like [ null ]"
    )
}
