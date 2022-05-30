package ru.anyfon.pbx.resource.user

import org.springframework.security.core.GrantedAuthority
import ru.anyfon.web.auth.domain.user.service.UserData
import java.security.Principal
import org.springframework.security.core.userdetails.UserDetails as SpringUserDetails

class AuthorizedUser(
    private val user: UserData,
    private val passwordHash: String
) : Principal, SpringUserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(object : GrantedAuthority {
            override fun getAuthority(): String = user.role.toString()
        })
    override fun getPassword(): String = passwordHash

    override fun getUsername(): String = user.phoneNumber.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = isEnabled

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = user.enabled

    override fun getName(): String = username

}
