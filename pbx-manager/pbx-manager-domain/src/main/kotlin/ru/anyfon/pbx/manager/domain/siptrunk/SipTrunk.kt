package ru.anyfon.pbx.manager.domain.siptrunk

import ru.anyfon.pbx.common.domain.Value

interface SipTrunk {

    val id: ID

    companion object {
        val INTERNAL = object : SipTrunk{
            override val id: ID = ID("internal")
        }
    }

    class ID(id: String) : Value.AsString(id)
}
