package com.JobTracker_Backend.backend.user.security;

import com.JobTracker_Backend.backend.user.model.User;
import com.JobTracker_Backend.backend.user.model.UserPrincipal;
import com.JobTracker_Backend.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class that implements Spring Security's {@link UserDetailsService}.
 *
 * Responsibilities:
 *  - Loads user-specific data for authentication.
 *  - Converts {@link User} entity into {@link UserPrincipal} for Spring Security.
 *
 * Fields:
 *  - userRepository: Repository for accessing User data from the database.
 *
 * Functionality:
 *  - Used by Spring Security to fetch user details during login.
 *  - Throws {@link UsernameNotFoundException} if the user does not exist.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /** Repository to access User entities */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by their username.
     *
     * @param username the username identifying the user whose data is required.
     * @return {@link UserDetails} representing the authenticated user
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserPrincipal(user);
    }
}