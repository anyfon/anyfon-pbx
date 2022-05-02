package ru.anyfon.pbx.asteriskcdr

import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

class AsteriskCel(
    val eventDateTime: LocalDateTime? = null,
    val eventType: String? = null,
    val cidNum: String? = null,
    val cidAni: String? = null,
    val cidRdnis: String? = null,
    val cidDnid: String? = null,
    val exten: String? = null,
    val context: String? = null,
    val appName: String? = null,
    val linkedId: String = "",
    val uniqueId: String = ""
)
