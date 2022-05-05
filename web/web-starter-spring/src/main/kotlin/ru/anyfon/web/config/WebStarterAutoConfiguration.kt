package ru.anyfon.web.config

import org.springframework.context.annotation.Import

@Import(*[WebFluxConfig::class, AppRouterConfig::class, AppConfig::class])
class WebStarterAutoConfiguration
