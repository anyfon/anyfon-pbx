package ru.anyfon.web.auth.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.tenant.Tenant
import ru.anyfon.web.auth.domain.tenant.TenantRepository
import ru.anyfon.web.auth.domain.user.User

class R2dbcTenantRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : TenantRepository {
    override suspend fun add(tenant: Tenant) {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: TenantID): Tenant? {
        TODO("Not yet implemented")
    }

    override suspend fun findByName(name: Tenant.UniqueName): Tenant? {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(limit: Int, offset: Int): List<Tenant> {
        TODO("Not yet implemented")
    }

    override suspend fun addUsers(tenantId: TenantID, usersId: List<User.ID>) {
        TODO("Not yet implemented")
    }

    override suspend fun removeUser(tenantId: TenantID, vararg userId: User.ID) {
        TODO("Not yet implemented")
    }

    override suspend fun removeUsers(tenantId: TenantID, usersId: List<User.ID>) {
        TODO("Not yet implemented")
    }

}
