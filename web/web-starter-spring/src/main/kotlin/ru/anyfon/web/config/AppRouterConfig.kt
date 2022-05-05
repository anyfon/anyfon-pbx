package ru.anyfon.web.config

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import ru.anyfon.web.handler.WebAppHandler

class AppRouterConfig {
    private val webAppHandler = WebAppHandler()
    @Bean
    fun webRoutes() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/", webAppHandler::handle)
            GET("/#**", webAppHandler::handle)
        }
    }
}
