package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.web.auth.domain.user.User

interface TenantRepository {

    suspend fun add(tenant: TenantDetails)

    suspend fun findById(id: Tenant.ID) : TenantDetails?

    suspend fun findByName(name: Tenant.UniqueName) : TenantDetails?

    suspend fun findAll(limit: Int = 50, offset: Int = 0) : List<TenantDetails>

    suspend fun addUser(tenantId: Tenant.ID, vararg userId: User.ID) = addUsers(tenantId, userId.toList())

    suspend fun addUsers(tenantId: Tenant.ID, usersId: List<User.ID>)

    suspend fun removeUser(tenantId: Tenant.ID, vararg userId: User.ID)

    suspend fun removeUsers(tenantId: Tenant.ID, usersId: List<User.ID>)
}
