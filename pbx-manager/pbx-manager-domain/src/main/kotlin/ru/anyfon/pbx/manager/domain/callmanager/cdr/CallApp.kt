package ru.anyfon.pbx.manager.domain.callmanager.cdr

import ru.anyfon.pbx.manager.domain.callmanager.AppName

class CallApp(
    val name: AppName,
    val operations: Iterable<CallOperation>
)
