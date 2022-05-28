package ru.anyfon.pbx.manager.domain.callmanager

import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.PhoneNumber

abstract class CallDirection(name: String) : Value.AsString(name, "[A-Z\\_]{5,15}"), ValueObject {

    object Inbound : CallDirection("INBOUND")

    object Outbound : CallDirection("OUTBOUND")

    object Internal : CallDirection("INTERNAL")

    companion object {
        fun of(fromNumber: PhoneNumber.Any, toNumber: PhoneNumber.Any) : CallDirection {
            if (fromNumber.isExternal && toNumber.isExternal) {
                return Inbound
            }
            if (fromNumber.isInternal && toNumber.isExternal) {
                return Outbound
            }
            return Internal
        }
    }

}
