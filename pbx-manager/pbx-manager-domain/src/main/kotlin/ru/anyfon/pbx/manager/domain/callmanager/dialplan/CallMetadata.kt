package ru.anyfon.pbx.manager.domain.callmanager.dialplan

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.manager.domain.callmanager.CallDirection
import ru.anyfon.pbx.manager.domain.callmanager.CallID
import java.time.LocalDateTime
import java.util.Locale

interface CallMetadata {

    val locale: Locale

    val callId: CallID

    val startDateTime: LocalDateTime

    val direction: CallDirection

    val source: PhoneNumber.Any

}
