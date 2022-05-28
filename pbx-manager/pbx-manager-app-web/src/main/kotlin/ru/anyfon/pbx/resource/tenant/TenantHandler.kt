package ru.anyfon.pbx.resource.tenant

import ru.anyfon.pbx.config.ApiRouteConfig
import ru.anyfon.pbx.resource.ResourceHandler

interface TenantHandler : ResourceHandler {
    companion object {

        const val BASE_PATH = ApiRouteConfig.BASE_API_PATH.plus("/auth/tenant")

        const val ID_PARAM = "tenantId"

        const val FETCH_LIST_PATH = BASE_PATH.plus("/fetch-list")

        const val FETCH_ONE_PATH = BASE_PATH.plus("/fetch-one/{$ID_PARAM}")

        const val ADD_PATH = BASE_PATH.plus("/add")

    }

}
