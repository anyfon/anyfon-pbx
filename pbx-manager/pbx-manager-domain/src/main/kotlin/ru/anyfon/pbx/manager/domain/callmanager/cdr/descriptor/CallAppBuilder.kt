package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.manager.domain.callmanager.AppName
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallApp

class CallAppBuilder(
    private val appName: AppName
) {
    private val operations: MutableList<CallOperationBuilder> = mutableListOf()

    fun addOperation(operation: CallOperationBuilder) = operations.add(operation)

    fun build() : CallApp = CallApp(
        appName,
        operations.map { it.build() }
    )
}
