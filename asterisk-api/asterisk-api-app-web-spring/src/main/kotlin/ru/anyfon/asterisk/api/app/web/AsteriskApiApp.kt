package ru.anyfon.asterisk.api.app.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class AsteriskApiApp

fun main(args: Array<String>) {
    runApplication<AsteriskApiApp>(*args)
}
