package com.JobTracker_Backend.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object (DTO) representing a User in the Job Tracker application.
 *
 * Purpose:
 *  - Encapsulates user-related data for transfer between layers (controller, service, etc.).
 *  - Avoids exposing entity internals directly to clients.
 *
 * Fields:
 *  - userId: Unique identifier of the user.
 *  - userName: Full name of the user.
 *  - userEmail: Email address of the user.
 *  - totalApplications: Total number of job applications submitted by the user.
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /** Unique user ID */
    public long userId;

    /** Full name of the user */
    private String userName;

    /** Email address of the user */
    private String userEmail;

    /** Total number of job applications submitted by the user */
    private int totalApplications;
}