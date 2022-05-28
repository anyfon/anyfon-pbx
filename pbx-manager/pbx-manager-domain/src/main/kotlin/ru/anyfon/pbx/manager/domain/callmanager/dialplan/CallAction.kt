package ru.anyfon.pbx.manager.domain.callmanager.dialplan

fun interface CallAction {
    fun execute(manager: CallContext)
}
