package ru.anyfon.asterisk.api.app.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsteriskApiApp

fun main(args: Array<String>) {
    runApplication<AsteriskApiApp>(*args)
}
