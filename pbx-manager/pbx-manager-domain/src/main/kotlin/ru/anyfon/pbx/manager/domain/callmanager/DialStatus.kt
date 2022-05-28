package ru.anyfon.pbx.manager.domain.callmanager

import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.type.ExecutionStatus

abstract class DialStatus(
    name: String,
    val executionStatus: ExecutionStatus
) : Value.AsString(name, "[A-Z\\_]{5,15}") {

    object Any : DialStatus("ANY", EmptyExecutionStatus)

    object Answered : DialStatus("ANSWERED", ExecutionStatus.Success)

    object NoAnswered : DialStatus("NO_ANSWER", ExecutionStatus.Failure)

    object UserBusy : DialStatus("USER_BUSY", ExecutionStatus.Failure)

    object TrunkFailure : DialStatus("TRUNK_FAILURE", ExecutionStatus.Failure)

    object UserNotAvailable : DialStatus("USER_NOT_AVAILABLE", ExecutionStatus.Failure)

    object WrongNumber : DialStatus("WRONG_NUMBER", ExecutionStatus.Failure)

    private object EmptyExecutionStatus : ExecutionStatus("EMPTY")

}
