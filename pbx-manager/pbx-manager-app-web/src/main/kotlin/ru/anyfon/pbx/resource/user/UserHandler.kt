package ru.anyfon.pbx.resource.user

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import ru.anyfon.pbx.config.ApiRouteConfig
import ru.anyfon.pbx.resource.ResourceHandler

interface UserHandler : ResourceHandler {

    companion object {

        const val BASE_PATH = ApiRouteConfig.BASE_API_PATH.plus("/auth/user")

        const val ID_PARAM = "userId"

        const val FETCH_LIST_PATH = BASE_PATH.plus("/fetch-list")

        const val FETCH_ONE_PATH = BASE_PATH.plus("/fetch-one/{$ID_PARAM}")

        const val ADD_PATH = BASE_PATH.plus("/add")

        const val FETCH_SELF_PATH = BASE_PATH.plus("/fetch-self")

    }

    suspend fun fetchSelf(serverRequest: ServerRequest) : ServerResponse

}
