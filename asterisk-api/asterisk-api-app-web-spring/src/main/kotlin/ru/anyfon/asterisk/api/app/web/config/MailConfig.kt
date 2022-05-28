package ru.anyfon.asterisk.api.app.web.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfig {
    @Bean
    fun mailSender(
        @Value("\${anyfon.mail.username}") username: String,
        @Value("\${anyfon.mail.password}") password: String
    ): JavaMailSender {

        val mailSender = JavaMailSenderImpl()

        mailSender.host = "smtp.mail.ru"
        mailSender.port = 465
        mailSender.username = username
        mailSender.password = password

        val props: Properties = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        // props["mail.debug"] = "true"
        props["mail.smtp.ssl.enable"] = true

        return mailSender
    }

}
