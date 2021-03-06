package ru.anyfon.web.auth.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.web.auth.domain.tenant.TenantData
import ru.anyfon.web.auth.domain.tenant.TenantRepository
import ru.anyfon.web.auth.domain.user.service.UserData

class R2dbcTenantRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : TenantRepository {
    override suspend fun add(tenant: TenantData) {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Tenant.ID): TenantData? {
        TODO("Not yet implemented")
    }

    override suspend fun findByName(name: Tenant.UniqueName): TenantData? {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(limit: Int, offset: Int): List<TenantData> {
        TODO("Not yet implemented")
    }

    override suspend fun addUsers(tenantId: Tenant.ID, usersId: List<UserData.ID>) {
        TODO("Not yet implemented")
    }

    override suspend fun removeUser(tenantId: Tenant.ID, vararg userId: UserData.ID) {
        TODO("Not yet implemented")
    }

    override suspend fun removeUsers(tenantId: Tenant.ID, usersId: List<UserData.ID>) {
        TODO("Not yet implemented")
    }

}
