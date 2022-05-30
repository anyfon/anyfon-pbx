package ru.anyfon.asterisk.api.data.cdr

import io.r2dbc.spi.Row
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallRecord
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
    private val sequence: Int = -1,
    val duration: Int? = null
) {
    constructor(row: Row) : this(
        row["uniqueid"]?.toString() ?: "",
        row["linkedid"]?.toString() ?: "",
        LocalDateTime.parse(row["calldate"]?.toString()),
        row["src"]?.toString(),
        row["dst"]?.toString(),
        row["did"]?.toString(),
        row["cnum"]?.toString(),
        row["recordingfile"]?.toString(),
        row["dcontext"]?.toString() ?: "",
        row["disposition"]?.toString() ?: "",
        ConvertUtils.toNumber(row["sequence"])?.toInt() ?: -1,
        ConvertUtils.toNumber(row["duration"])?.toInt()
    )

    fun toCallDetails(): CallDetails =
        CallDetails(
            CallDetails.ID(uniqueid),
            CallRecord.ID(linkedid),
            calldate,
            ConvertUtils.tryOrNull { PhoneNumber.Any(src) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(dst) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(did) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(cnum) },
            recordingfile?.isNotBlank() == true,
            recordingfile,
            dcontext,
            CallDetails.Status.valueOf(disposition.replace(" ", "_")),
            sequence
        )
}
