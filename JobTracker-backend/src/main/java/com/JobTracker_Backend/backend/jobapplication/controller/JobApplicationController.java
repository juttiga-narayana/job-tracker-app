package com.JobTracker_Backend.backend.jobapplication.controller;

import com.JobTracker_Backend.backend.jobapplication.dto.JobApplicationDto;
import com.JobTracker_Backend.backend.jobapplication.dto.JobApplicationSummaryDto;
import com.JobTracker_Backend.backend.jobapplication.model.JobApplication;
import com.JobTracker_Backend.backend.jobapplication.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing job applications.
 *
 * Provides endpoints for:
 * - Fetching job applications by ID
 * - Fetching all job applications for a user
 * - Creating new job applications
 * - Updating existing job applications
 * - Deleting job applications
 *
 * All endpoints return appropriate HTTP status codes
 * to indicate success or failure.
 */
@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * Retrieves a job application by its ID.
     *
     * @param applicationId the ID of the job application
     * @return ResponseEntity with JobApplicationDto and HTTP 200 if found,
     *         otherwise 404
     */
    @GetMapping("/applications/{applicationId}")
    public ResponseEntity<JobApplicationDto> getApplicationById(@PathVariable Long applicationId) {
        JobApplicationDto jobApplicationDto = jobApplicationService.getApplicationById(applicationId);
        return new ResponseEntity<>(jobApplicationDto, HttpStatus.OK);
    }

    /**
     * Retrieves all job applications submitted by a specific user.
     *
     * @param userId the ID of the user
     * @return ResponseEntity with JobApplicationSummaryDto and HTTP 200 if applications exist,
     *         otherwise 404 if no applications found
     */
    @GetMapping("/users/{userId}/applications")
    public ResponseEntity<JobApplicationSummaryDto> getApplicationsByUser(@PathVariable Long userId) {
        JobApplicationSummaryDto summary = jobApplicationService.getApplicationsSummaryByUserId(userId);

        if (summary.getApplications().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    /**
     * Creates a new job application for a specific user.
     *
     * @param userId the ID of the user
     * @param jobApplication the job application details (from request body)
     * @return ResponseEntity with HTTP 201 if created successfully,
     *         otherwise 400 with error message
     */
    @PostMapping("/users/{userId}/applications")
    public ResponseEntity<?> createJobApplication(@PathVariable Long userId, @RequestBody JobApplication jobApplication) {
        try {
            jobApplicationService.createJobApplication(userId, jobApplication);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing job application.
     *
     * @param applicationId the ID of the job application to update
     * @param jobApplication the updated job application details (from request body)
     * @return ResponseEntity with HTTP 200 if updated successfully,
     *         otherwise 400 with error message
     */
    @PutMapping("/applications/{applicationId}")
    public ResponseEntity<?> updateJobApplication(@PathVariable Long applicationId, @RequestBody JobApplication jobApplication) {
        try {
            jobApplicationService.updateJobApplication(applicationId, jobApplication);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a job application by its ID.
     *
     * @param applicationId the ID of the job application to delete
     * @return ResponseEntity with HTTP 204 if deleted successfully,
     *         otherwise 400 with error message
     */
    @DeleteMapping("/applications/{applicationId}")
    public ResponseEntity<?> deleteJobApplication(@PathVariable Long applicationId) {
        try {
            jobApplicationService.deleteJobApplication(applicationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}