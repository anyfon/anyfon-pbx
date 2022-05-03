package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.user.User

interface TenantRepository {

    suspend fun add(tenant: Tenant)

    suspend fun findById(id: TenantID) : Tenant?

    suspend fun findByName(name: Tenant.UniqueName) : Tenant?

    suspend fun addUser(tenantId: TenantID, vararg userId: User.ID) = addUsers(tenantId, userId.toList())

    suspend fun addUsers(tenantId: TenantID, usersId: List<User.ID>)

    suspend fun removeUser(tenantId: TenantID, vararg userId: User.ID)

    suspend fun removeUsers(tenantId: TenantID, usersId: List<User.ID>)
}
