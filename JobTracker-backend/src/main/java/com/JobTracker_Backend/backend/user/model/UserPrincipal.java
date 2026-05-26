package com.JobTracker_Backend.backend.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * UserPrincipal class implementing Spring Security's UserDetails interface.
 *
 * Purpose:
 *  - Acts as a security principal for authentication and authorization.
 *  - Wraps a User entity and provides Spring Security with necessary user details.
 *
 * Responsibilities:
 *  - Provide authorities granted to the user (role-based).
 *  - Expose username and password for authentication.
 *  - Indicate account status (non-expired, non-locked, credentials valid, enabled).
 *
 * Fields:
 *  - user: The User entity being wrapped.
 *
 * Methods:
 *  - getAuthorities(): Returns the roles/authorities of the user.
 *  - getPassword(): Returns the user's password.
 *  - getUsername(): Returns the user's username.
 *  - isAccountNonExpired(): Indicates whether the account has not expired.
 *  - isAccountNonLocked(): Indicates whether the account is not locked.
 *  - isCredentialsNonExpired(): Indicates whether credentials are valid.
 *  - isEnabled(): Indicates whether the account is enabled.
 */
public class UserPrincipal implements UserDetails {

    /** Wrapped User entity */
    private User user;

    /**
     * Constructs a UserPrincipal with the given User entity.
     * @param user the User entity to wrap
     */
    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Returns a collection of authorities granted to the user.
     * @return collection containing a single "USER" authority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Returns the user's password.
     * @return user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the user's username.
     * @return user's username
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true (account is non-expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * @return true (account is not locked)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return true (credentials are valid)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * @return true (user is enabled)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}