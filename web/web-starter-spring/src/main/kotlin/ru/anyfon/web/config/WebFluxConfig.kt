package ru.anyfon.web.config


import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

class WebFluxConfig : WebFluxConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/favicon?ico").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/static/app/**").addResourceLocations("classpath:/static/app/")
    }
}
