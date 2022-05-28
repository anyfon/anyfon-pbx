package ru.anyfon.pbx.asteriskcdrt

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

interface AsteriskCdrExtractor {

    suspend fun extractLast(sequenceNum: Int) : List<AsteriskCdrBundle>

    class Base : AsteriskCdrExtractor {
        private val webClient = WebClient.create("http://sip.globalsip.net:8080")

        override suspend fun extractLast(sequenceNum: Int): List<AsteriskCdrBundle> {
            val request = mapOf<String, Any>(Pair("sequenceStart", 171050), Pair("callDateEnd", LocalDateTime.now()))
            return webClient.post().uri("/api/cdr/find-all").bodyValue(request).exchangeToFlux {
                it.bodyToFlux(AsteriskCdrBundle::class.java)
            }.collectList().awaitSingle().map {
                println(it)
                it
            }
        }

    }
}
