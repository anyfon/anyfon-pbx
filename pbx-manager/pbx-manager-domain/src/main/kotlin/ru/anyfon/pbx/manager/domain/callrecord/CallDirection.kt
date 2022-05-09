package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.Value

class CallDirection(name: String) : Value.AsString(name, "[A-Z\\_]{5,15}") {
    companion object {
        val ANY = CallDirection("ANY")
        val INBOUND = CallDirection("INBOUND")
        val OUTBOUND = CallDirection("OUTBOUND")
        val INTERNAL = CallDirection("INTERNAL")
    }
}
