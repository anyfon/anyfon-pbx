package ru.anyfon.pbx.resource.user

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.web.auth.domain.user.service.UserData
import ru.anyfon.web.auth.domain.user.UserRepository

class UserHandlerImpl(
    private val userRepository: UserRepository
) : UserHandler {
    override suspend fun fetchList(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        if (user.authorities.any { it.authority == "ROOT" }) {
            val list = userRepository.findAll().map { it.hidePassword() }
            return ServerResponse.ok().bodyValueAndAwait(list)
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")
    }

    override suspend fun fetchOne(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        val userId = ConvertUtils.tryOrNull { UserData.ID(serverRequest.pathVariable(UserHandler.ID_PARAM)) }
            ?: return ServerResponse.notFound().buildAndAwait()

        if (user.authorities.any { it.authority == "ROOT" || it.authority == "ADMIN" } || userId == user.id) {
            val userResponse = userRepository.findById(userId)?.hidePassword()
                ?: return ServerResponse.notFound().buildAndAwait()

            return ServerResponse.ok().bodyValue(userResponse).awaitSingle()
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")

    }

    override suspend fun add(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSelf(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        val userData = userRepository.findById(user.id)?.hidePassword()
            ?: return ServerResponse.notFound().buildAndAwait()

        return ServerResponse.ok().bodyValueAndAwait(userData)
    }

}
