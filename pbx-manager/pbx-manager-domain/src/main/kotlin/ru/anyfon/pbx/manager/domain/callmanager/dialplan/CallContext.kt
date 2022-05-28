package ru.anyfon.pbx.manager.domain.callmanager.dialplan

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TimeDuration
import java.util.function.Consumer

interface CallContext {

    val metadata: CallMetadata

    fun dial(number: PhoneNumber.Any)

    fun groupDial(numbers: Iterable<PhoneNumber.Any>, onAnswer: Consumer<Any>, onFailure: Consumer<Any>)

    fun playMedia()

    fun startRecord()

    fun waitForDigit(timeout: TimeDuration = TimeDuration.EMPTY) : Char

    fun waitForData(timeout: TimeDuration = TimeDuration.EMPTY) : String

    fun hangup()
}
