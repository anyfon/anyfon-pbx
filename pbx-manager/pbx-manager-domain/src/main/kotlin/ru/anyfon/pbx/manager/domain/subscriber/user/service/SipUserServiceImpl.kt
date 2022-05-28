package ru.anyfon.pbx.manager.domain.subscriber.user.service

import ru.anyfon.pbx.common.domain.DomainException
import ru.anyfon.pbx.common.domain.service.MessageSource
import ru.anyfon.pbx.common.domain.service.PasswordEncoder
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUser
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUserRequest

class SipUserServiceImpl(
    private val subscriberRepository: SubscriberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val messageSource: MessageSource? = null
) : SipUserService {
    override suspend fun findByFilter(request: SipUserRequest.Filter): List<SipUser> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(idRow: String): SipUser {
        TODO("Not yet implemented")
    }

    override suspend fun create(request: SipUserRequest.Data): Subscriber.Uuid {
        TODO("Not yet implemented")
    }

    override suspend fun delete(uuidRaw: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(uuidsRaw: Iterable<String>) {
        TODO("Not yet implemented")
    }

    override suspend fun enable(uuidRaw: String) {

        val uuid = validParam("uuid", uuidRaw) { Subscriber.Uuid(uuidRaw) }

        subscriberRepository.findById(uuid)?.let {
            subscriberRepository.save(Subscriber(it.uuid, it.username, true))
            return@let
        } ?: throw DomainException.EntityNotFound(Subscriber::class, uuidRaw)

    }

    override suspend fun disable(uuidRaw: String) {

        val uuid = validParam("uuid", uuidRaw) { Subscriber.Uuid(uuidRaw) }

        subscriberRepository.findById(uuid)?.let {
            subscriberRepository.save(Subscriber(it.uuid, it.username, false))
            return@let
        } ?: throw DomainException.EntityNotFound(Subscriber::class, uuidRaw)

    }

    override suspend fun updatePassword(request: SipUserRequest.UpdatePassword) {

        val req = validRequest(request, messageSource)

        subscriberRepository.findById(req.subscriberUuid)?.let {
            subscriberRepository.save(it, passwordEncoder.encode(req.password.toString()))
            return@let
        } ?: throw DomainException.EntityNotFound(Subscriber::class, req.subscriberUuid.toString())
    }

    override suspend fun update(request: SipUserRequest.Data) {
        TODO("Not yet implemented")
    }
}
