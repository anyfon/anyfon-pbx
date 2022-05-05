package ru.anyfon.pbx.resource.tenant

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class TenantHandlerImpl : TenantHandler {
    override suspend fun fetchList(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override suspend fun fetchOne(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }

    override suspend fun add(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }
}
