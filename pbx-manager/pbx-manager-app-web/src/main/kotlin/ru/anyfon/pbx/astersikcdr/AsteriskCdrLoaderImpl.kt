package ru.anyfon.pbx.astersikcdr

import kotlinx.coroutines.runBlocking
import ru.anyfon.asterisk.api.domain.cdr.CallRecord
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService

class AsteriskCdrLoaderImpl(
    private val asteriskCdrService: CallRecordService,
    private val cdrHandler: AsteriskCdrHandler
) : AsteriskCdrLoader {

    override fun load(limit: Int) {
        runBlocking {
            val cdrs = asteriskCdrService.fetchLastEndedRecords(LastLinkedId.get(), 10)
            cdrs.forEach {
                cdrHandler.handle(it)
            }
            cdrs.lastOrNull()?.also {
                LastLinkedId.set(it.id.toString())
            }
        }
    }

    private suspend fun addRecord(callRecord: CallRecord) {
        //callRecordDescriptor.create().save()
    }
}
