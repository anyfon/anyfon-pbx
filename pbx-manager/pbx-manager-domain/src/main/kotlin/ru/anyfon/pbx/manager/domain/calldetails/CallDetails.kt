package ru.anyfon.pbx.manager.domain.calldetails

import ru.anyfon.pbx.manager.domain.calldetails.record.CallDetailRecord

class CallDetails(
    val id: CallId,
    val records: List<CallDetailRecord>
)
