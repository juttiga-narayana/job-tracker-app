package com.JobTracker_Backend.backend.jobapplication.repository;

import com.JobTracker_Backend.backend.jobapplication.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link JobApplication} entities.
 *
 * This interface extends {@link JpaRepository}, which provides basic CRUD operations
 * and query execution for the JobApplication entity. It also includes custom query
 * methods defined using Spring Data JPA's method naming conventions.
 *
 * Annotations:
 *  - @Repository → Indicates that this interface is a Spring Data Repository
 *                  and eligible for Spring’s exception translation.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    /**
     * Retrieves a list of job applications belonging to a specific user.
     *
     * @param userId ID of the user
     * @return list of job applications for the given user
     */
    List<JobApplication> findByUserUserId(Long userId);

    /**
     * Counts the total number of job applications submitted by a specific user.
     *
     * @param userId ID of the user
     * @return number of job applications
     */
    long countByUserUserId(Long userId);

    /**
     * Counts the number of job applications for a specific user
     * that match the given status (case-insensitive).
     *
     * Example statuses: "Applied", "Interview", "Offer", "Rejected".
     *
     * @param userId ID of the user
     * @param status application status to filter
     * @return number of applications with the given status
     */
    long countByUserUserIdAndStatusIgnoreCase(Long userId, String status);

}