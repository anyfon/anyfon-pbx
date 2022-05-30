package ru.anyfon.asterisk.api.app.web.notify

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.pbx.common.domain.type.PhoneNumber


class CallNotifier(
    private val service: CallRecordService,
    private val mailSender: JavaMailSender
) {

    suspend fun notify() {
        val lastId = LastEventId.get()
        val records = service.fetchLastEndedRecords(lastId.toString(), 1000)

        records.lastOrNull()?.also {
            LastEventId.set(it.lastEventId.toString())
        }

        records.forEach { record ->
            val rootCalls = record.details.filter {
                (it.id.toString() == it.callRecordId.toString())
            }.sortedBy {
                it.sequence
            }

            val inbounds = rootCalls.filter {
                        it.did == PhoneNumber.Any(74952253376) || it.did == PhoneNumber.Any(79259296264)
            }

            if (inbounds.isEmpty()) return@forEach

            val isNoAnswered = inbounds.none {
                it.status == CallDetails.Status.ANSWERED && it.dcontext != "app-blackhole"
            }

            if (isNoAnswered) {
                val call = inbounds.first {
                    it.callRecordId.toString() == it.id.toString()
                }

                val message = SimpleMailMessage()

                message.from = "warn@anyfon.ru"

                message.setTo("geo5010031649@gmail.com")

                message.setCc(
                    "kkryloff@yandex.ru",
                    "dariasharova31@yandex.ru",
                    "igorhak@mail.ru",
                    "uleo0471@mail.ru",
                    "md3040@list.ru"
                )

                message.subject = "Пропущенный вызов"

                val text = "Пропущенный вызов в [ ${call.callDateTime} ] от [ ${call.src} ] на [ ${call.did} ]"

                message.text = text

                mailSender.send(message)

               println(text)
            }

            if (!isNoAnswered) {

                val call = inbounds.first {
                    it.callRecordId.toString() == it.id.toString()
                }

                val recordingFile =  service.findRecordingFile(call.id)

                val message = mailSender.createMimeMessage()

                val helper = MimeMessageHelper(message, true)

                helper.setFrom("warn@anyfon.ru")

                helper.setTo("geo5010031649@gmail.com")
                helper.setCc(
                    arrayOf(
                        "kkryloff@yandex.ru",
                        "dariasharova31@yandex.ru",
                        "igorhak@mail.ru",
                        "uleo0471@mail.ru",
                        "md3040@list.ru"
                    )
                )

                helper.setSubject("Входящий вызов")

                val text = "Входящий вызов в [ ${call.callDateTime} ] от [ ${call.src} ] на [ ${call.did} ]"

                helper.setText(text)

                recordingFile?.apply {
                    helper.addAttachment(this.name, this)
                }

                mailSender.send(message)

                println(text)
            }

        }

    }
}
