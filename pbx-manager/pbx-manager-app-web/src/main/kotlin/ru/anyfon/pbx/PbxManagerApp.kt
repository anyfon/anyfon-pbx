package ru.anyfon.pbx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PbxManagerApp


fun main(args: Array<String>) {
    runApplication<PbxManagerApp>(*args)
}
