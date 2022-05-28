package ru.anyfon.pbx.manager.domain.callmanager

import ru.anyfon.pbx.common.domain.EntityID

class CallID(id: Any?) : EntityID.AsLong(id) {
    companion object {
        val EMPTY = CallID(0)
    }

    override fun isEmpty(): Boolean = this == EMPTY
}
