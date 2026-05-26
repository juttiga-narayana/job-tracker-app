package com.JobTracker_Backend.backend.jobapplication.service;

import com.JobTracker_Backend.backend.jobapplication.dto.JobApplicationDto;
import com.JobTracker_Backend.backend.jobapplication.dto.JobApplicationSummaryDto;
import com.JobTracker_Backend.backend.jobapplication.model.JobApplication;
import com.JobTracker_Backend.backend.jobapplication.repository.JobApplicationRepository;
import com.JobTracker_Backend.backend.user.model.User;
import com.JobTracker_Backend.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing job applications in the Job Tracker application.
 *
 * Responsibilities:
 *  - Handles creation, update, deletion, and retrieval of job applications.
 *  - Converts JobApplication entities to DTOs for frontend consumption.
 *  - Provides summary statistics for user's job applications.
 */
@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a summary of all job applications for a specific user.
     *
     * @param userId ID of the user
     * @return {@link JobApplicationSummaryDto} containing total applications, total offers,
     * total interviews, and the list of applications as DTOs
     */
    public JobApplicationSummaryDto getApplicationsSummaryByUserId(Long userId) {
        List<JobApplication> applications = jobApplicationRepository.findByUserUserId(userId);

        List<JobApplicationDto> dtos = applications.stream()
                .map(app -> new JobApplicationDto(
                        app.getId(),
                        app.getCompanyName(),
                        app.getRole(),
                        app.getStatus(),
                        app.getApplicationDate()
                ))
                .collect(Collectors.toList());

        int totalApplications = (int) jobApplicationRepository.countByUserUserId(userId);
        int totalOffers = (int) jobApplicationRepository.countByUserUserIdAndStatusIgnoreCase(userId, "Offer");
        int totalInterviews = (int) jobApplicationRepository.countByUserUserIdAndStatusIgnoreCase(userId, "Interview");

        return new JobApplicationSummaryDto(totalApplications, totalOffers, totalInterviews, dtos);
    }

    /**
     * Creates a new job application for a specific user.
     *
     * @param userId ID of the user
     * @param jobApplication JobApplication entity to be created
     * @throws RuntimeException if the user is not found
     */
    public void createJobApplication(Long userId, JobApplication jobApplication) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        jobApplication.setUser(user);
        jobApplicationRepository.save(jobApplication);
    }

    /**
     * Deletes an existing job application by its ID.
     *
     * @param applicationId ID of the job application to delete
     * @throws RuntimeException if the job application is not found
     */
    public void deleteJobApplication(Long applicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));
        jobApplicationRepository.delete(jobApplication);
    }

    /**
     * Updates an existing job application with new details.
     *
     * @param applicationId ID of the job application to update
     * @param jobApplication JobApplication entity containing updated data
     * @throws RuntimeException if the job application is not found
     */
    public void updateJobApplication(Long applicationId, JobApplication jobApplication) {
        JobApplication existingApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        existingApplication.setCompanyName(jobApplication.getCompanyName());
        existingApplication.setRole(jobApplication.getRole());
        existingApplication.setStatus(jobApplication.getStatus());
        existingApplication.setApplicationDate(jobApplication.getApplicationDate());

        jobApplicationRepository.save(existingApplication);
    }

    /**
     * Retrieves a job application by its ID.
     *
     * @param applicationId ID of the job application
     * @return {@link JobApplicationDto} representation of the job application
     * @throws ResponseStatusException with HttpStatus.NOT_FOUND if the job application does not exist
     */
    public JobApplicationDto getApplicationById(Long applicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new JobApplicationDto(
                jobApplication.getId(),
                jobApplication.getCompanyName(),
                jobApplication.getRole(),
                jobApplication.getStatus(),
                jobApplication.getApplicationDate()
        );
    }
}