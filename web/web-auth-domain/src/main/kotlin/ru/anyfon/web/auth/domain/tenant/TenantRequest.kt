package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.service.message.MessageParams
import ru.anyfon.pbx.common.domain.service.message.MessageSource
import ru.anyfon.pbx.common.domain.service.request.*
import ru.anyfon.pbx.common.domain.type.Tenant

interface TenantRequest : Request {

    class Create(
        uniqueName: String? = null
    ) : TenantRequest {

        private val errorMessages: MutableMap<FieldID, MessageParams> = mutableMapOf()

        val uniqueName = ValidatedField("name", uniqueName, errorMessages) {
            FieldMapper.Result("Allowed | a-z | _ | - | min 5 | max 20") {
                it?.let { Tenant.UniqueName(it) }
            }
        }.value


        override fun validate(messageSource: MessageSource?): ValidatedRequest =
            ValidatedRequest.compose(errorMessages, messageSource)
    }

    class Update(
        enabled: String? = null
    ) : TenantRequest {
        override fun validate(messageSource: MessageSource?): ValidatedRequest {
            TODO("Not yet implemented")
        }

    }
}
