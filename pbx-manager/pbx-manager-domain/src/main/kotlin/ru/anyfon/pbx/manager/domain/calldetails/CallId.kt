package ru.anyfon.pbx.manager.domain.calldetails

import ru.anyfon.pbx.common.domain.EntityID

class CallId(id: String) : EntityID.AsString(id, "[A-Z0-9\\-\\.]{12,36}") //UUID || UNIQUEID (Astersik)
