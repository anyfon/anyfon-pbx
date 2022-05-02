package ru.anyfon.pbx.asteriskcdr

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

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
