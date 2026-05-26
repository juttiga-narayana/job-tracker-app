//package com.JobTracker_Backend.backend.user.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private  JWTService jwtService;
//
//    @Autowired
//    ApplicationContext context;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            try {
//                username = jwtService.extractUsername(token);
//            } catch (Exception e) {
//                System.out.println("Invalid JWT token");
//            }
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
//            if (jwtService.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}


package com.JobTracker_Backend.backend.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT filter for authenticating HTTP requests in the Job Tracker application.
 *
 * Purpose:
 *  - Intercepts incoming HTTP requests.
 *  - Extracts JWT tokens from the "Authorization" header.
 *  - Validates JWT tokens and sets the authentication in the security context.
 *
 * Fields:
 *  - jwtService: Handles extracting username and validating JWT tokens.
 *  - context: Spring ApplicationContext to get UserDetailsService beans.
 *
 * Functionality:
 *  - Checks if the Authorization header contains a Bearer token.
 *  - Extracts the username from the token using jwtService.
 *  - Loads user details from MyUserDetailsService.
 *  - Validates the token and sets a UsernamePasswordAuthenticationToken in SecurityContextHolder.
 *  - Continues the filter chain after processing.
 *
 * Notes:
 *  - Extends OncePerRequestFilter to ensure it executes once per request.
 *  - Supports stateless JWT authentication for secured endpoints.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    /** Service for JWT operations: extracting username and validating token */
    @Autowired
    private JWTService jwtService;

    /** Spring application context to obtain beans like MyUserDetailsService */
    @Autowired
    ApplicationContext context;

    /**
     * Filters incoming HTTP requests for JWT authentication.
     *
     * @param request HTTP servlet request
     * @param response HTTP servlet response
     * @param filterChain Filter chain for further processing
     * @throws ServletException in case of servlet errors
     * @throws IOException in case of I/O errors
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(MyUserDetailsService.class)
                    .loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}