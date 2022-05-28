package ru.anyfon.pbx.resource.subscriber

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.DomainException
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.service.SubscriberService
import ru.anyfon.pbx.resource.user.UserHandler

@Component
class SubscriberHandlerImpl(
    private val subscriberService: SubscriberService
) : SubscriberHandler {
    override suspend fun fetchList(serverRequest: ServerRequest): ServerResponse {

        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        if (user.authorities.any { it.authority == "ROOT" || it.authority == "ADMIN"}) {
            val list = subscriberService.findByFilters()
            return ServerResponse.ok().bodyValueAndAwait(list)
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")
    }


    override suspend fun fetchOne(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        val subscriberUuid = ConvertUtils.tryOrNull {
            Subscriber.Uuid(serverRequest.pathVariable(UserHandler.ID_PARAM))
        } ?: return ServerResponse.notFound().buildAndAwait()

        if (user.authorities.any { it.authority == "ROOT" || it.authority == "ADMIN" }) {
            val subscriberResponse = try {
                subscriberService.findById(subscriberUuid)
            } catch (e: DomainException) {
                return when (e) {
                    is DomainException.EntityNotFound -> ServerResponse.notFound().buildAndAwait()
                    else -> ServerResponse.status(500).bodyValue("Server error").awaitSingle()
                }
            }

            return ServerResponse.ok().bodyValue(subscriberResponse).awaitSingle()
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")
    }

    override suspend fun add(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }
}
