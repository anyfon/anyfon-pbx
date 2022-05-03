package ru.anyfon.pbx.common.domain.type

import ru.anyfon.common.util.Asserts
import ru.anyfon.pbx.common.domain.Value

class Email(email: String?) : Value.AsString(Asserts.requireStringMatching(email, EMAIL_PATTERN)) {

    companion object {
        const val EMAIL_PATTERN = "^[A-Za-z0-9\\_\\-\\+]+(\\.[A-Za-z0-9\\-\\_]+)*" +
                "@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"

    }
}
