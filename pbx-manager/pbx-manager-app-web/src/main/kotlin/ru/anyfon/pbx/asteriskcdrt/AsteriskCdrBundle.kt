package ru.anyfon.pbx.asteriskcdrt

class AsteriskCdrBundle(
    val linkedId: String = "",
    val details: List<AsteriskCdr> = emptyList(),
    val events: List<AsteriskCel> = emptyList()
)
