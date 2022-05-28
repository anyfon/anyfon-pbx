package ru.anyfon.pbx.resource.tenant

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.tenant.TenantRepository

class TenantHandlerImpl(
    private val tenantRepository: TenantRepository
) : TenantHandler {
    override suspend fun fetchList(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        if (user.authorities.any { it.authority == "ROOT" }) {
            val list = tenantRepository.findAll()
            return ServerResponse.ok().bodyValueAndAwait(list)
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")
    }

    override suspend fun fetchOne(serverRequest: ServerRequest): ServerResponse {
        val user = retrieveAuthorizedUser(serverRequest)
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Principal undefined")

        val tenantId = ConvertUtils.tryOrNull { TenantID(serverRequest.pathVariable(TenantHandler.ID_PARAM)) }
            ?: return ServerResponse.notFound().buildAndAwait()

        if (user.authorities.any { it.authority == "ROOT" || it.authority == "MANAGER" } ) {
            val response = tenantRepository.findById(tenantId)
                ?: return ServerResponse.notFound().buildAndAwait()

            return ServerResponse.ok().bodyValue(response).awaitSingle()
        }
        return ServerResponse.status(HttpStatus.FORBIDDEN).bodyValueAndAwait("Forbidden")
    }

    override suspend fun add(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }
}
