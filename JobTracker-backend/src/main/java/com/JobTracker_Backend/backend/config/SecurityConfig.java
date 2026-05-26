package com.JobTracker_Backend.backend.config;

import com.JobTracker_Backend.backend.user.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig.java
 * ------------------------
 * Spring Security configuration class for the Job Tracker backend.
 *
 * Purpose:
 *  - Secures all backend endpoints with JWT-based authentication.
 *  - Allows public access to specific endpoints (e.g., register, login).
 *  - Configures authentication, authorization, and password encoding.
 *
 * Key Features:
 *  - Stateless session management (no HTTP sessions, only JWT).
 *  - Integrates a custom JwtFilter to validate tokens on each request.
 *  - Configures CORS and disables CSRF (since JWT is used).
 *  - Uses BCryptPasswordEncoder for secure password hashing.
 *  - Provides DaoAuthenticationProvider with UserDetailsService for authentication.
 *
 * Endpoints:
 *  - /register → Public (new user signup)
 *  - /login    → Public (user authentication + token generation)
 *  - All other endpoints → Require valid JWT token
 *
 * Usage:
 *  - Injects JwtFilter into the security chain before UsernamePasswordAuthenticationFilter.
 *  - AuthenticationManager is exposed as a bean for use in login service.
 *  - BCrypt strength = 12 for hashing passwords securely.
 *
 * Notes:
 *  - Ensure JwtFilter is implemented correctly to parse, validate, and authenticate JWT tokens.
 *  - Stateless session policy ensures backend never stores authentication state in memory.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Defines the security filter chain for HTTP requests.
     *
     * @param http HttpSecurity configuration object
     * @return SecurityFilterChain enforcing authentication and authorization rules
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // Enable CORS
                .csrf(customizer -> customizer.disable()) // Disable CSRF (not needed for JWT)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login").permitAll() // Allow public access
                        .anyRequest().authenticated() // Protect all other endpoints
                )
//              .formLogin(Customizer.withDefaults()) // Uncomment if form login is needed
                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic auth for debugging
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .build();
    }

    /**
     * Configures the authentication provider with UserDetailsService and password encoder.
     *
     * @return AuthenticationProvider (DAO-based with BCrypt password hashing)
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * Exposes AuthenticationManager bean for login services to use during authentication.
     *
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager instance
     * @throws Exception if authentication configuration fails
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}