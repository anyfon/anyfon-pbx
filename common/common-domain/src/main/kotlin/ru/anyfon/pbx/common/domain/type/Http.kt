package ru.anyfon.pbx.common.domain.type

object Http {
    enum class Method {
        GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
    }

    enum class Status(val status: Int) {
        OK(200), NOT_FOUND(404);
    }
}
