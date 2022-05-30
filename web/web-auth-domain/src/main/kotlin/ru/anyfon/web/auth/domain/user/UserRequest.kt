package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.service.message.MessageParams
import ru.anyfon.pbx.common.domain.service.message.MessageSource
import ru.anyfon.pbx.common.domain.service.request.*
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.NamePart
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.User

interface UserRequest : Request {

    class Data(
        uuid: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null,
        email: String? = null,
        phoneNumber: String? = null,
        role: String? = null,
        enabled: String? = null,
        password: String? = null
    ) : UserRequest {

        private val errorMessages: MutableMap<FieldID, MessageParams> = mutableMapOf()

        val subscriberUuid = ValidatedField("uuid", uuid, errorMessages) {
            FieldMapper.Result("Must be UUID") {
                it?.let { User.Uuid(it) } ?: User.Uuid.Empty
            }
        }.value!!

        val firstName = ValidatedField("firstName", firstName, errorMessages) {
            FieldMapper.Result("Allow [А-Яа-я\\w\\s]{2,25} ") {
                NamePart(it)
            }
        }.value!!

        val middleName = ValidatedField("middleName", middleName, errorMessages) {
            FieldMapper.Result("Allow [А-Яа-я\\w\\s]{2,25} not required") {
                it?.let { NamePart(it) }
            }
        }.value

        val lastName = ValidatedField("lastName", lastName, errorMessages) {
            FieldMapper.Result("Allow [А-Яа-я\\w\\s]{2,25} ") {
                NamePart(it)
            }
        }.value!!

        val email = ValidatedField("email", email, errorMessages) {
            FieldMapper.Result("Must be e-mail") {
                Email(it)
            }
        }.value!!

        val phoneNumber = ValidatedField("phoneNumber", phoneNumber, errorMessages) {
            FieldMapper.Result("Must be phone number") {
                PhoneNumber.External(it)
            }
        }.value!!

        val role = ValidatedField("role", role, errorMessages) {
            FieldMapper.Result("Must be User.Role") {
                User.Role(it)
            }
        }.value!!

        val enabled = enabled?.let { it == "true" }

        val password = ValidatedField("password", password, errorMessages) {
            FieldMapper.Result("Allow ") {
                it?.let { User.Password(it) }
            }
        }.value

        override fun validate(messageSource: MessageSource?): ValidatedRequest =
            ValidatedRequest.compose(errorMessages, messageSource)


    }
}
