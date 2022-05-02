package ru.anyfon.pbx.manager.domain.calldetails

import ru.anyfon.pbx.common.domain.Value

class CallDirection(name: String) : Value.AsString(name, NAME_PATTERN) {
    companion object {
        const val NAME_PATTERN = "[A-Z\\_]{5,20}"

        val INBOUND = CallDirection("INBOUND")

        val OUTBOUND = CallDirection("OUTBOUND")

        val INTERNAL = CallDirection("INTERNAL")
    }
}
