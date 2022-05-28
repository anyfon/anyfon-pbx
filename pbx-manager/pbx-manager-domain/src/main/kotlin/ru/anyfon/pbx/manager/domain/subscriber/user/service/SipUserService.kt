package ru.anyfon.pbx.manager.domain.subscriber.user.service

import ru.anyfon.pbx.common.domain.service.Service
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUser
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUserRequest

interface SipUserService : Service {

    suspend fun findByFilter(request: SipUserRequest.Filter): List<SipUser>

    suspend fun findById(uuidRaw: String): SipUser

    suspend fun create(request: SipUserRequest.Data): Subscriber.Uuid

    suspend fun delete(uuidRaw: String)

    suspend fun deleteAll(uuidsRaw: Iterable<String>)

    suspend fun enable(uuidRaw: String)

    suspend fun disable(uuidRaw: String)

    suspend fun updatePassword(request: SipUserRequest.UpdatePassword)

    suspend fun update(request: SipUserRequest.Data)

}
