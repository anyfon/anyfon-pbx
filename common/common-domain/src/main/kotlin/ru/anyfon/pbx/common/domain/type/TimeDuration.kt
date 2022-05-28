package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class TimeDuration(seconds: Number) : Value.AsPositiveInt(seconds, true) {
    companion object {
        val EMPTY = TimeDuration(-1)
    }

}
