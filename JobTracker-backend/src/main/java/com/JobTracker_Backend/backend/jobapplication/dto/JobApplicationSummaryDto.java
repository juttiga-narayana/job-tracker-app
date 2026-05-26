package com.JobTracker_Backend.backend.jobapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) for summarizing job application statistics.
 *
 * This DTO is used to send both aggregate metrics (totals) and
 * detailed job application records in a single response.
 *
 * Annotations:
 *  - @Data → Generates getters, setters, equals, hashCode, and toString methods.
 *  - @AllArgsConstructor → Creates a constructor with all fields.
 *  - @NoArgsConstructor → Creates a no-argument constructor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationSummaryDto {

    /** Total number of job applications submitted */
    private int totalApplications;

    /** Total number of job offers received */
    private int totalOffers;

    /** Total number of interview opportunities received */
    private int totalInterviews;

    /** List of individual job applications with their details */
    private List<JobApplicationDto> applications;
}