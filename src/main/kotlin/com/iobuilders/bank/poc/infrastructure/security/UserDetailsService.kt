package com.iobuilders.bank.poc.infrastructure.security
import com.iobuilders.bank.poc.domain.service.UserService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserDetailsService(
    private val userService: UserService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        // Create a method in your repo to find a user by its username
        val user = userService.findUser(1L) ?: throw UsernameNotFoundException("$username not found")
        return UserSecurity(
            user.id.toString(),
            user.username,
            user.password,
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}
