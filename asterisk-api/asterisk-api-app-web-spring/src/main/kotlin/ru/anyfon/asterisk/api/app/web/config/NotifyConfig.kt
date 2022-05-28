package ru.anyfon.asterisk.api.app.web.config

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Scheduled
import ru.anyfon.asterisk.api.app.web.notify.NoAnswerNotifier
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService


@Configuration
class NotifyConfig(service: CallRecordService, mailSender: JavaMailSender) {

    private val notifier = NoAnswerNotifier(service, mailSender)


    @Scheduled(fixedRate = 90_000)
    fun loadCdr() {
        runBlocking {
            notifier.notify()
        }
    }
}
