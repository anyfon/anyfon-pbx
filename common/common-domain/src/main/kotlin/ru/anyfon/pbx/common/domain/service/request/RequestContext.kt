package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.RequestId
import ru.anyfon.pbx.common.domain.service.message.MessageSource
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.common.domain.type.User
import java.util.*
import kotlin.reflect.KClass

interface RequestContext {

    val requestId: RequestId

    val user: User

    val tenant: Tenant

    val locale: Locale

    fun domainPermission(domainClass: KClass<out DomainEntity>) : DomainPermission

    fun messageSource() : MessageSource? = messageSource(locale)

    fun messageSource(locale: Locale) : MessageSource?
}
