package com.JobTracker_Backend.backend.user.service;

import com.JobTracker_Backend.backend.user.dto.UserDto;
import com.JobTracker_Backend.backend.user.model.User;
import com.JobTracker_Backend.backend.user.repository.UserRepository;
import com.JobTracker_Backend.backend.user.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class for managing users in the Job Tracker application.
 *
 * Responsibilities:
 *  - Handles user registration, login, update, and deletion.
 *  - Encodes passwords using BCrypt.
 *  - Generates JWT tokens for authenticated users.
 *  - Converts User entities to UserDto for frontend consumption.
 */
@Service
public class UserService {

    /** Repository to access User entities */
    @Autowired
    private UserRepository userRepository;

    /** Service to generate and validate JWT tokens */
    @Autowired
    private JWTService jwtService;

    /** Authentication manager to authenticate users */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** BCrypt password encoder with strength 12 */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * Registers a new user in the system.
     * Encodes the password before saving.
     *
     * @param user the User entity to register
     */
    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Deletes an existing user by ID.
     *
     * @param id the ID of the user to delete
     * @throws RuntimeException if the user is not found
     */
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    /**
     * Updates an existing user's details.
     * Encodes the new password before saving.
     *
     * @param id   the ID of the user to update
     * @param user the User entity containing updated data
     * @throws RuntimeException if the user is not found
     */
    public void updateUser(long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(existingUser);
    }

    /**
     * Retrieves a user by ID and converts it to UserDto.
     *
     * @param id the ID of the user
     * @return {@link UserDto} representation of the user
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if the user is not found
     */
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        int totalApplications = user.getJobApplications() != null ? user.getJobApplications().size() : 0;

        return new UserDto(user.getUserId(), user.getUserName(), user.getUserEmail(), totalApplications);
    }

    /**
     * Authenticates a user and generates a JWT token if successful.
     *
     * @param userInput the User entity containing login credentials
     * @return JWT token as String
     * @throws ResponseStatusException with HttpStatus.UNAUTHORIZED if authentication fails
     */
    public String login(User userInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInput.getUserName(), userInput.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUserName(userInput.getUserName());
            return jwtService.generateToken(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
}