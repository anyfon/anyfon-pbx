package ru.anyfon.pbx.manager.domain.subscriber.user

import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.common.util.StringUtils
import ru.anyfon.pbx.common.domain.service.*
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.FullName
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

interface SipUserRequest : Request {

    class Filter(
        subscribersUuid: List<String> = emptyList(),
        emails: List<String> = emptyList(),
        names: List<String> = emptyList(),
        tenantsId: List<String> = emptyList(),
        extensions: List<String> = emptyList(),
        usernames: List<String> = emptyList(),
        nameEmailExtensionContains: String? = null,
        val enabled: Boolean? = null,
        val registered: Boolean? = null
    ) : SipUserRequest {

        val subscribersUuid = subscribersUuid.mapNotNull { ConvertUtils.tryOrNull { Subscriber.Uuid(it) } }

        val emails = emails.mapNotNull { ConvertUtils.tryOrNull { Email(it) } }

        val names = names.mapNotNull { ConvertUtils.tryOrNull { FullName(it) } }

        val tenantsId = tenantsId.mapNotNull { ConvertUtils.tryOrNull { Tenant.ID(it) } }

        val extensions = extensions.mapNotNull { ConvertUtils.tryOrNull { PhoneNumber.Extension(it) } }

        val usernames = usernames.mapNotNull { ConvertUtils.tryOrNull { Subscriber.Username(it) } }

        val nameEmailExtensionContains = nameEmailExtensionContains
            ?.replace(StringUtils.ALL_LETTER_INT_DOG, "")

        override fun validate(messageSource: MessageSource?): ValidatedRequest = ValidatedRequest.VALID

    }

    class Data(
        subscriberUuid: String? = null,
        internalNumber: String? = null,
        name: String? = null,
        email: String? = null,
        description: String? = null
    ) : SipUserRequest {

        private val errorMessages: MutableMap<FieldID, MessageParams> = mutableMapOf()

        val subscriberUuid = ValidatedField("subscriberUuid", subscriberUuid, errorMessages) {
            FieldMapper.Result("Must be UUID") {
                it?.let { Subscriber.Uuid(it) }  ?: Subscriber.Uuid.EMPTY
            }
        }.value

        val internalNumber = ValidatedField("internalNumber", internalNumber, errorMessages) {
            FieldMapper.Result("Must be INT from 100 to 999999") {
                PhoneNumber.Internal(it)
            }
        }.value

        val name = ValidatedField("name", name, errorMessages) {
            FieldMapper.Result("Must be Full Name") {
                it?.let { FullName(it) }
            }
        }.value

        val email = ValidatedField("email", email, errorMessages) {
            FieldMapper.Result("Must be e-mail") {
                it?.let { Email(it) }
            }
        }.value

        val description = description?.let {
            it.replace(StringUtils.ALLOWED_HTML_SYMBOLS_PATTERN.toRegex(),"")
        }


        override fun validate(messageSource: MessageSource?): ValidatedRequest =
            ValidatedRequest.compose(errorMessages, messageSource)
    }

    class UpdatePassword(
        subscriberUuid: String? = null,
        password: String? = null
    ) : SipUserRequest {
        private val errorMessages: MutableMap<FieldID, MessageParams> = mutableMapOf()

        val subscriberUuid = ValidatedField("subscriberUuid", subscriberUuid, errorMessages) {
            FieldMapper.Result("Must be UUID") {
                Subscriber.Uuid(it)
            }
        }.value!!

        val password = ValidatedField("password", password, errorMessages) {
            FieldMapper.Result("Must be UUID") {
                Subscriber.Password(it)
            }
        }.value!!

        override fun validate(messageSource: MessageSource?): ValidatedRequest {
            TODO("Not yet implemented")
        }

    }
}
