package ru.anyfon.asterisk.api.data.cdr

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.asterisk.api.domain.cdr.CallRecord
import ru.anyfon.asterisk.api.domain.cdr.ChannelEvent
import ru.anyfon.asterisk.api.domain.cdr.DetailRecord
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import java.time.LocalDateTime

@Table("cel")
class ChannelEventEntity(
    @Column("eventtime")
    private val eventDateTime: LocalDateTime? = null,

    @Column("eventtype")
    private val eventType: String? = null,

    @Column("cid_num")
    private val cidNum: String? = null,

    @Column("cid_ani")
    private val cidAni: String? = null,

    @Column("cid_rdnis")
    private val cidRdnis: String? = null,

    @Column("cid_dnid")
    private val cidDnid: String? = null,

    private val exten: String? = null,

    private val context: String? = null,

    @Column("appname")
    private val appName: String? = null,

    @Column("linkedid")
    private val linkedId: String = "",

    @Column("uniqueid")
    private val uniqueId: String = ""
) {
    fun toChannelEvent(): ChannelEvent =
        ChannelEvent(
            eventDateTime ?: throw IllegalArgumentException("Field [ eventDateTime ] must not be null"),
            ChannelEvent.Type.valueOf(eventType.toString()),
            CallRecord.ID(linkedId),
            DetailRecord.ID(uniqueId),
            ConvertUtils.tryOrNull { PhoneNumber.Any(cidNum) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(cidAni) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(cidRdnis) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(cidDnid) },
            ConvertUtils.tryOrNull { PhoneNumber.Any(exten) },
            context.toString(),
            appName.toString()
        )

}
