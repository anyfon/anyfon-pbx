package ru.anyfon.pbx.manager.domain.callmanager.cdr

class ExecutionSequence(
    val apps: Iterable<CallApp>
) {
    val allOperations: Iterable<CallOperation> = apps.flatMap { it.operations }
}
