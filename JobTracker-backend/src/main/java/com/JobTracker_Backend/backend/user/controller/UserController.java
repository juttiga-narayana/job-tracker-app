package com.JobTracker_Backend.backend.user.controller;

import com.JobTracker_Backend.backend.user.dto.UserDto;
import com.JobTracker_Backend.backend.user.model.User;
import com.JobTracker_Backend.backend.user.repository.UserRepository;
import com.JobTracker_Backend.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling user-related HTTP requests in the Job Tracker application.
 *
 * Responsibilities:
 *  - Handles registration, login, update, deletion, and retrieval of user data.
 *  - Delegates business logic to {@link UserService}.
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a user by their ID.
     *
     * @param id User ID
     * @return ResponseEntity containing {@link UserDto} and HTTP status OK
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * Registers a new user.
     *
     * @param user User entity containing registration details
     * @return ResponseEntity with HTTP status CREATED if successful,
     *         or INTERNAL_SERVER_ERROR with error message if failed
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.register(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticates a user and performs login.
     *
     * @param user User entity containing login credentials
     * @return JWT token or login response string from {@link UserService#login(User)}
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * Updates an existing user's details.
     *
     * @param id   User ID to update
     * @param user User entity containing updated information
     * @return ResponseEntity with HTTP status OK if successful,
     *         or INTERNAL_SERVER_ERROR with error message if failed
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id User ID to delete
     * @return ResponseEntity with success message and HTTP status OK if successful,
     *         or INTERNAL_SERVER_ERROR with error message if failed
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}