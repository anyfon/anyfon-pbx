package ru.anyfon.pbx.resource

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.resource.user.AuthorizedUser
import java.security.Principal

interface ResourceHandler {
    suspend fun fetchList(serverRequest: ServerRequest) : ServerResponse

    suspend fun fetchOne(serverRequest: ServerRequest) : ServerResponse

    suspend fun add(serverRequest: ServerRequest) : ServerResponse

    suspend fun ResourceHandler.retrieveAuthorizedUser(request: ServerRequest) : AuthorizedUser? {
        return request.exchange().getPrincipal<Principal>().mapNotNull {
            if (it is UsernamePasswordAuthenticationToken && it.isAuthenticated) {
                return@mapNotNull ConvertUtils.tryOrNull {  it.principal as AuthorizedUser }
            }
            return@mapNotNull null
        }.awaitSingle()
    }

}
