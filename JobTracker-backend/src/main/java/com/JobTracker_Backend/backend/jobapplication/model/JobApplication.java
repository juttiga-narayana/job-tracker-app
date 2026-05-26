package com.JobTracker_Backend.backend.jobapplication.model;

import com.JobTracker_Backend.backend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity class representing a job application.
 *
 * This class maps to the "job_application" table in the database (by default).
 * It stores details of a job application submitted by a user such as role,
 * company name, date of application, and its current status.
 *
 * Annotations:
 *  - @Entity → Marks this class as a JPA entity.
 *  - @Data → Generates getters, setters, equals, hashCode, and toString methods.
 *  - @AllArgsConstructor → Generates a constructor with all fields.
 *  - @NoArgsConstructor → Generates a default no-argument constructor.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {

    /** Unique identifier for the job application (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Role or job title applied for */
    private String role;

    /** Name of the company where the application is submitted */
    private String companyName;

    /** Date when the application was submitted */
    private Date applicationDate;

    /** Current status of the application (e.g., Applied, Interview, Offer, Rejected) */
    private String status;

    /**
     * Many job applications can belong to a single user.
     * Establishes a foreign key relationship with the {@link User} entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}