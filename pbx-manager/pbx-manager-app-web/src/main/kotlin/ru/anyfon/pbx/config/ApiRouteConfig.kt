package ru.anyfon.pbx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import ru.anyfon.pbx.resource.user.UserHandler

@Configuration
class ApiRouteConfig(
    private val userHandler: UserHandler
) {
    companion object {
        const val BASE_API_PATH = "/api"
    }

    @Bean
    fun apiRoutes() = coRouter {
        GET(UserHandler.FETCH_ONE_USER_PATH, userHandler::fetchOne)
        GET(UserHandler.FETCH_LIST_USER_PATH, userHandler::fetchList)
        GET(UserHandler.FETCH_SELF_USER_PATH, userHandler::fetchSelf)
    }
}
