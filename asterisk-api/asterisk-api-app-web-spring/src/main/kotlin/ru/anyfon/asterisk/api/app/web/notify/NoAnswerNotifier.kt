package ru.anyfon.asterisk.api.app.web.notify

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import java.awt.SystemColor.text

class NoAnswerNotifier(
    private val service: CallRecordService,
    private val mailSender: JavaMailSender
) {
    private var lastRecordId = "1651834117.137810"

    suspend fun notify() {
//        val records = service.fetchLastEndedRecords(lastRecordId, 1000)
//
//        records.forEach { record ->
//            val inbounds = record.details.filter {
//                (it.did == PhoneNumber.Any(74952253376) || it.did == PhoneNumber.Any(79259296264))
//            }
//
//            if (inbounds.isEmpty()) return@forEach
//
//            val isNoAnswered = inbounds.none {
//                it.status == CallDetails.Status.ANSWERED
//            }
//
//            if (record.details.isNotEmpty() && isNoAnswered) {
//                val call = record.details.first()
//
//                val message = SimpleMailMessage()
//                message.from = "warn@anyfon.ru"
//                message.setTo("geo5010031649@gmail.com")
//                message.setCc("kkryloff@yandex.ru", "dariasharova31@yandex.ru", "igorhak@mail.ru")
//                message.subject = "Пропущенный вызов"
//                val text = "Пропущенный вызов в [ ${call.callDateTime} ] от [ ${call.src} ] на [ ${call.did} ]"
//                message.text = text
//                mailSender.send(message)
//                println(text)
//            }
//
//        }
//
//        records.lastOrNull()?.also {
//            lastRecordId = it.id.toString()
//        }
    }
}
