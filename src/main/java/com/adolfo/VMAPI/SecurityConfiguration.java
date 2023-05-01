package com.adolfo.VMAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        return http.csrf().disable()
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/h2-console/**").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(withDefaults()).build();
    }

    @Bean
    protected InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder().username("adolfo").password("123").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

}
