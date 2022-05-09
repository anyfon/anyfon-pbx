package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class TimeDuration(seconds: Int) : Value.AsPositiveInt(seconds, true) {
}
