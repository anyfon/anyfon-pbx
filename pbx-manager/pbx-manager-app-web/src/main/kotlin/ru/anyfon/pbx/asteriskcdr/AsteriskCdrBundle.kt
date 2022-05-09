package ru.anyfon.pbx.asteriskcdr

class AsteriskCdrBundle(
    val linkedId: String = "",
    val details: List<AsteriskCdr> = emptyList(),
    val events: List<AsteriskCel> = emptyList()
)
