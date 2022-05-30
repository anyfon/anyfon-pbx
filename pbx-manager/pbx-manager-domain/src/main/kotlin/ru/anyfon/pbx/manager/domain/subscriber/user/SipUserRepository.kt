package ru.anyfon.pbx.manager.domain.subscriber.user

import ru.anyfon.pbx.common.domain.service.request.Request
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

interface SipUserRepository {

    suspend fun findById(uuid: Request.Valid<Subscriber.Uuid>): SipUser?

    suspend fun findByFilters(request: Request.Valid<SipUserRequest>): List<SipUser>

    suspend fun create(request: Request.Valid<SipUserRequest.Data>): Subscriber.Uuid

    suspend fun update(request: Request.Valid<SipUserRequest.Data>)

    suspend fun deleteById(uuid: Request.Valid<Subscriber.Uuid>)

    suspend fun deleteAllById(uuids: Request.Valid<Iterable<Subscriber.Uuid>>)
}
