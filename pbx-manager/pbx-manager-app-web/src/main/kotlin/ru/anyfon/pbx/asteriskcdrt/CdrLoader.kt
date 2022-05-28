package ru.anyfon.pbx.asteriskcdrt

import org.springframework.scheduling.annotation.Scheduled

interface CdrLoader {
    fun load()

    class Base(
        private val cdrExtractor: AsteriskCdrExtractor
    ) : CdrLoader {

        @Scheduled(fixedRate = 60_000)
        override fun load() {

        }

    }
}
