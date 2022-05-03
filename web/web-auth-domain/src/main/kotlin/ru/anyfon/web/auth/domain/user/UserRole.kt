package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.Value


class UserRole(name: String) : Value.AsString(name, "[A-Z\\_]{3,10}") {
    companion object {
        val ADMIN = UserRole("MANAGER")
        val ROOT = UserRole("ROOT")
        val CUSTOMER = UserRole("CUSTOMER")
    }
}
