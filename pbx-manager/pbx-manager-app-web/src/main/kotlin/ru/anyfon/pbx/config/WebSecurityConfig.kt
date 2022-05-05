package ru.anyfon.pbx.config

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.web.auth.domain.user.UserRepository


@Configuration
class WebSecurityConfig {

    @Bean
    fun bcryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return http
            .httpBasic().disable()
            .formLogin().and()
            .authorizeExchange()
            .pathMatchers("/login").permitAll()
            .pathMatchers("/**").authenticated()
            .and() //.addFilterAfter( this.authenticationWebFilter, SecurityWebFiltersOrder.REACTOR_CONTEXT )
            .build()
    }

    @Bean
    fun reactiveUserDetailsService(repository: UserRepository): ReactiveUserDetailsService =
        ReactiveUserDetailsService {
            val user = runBlocking {
                ConvertUtils.tryOrNull { PhoneNumber.External(it) }?.let {
                    repository.findByPhone(it)
                } ?:
                ConvertUtils.tryOrNull { Email(it) }?.let {
                    repository.findByEmail(it)
                }
            }

            return@ReactiveUserDetailsService user?.let {
                val details = object : UserDetails {

                    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
                        mutableListOf(object : GrantedAuthority {
                            override fun getAuthority(): String = it.role.toString()
                        })

                    override fun getPassword(): String = it.password

                    override fun getUsername(): String = it.phoneNumber.toString()

                    override fun isAccountNonExpired(): Boolean = true

                    override fun isAccountNonLocked(): Boolean = true

                    override fun isCredentialsNonExpired(): Boolean = true

                    override fun isEnabled(): Boolean = it.enabled

                }
                Mono.just(details)
            } ?: Mono.empty()

        }
}
