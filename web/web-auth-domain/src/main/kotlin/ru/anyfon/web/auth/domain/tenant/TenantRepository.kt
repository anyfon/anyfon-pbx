package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.service.request.Request
import ru.anyfon.pbx.common.domain.type.Tenant

interface TenantRepository {

    suspend fun add(request: TenantRequest): Tenant.ID

    suspend fun findById(id: Request.Valid<Tenant.ID>) : TenantData?

    suspend fun findByName(uniqueName: Request.Valid<Tenant.UniqueName>) : TenantData?
//
//    suspend fun findAll(limit: Int = 50, offset: Int = 0) : List<TenantDetails>
//
//    suspend fun addUser(tenantId: Tenant.ID, vararg userId: User.ID) = addUsers(tenantId, userId.toList())
//
//    suspend fun addUsers(tenantId: Tenant.ID, usersId: List<UserDetails.ID>)
//
//    suspend fun removeUser(tenantId: Tenant.ID, vararg userId: UserDetails.ID)
//
//    suspend fun removeUsers(tenantId: Tenant.ID, usersId: List<UserDetails.ID>)
}
