package ru.anyfon.pbx.asteriskcdr

import ru.anyfon.pbx.manager.domain.calldetails.CallId

class AsteriskCdrBundle(
    val linkedId: String = "",
    val details: List<AsteriskCdr> = emptyList(),
    val events: List<AsteriskCel> = emptyList()
)
