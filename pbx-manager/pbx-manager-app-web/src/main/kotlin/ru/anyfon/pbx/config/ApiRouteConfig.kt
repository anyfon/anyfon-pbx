package ru.anyfon.pbx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import ru.anyfon.pbx.resource.subscriber.SubscriberHandler
import ru.anyfon.pbx.resource.tenant.TenantHandler
import ru.anyfon.pbx.resource.user.UserHandler

@Configuration
class ApiRouteConfig(
    private val userHandler: UserHandler,
    private val tenantHandler: TenantHandler,
    private val subscriberHandler: SubscriberHandler
) {
    companion object {
        const val BASE_API_PATH = "/api"
    }

    @Bean
    fun apiRoutes() = coRouter {
        GET(UserHandler.FETCH_ONE_PATH, userHandler::fetchOne)
        GET(UserHandler.FETCH_LIST_PATH, userHandler::fetchList)
        POST(UserHandler.FETCH_SELF_PATH, userHandler::fetchSelf)

        GET(TenantHandler.FETCH_ONE_PATH, tenantHandler::fetchOne)
        GET(TenantHandler.FETCH_LIST_PATH, tenantHandler::fetchList)
        POST(TenantHandler.ADD_PATH, tenantHandler::add)


        GET(SubscriberHandler.FETCH_LIST_PATH, subscriberHandler::fetchList)
        GET(SubscriberHandler.FETCH_ONE_PATH, subscriberHandler::fetchOne)
        POST(SubscriberHandler.ADD_PATH, subscriberHandler::add)

    }
}
