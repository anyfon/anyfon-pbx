package ru.anyfon.asterisk.api.data.cdr

import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.asterisk.api.domain.cdr.CallRecord
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import java.time.LocalDateTime

@Table("cdr")
class DetailRecordEntity(
    private val uniqueid: String = "",
    private val linkedid: String = "",
    private val calldate: LocalDateTime = LocalDateTime.MIN,
    private val src: String? = null,
    private val dst: String? = null,
    private val did: String? = null,
    private val cnum: String? = null,
    private val recordingfile: String? = null,
    private val dcontext: String = "",
    private val disposition: String = "",
    private val sequence: Int = -1
) {
    fun toDetailRecord() : CallDetails =
        CallDetails(
            CallDetails.ID(uniqueid),
            CallRecord.ID(linkedid),
            calldate,
            ConvertUtils.tryOrNull { PhoneNumber.Any(src) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(dst) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(did) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(cnum) },
            recordingfile?.isNotBlank() == true,
            dcontext,
            CallDetails.Status.valueOf(disposition.replace(" ", "_")),
            sequence
        )
}
