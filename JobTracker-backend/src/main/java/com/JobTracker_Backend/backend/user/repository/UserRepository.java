package com.JobTracker_Backend.backend.user.repository;

import com.JobTracker_Backend.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository interface for performing CRUD operations on User entities.
 *
 * Purpose:
 *  - Provides data access methods for User entity.
 *  - Extends JpaRepository to leverage Spring Data JPA features.
 *
 * Methods:
 *  - existsByUserName(String userName): Checks if a user exists with the given username.
 *  - findByUserName(String userName): Retrieves a User entity by its username.
 *  - findByUserEmail(String userEmail): Retrieves an Optional containing a User entity by its email.
 *
 * Notes:
 *  - Spring Data JPA automatically implements these methods based on method naming conventions.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks whether a user exists with the specified username.
     * @param userName the username to check
     * @return true if a user exists with the given username, false otherwise
     */
    boolean existsByUserName(String userName);

    /**
     * Finds a user by their username.
     * @param userName the username to search for
     * @return the User entity with the specified username
     */
    User findByUserName(String userName);

    /**
     * Finds a user by their email.
     * @param userEmail the email to search for
     * @return an Optional containing the User entity if found, or empty if not found
     */
    Optional<User> findByUserEmail(String userEmail);
}