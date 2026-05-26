package com.JobTracker_Backend.backend.user.model;

import com.JobTracker_Backend.backend.jobapplication.model.JobApplication;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity class representing a User in the Job Tracker application.
 *
 * Purpose:
 *  - Maps to the "User" table in the database.
 *  - Stores information about registered users.
 *  - Establishes relationship with job applications submitted by the user.
 *
 * Fields:
 *  - userId: Primary key and unique identifier for the user.
 *  - userName: Unique username for login and identification.
 *  - userEmail: Unique email address of the user.
 *  - password: User password (8-90 characters, validated).
 *  - role: Role of the user (default is "USER").
 *  - jobApplications: List of job applications associated with this user.
 *
 * Relationships:
 *  - One-to-Many with JobApplication (mapped by "user"), with cascade and orphan removal enabled.
 *
 * Validation:
 *  - Password must be between 8 and 90 characters.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName")
})
public class User {

    /** Primary key: unique user ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /** Unique username for the user, required */
    @Column(nullable = false, unique = true)
    private String userName;

    /** Unique email of the user, required */
    @Column(unique = true, nullable = false)
    private String userEmail;

    /** User password with size constraints (8-90 characters) */
    @Column(nullable = false, length = 90)
    @Size(min = 8, max = 90, message = "Password must be between 8 and 13 characters")
    private String password;

    /** Role of the user; defaults to "USER" */
    private String role = "USER";

    /** List of job applications submitted by the user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;
}