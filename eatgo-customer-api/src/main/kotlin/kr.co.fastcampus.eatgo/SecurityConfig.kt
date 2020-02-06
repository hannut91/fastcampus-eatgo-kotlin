package kr.co.fastcampus.eatgo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.cors().disable()
                    .csrf().disable()
                    .formLogin().disable()
                    .headers().frameOptions().disable()
        }
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
