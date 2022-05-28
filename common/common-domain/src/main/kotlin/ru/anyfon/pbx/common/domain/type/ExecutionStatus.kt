package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

abstract class ExecutionStatus(name: String) : Value.AsString(name, "[A-Z\\_]{5,15}") {

    object Success : ExecutionStatus("SUCCESS")

    object Failure : ExecutionStatus("FAILURE")

    class Custom(name: String) : ExecutionStatus(name)

}
