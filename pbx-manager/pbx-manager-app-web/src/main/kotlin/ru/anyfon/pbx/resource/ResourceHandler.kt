package ru.anyfon.pbx.resource

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.reactive.function.server.ServerRequest
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.resource.user.AuthorizedUser
import java.security.Principal

interface ResourceHandler

suspend fun ResourceHandler.retrieveAuthorizedUser(request: ServerRequest) : AuthorizedUser? {
    return request.exchange().getPrincipal<Principal>().mapNotNull {
        if (it is UsernamePasswordAuthenticationToken && it.isAuthenticated) {
            return@mapNotNull ConvertUtils.tryOrNull {  it.principal as AuthorizedUser }
        }
        return@mapNotNull null
    }.awaitSingle()
}
