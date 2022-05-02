package ru.anyfon.pbx.config

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import ru.anyfon.pbx.asteriskcdr.AsteriskCdrExtractor

@Configuration
class AsteriskConfig {
    private val cdrExtractor = AsteriskCdrExtractor.Base()

    @Scheduled(fixedRate = 30_000)
    fun loadCdr() {
        runBlocking {
            cdrExtractor.extractLast(171050)
        }
    }
}
