package ru.anyfon.asterisk.api.app.web.config

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Scheduled
import ru.anyfon.asterisk.api.app.web.notify.CallNotifier
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService


@Configuration
class NotifyConfig(service: CallRecordService, mailSender: JavaMailSender) {

    private val notifier = CallNotifier(service, mailSender)


    @Scheduled(fixedRate = 30_000)
    fun loadCdr() {
        runBlocking {
            notifier.notify()
        }
    }
}
