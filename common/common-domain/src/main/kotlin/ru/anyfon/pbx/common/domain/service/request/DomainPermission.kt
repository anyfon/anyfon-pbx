package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.EntityID

interface DomainPermission {

    fun hasPermit(permission: Permission): Boolean

    fun DomainPermission.canCreate() = this@DomainPermission.hasPermit(Permission.Create)

    fun DomainPermission.canRead(id: EntityID<*>) = this@DomainPermission.hasPermit(Permission.ReadEntity(id))

    fun DomainPermission.canUpdate(id: EntityID<*>) = this@DomainPermission.hasPermit(Permission.Update(id))

    fun DomainPermission.canDelete(id: EntityID<*>) = this@DomainPermission.hasPermit(Permission.Delete(id))

}
