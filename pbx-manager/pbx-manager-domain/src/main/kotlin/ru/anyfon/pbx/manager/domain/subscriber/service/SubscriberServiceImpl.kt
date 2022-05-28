package ru.anyfon.pbx.manager.domain.subscriber.service

import ru.anyfon.pbx.common.domain.DomainException
import ru.anyfon.pbx.common.domain.service.PasswordEncoder
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository

class SubscriberServiceImpl(
    private val subscriberRepository: SubscriberRepository,
    private val passwordEncoder: PasswordEncoder
) : SubscriberService {

    override suspend fun findByFilters(

    ): List<SubscriberDetails> =
        subscriberRepository.findByFilters().map {
            //TODO rewrite [ registered ] arg
            SubscriberDetails(it, true)
        }

    override suspend fun findById(uuid: Subscriber.Uuid): SubscriberDetails =
        subscriberRepository.findById(uuid)?.let {
            //TODO rewrite [ registered ] arg
            SubscriberDetails(it, true)
        } ?: throw DomainException.EntityNotFound(Subscriber::class, uuid.toString())


    override suspend fun create(
        subscriber: Subscriber,
        password: Subscriber.Password?
    ): Subscriber.Uuid = subscriberRepository.save(subscriber)



    override suspend fun delete(
        uuid: Subscriber.Uuid
    ) = subscriberRepository.findById(uuid)?.let {
        subscriberRepository.remove(it.uuid)
    } ?: throw DomainException.EntityNotFound(Subscriber::class, uuid.toString())
}
