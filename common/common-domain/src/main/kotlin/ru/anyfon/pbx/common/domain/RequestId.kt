package ru.anyfon.pbx.common.domain

class RequestId(uuid: Any?) : EntityID.AsUuidString(uuid) {

    companion object {
        val EMPTY = RequestId(EMPTY_UUID)
    }

    override fun isEmpty(): Boolean = this == EMPTY
}
