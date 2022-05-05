package ru.anyfon.pbx.resource.user

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import ru.anyfon.pbx.config.ApiRouteConfig
import ru.anyfon.pbx.resource.ResourceHandler

interface UserHandler : ResourceHandler {

    companion object {

        private const val AUTH_USER_PATH = ApiRouteConfig.BASE_API_PATH.plus("/auth/user")

        const val USER_ID_PARAM = "userId"

        const val FETCH_LIST_USER_PATH = AUTH_USER_PATH.plus("/fetch-list")

        const val FETCH_ONE_USER_PATH = AUTH_USER_PATH.plus("/fetch-one/{$USER_ID_PARAM}")

        const val FETCH_SELF_USER_PATH = AUTH_USER_PATH.plus("/fetch-self")

        const val ADD_USER_PATH = AUTH_USER_PATH.plus("/add")

    }


    suspend fun fetchList(serverRequest: ServerRequest) : ServerResponse

    suspend fun fetchOne(serverRequest: ServerRequest) : ServerResponse

    suspend fun add(serverRequest: ServerRequest) : ServerResponse

    suspend fun fetchSelf(serverRequest: ServerRequest) : ServerResponse

}
