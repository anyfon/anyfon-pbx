package ru.anyfon.pbx.astersikcdr

import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.common.util.Asserts
import java.io.File

object LastLinkedId {
    const val FILE_NAME = ".last_linked_id"

    fun get() : String = retrieveFromFile() ?: "0"

    private fun retrieveFromFile() : String? {
        val file = File(FILE_NAME)
        if (!file.exists()) {
            println("File not exists")
            return null
        }
        return requiredId(file.readText())
    }


    fun set(id: String) {
        val reqId = requiredId(id)
        val file = File(FILE_NAME)
        if (file.exists()) file.delete()
        file.createNewFile()
        file.writeText(reqId)
    }

    private fun requiredId(id: String) : String =
        Asserts.requireStringMatching(id, CallDetails.ID.ID_PATTERN) {
            IllegalStateException("File must contains the last linked id of the asterisk cdr. " +
                    "[ pattern: ${CallDetails.ID.ID_PATTERN} e.g. 1627553821.13450 ]")
        }
}
