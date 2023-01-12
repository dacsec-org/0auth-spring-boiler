package org.dacsec.demo.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@link SecurityConfig} is the Spring Security configuration class.
 */
@EnableWebSecurity
public class SecurityConfig {
    private final LogoutHandler logoutHandler;
    
    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }
    
    /**
     * {@link SecurityConfig#filterChain(HttpSecurity)} is the  http {@link SecurityFilterChain}
     * that filters requests to the application, for login, logout, and access control.
     * @param http the {@link HttpSecurity} object.
     * @return the {@link SecurityFilterChain} that filters requests to the application.
     * @throws Exception if there is a problem configuring the {@link SecurityFilterChain}.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                 .oauth2Login()
                 .and().logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                 .addLogoutHandler(logoutHandler)
                 .and().build();
    }
}
