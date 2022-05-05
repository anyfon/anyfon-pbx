package ru.anyfon.pbx.resource.tenant

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import ru.anyfon.pbx.config.ApiRouteConfig
import ru.anyfon.pbx.resource.ResourceHandler

interface TenantHandler : ResourceHandler {
    companion object {

        private const val AUTH_TENANT_PATH = ApiRouteConfig.BASE_API_PATH.plus("/auth/tenant")

        const val TENANT_ID_PARAM = "tenantId"

        const val FETCH_TENANT_LIST_PATH = AUTH_TENANT_PATH.plus("/fetch-list")

        const val FETCH_ONE_TENANT_PATH = AUTH_TENANT_PATH.plus("/fetch-one/{$TENANT_ID_PARAM}")

        const val ADD_TENANT_PATH = AUTH_TENANT_PATH.plus("/add")

    }


    suspend fun fetchList(serverRequest: ServerRequest) : ServerResponse

    suspend fun fetchOne(serverRequest: ServerRequest) : ServerResponse

    suspend fun add(serverRequest: ServerRequest) : ServerResponse
}
