package ru.anyfon.web.config

import org.springframework.context.annotation.Import

@Import(*[WebFluxConfig::class, RouterConfig::class])
class WebStarterAutoConfiguration
