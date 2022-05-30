package ru.anyfon.asterisk.api.app.web.notify

import ru.anyfon.pbx.common.domain.Value
import java.io.File

object LastEventId {

    const val FILE_NAME = ".last_event_id"

    fun get() : Value.PositiveZeroInt = retrieveFromFile()

    private fun retrieveFromFile() : Value.PositiveZeroInt {
        val file = File(FILE_NAME)
        if (!file.exists()) {
            println("File [ $FILE_NAME ] not exists")
            return Value.PositiveZeroInt(0)
        }
        return Value.PositiveZeroInt(file.readText())
    }


    fun set(id: String) {
        val reqId = Value.PositiveZeroInt(id)
        val file = File(FILE_NAME)
        if (file.exists()) file.delete()
        file.createNewFile()
        file.writeText(reqId.toString())
    }


}
