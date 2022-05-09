package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.Value

class TerminationStatus(name: String) : Value.AsString(name, "[A-Z\\_]{5,15}") {
    companion object {
        val ANY = TerminationStatus("ANY")
        val ANSWERED = TerminationStatus("ANSWERED")
        val NO_ANSWER = TerminationStatus("NO_ANSWER")
        val USER_BUSY = TerminationStatus("USER_BUSY")
        val TRUNK_FAILURE = TerminationStatus("TRUNK_FAILURE")
        val USER_NOT_AVAILABLE = TerminationStatus("USER_NOT_AVAILABLE")
        val WRONG_NUMBER = TerminationStatus("ERROR_NUMBER")
    }
}
