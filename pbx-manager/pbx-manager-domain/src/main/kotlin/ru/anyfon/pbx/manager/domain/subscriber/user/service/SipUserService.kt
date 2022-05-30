package ru.anyfon.pbx.manager.domain.subscriber.user.service

import ru.anyfon.pbx.common.domain.service.Service
import ru.anyfon.pbx.common.domain.service.request.RequestContext
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUserRequest

interface SipUserService : Service {

    suspend fun findByFilter(
        request: SipUserRequest.Filter,
        context: RequestContext
    ): List<SipUserDetails>

    suspend fun findById(
        uuidRaw: String,
        context: RequestContext
    ): SipUserDetails

    suspend fun create(
        request: SipUserRequest.Data,
        context: RequestContext
    ): Subscriber.Uuid

    suspend fun delete(
        uuidRaw: String,
        context: RequestContext
    )

    suspend fun deleteAll(
        uuidsRaw: Iterable<String>,
        context: RequestContext
    )

    suspend fun enable(
        uuidRaw: String,
        context: RequestContext
    )

    suspend fun disable(
        uuidRaw: String,
        context: RequestContext
    )

    suspend fun updatePassword(
        request: SipUserRequest.UpdatePassword,
        context: RequestContext
    )

    suspend fun update(
        request: SipUserRequest.Data,
        context: RequestContext
    )

}
