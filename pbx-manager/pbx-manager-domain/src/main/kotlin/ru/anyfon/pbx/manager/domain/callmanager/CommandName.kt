package ru.anyfon.pbx.manager.domain.callmanager

import ru.anyfon.pbx.common.domain.Value

class CommandName(name: String?) : Value.AsString(name, "[\\w]{5,25}")
