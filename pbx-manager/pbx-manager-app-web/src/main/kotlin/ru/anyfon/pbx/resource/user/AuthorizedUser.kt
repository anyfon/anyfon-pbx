package ru.anyfon.pbx.resource.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.anyfon.web.auth.domain.user.User
import java.security.Principal

class AuthorizedUser(
    private val user: User
) : UserDetails, Principal {

    val id = user.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(object : GrantedAuthority {
            override fun getAuthority(): String = user.role.toString()
        })
    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.phoneNumber.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = isEnabled

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = user.enabled

    override fun getName(): String = username

}
