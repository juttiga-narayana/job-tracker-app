package com.JobTracker_Backend.backend.jobapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for transferring job application details
 * between backend and frontend.
 *
 * This class ensures that only the required fields are exposed
 * instead of directly exposing the entity.
 *
 * Annotations:
 *  - @Data → Generates getters, setters, equals, hashCode, and toString.
 *  - @AllArgsConstructor → Generates a constructor with all fields.
 *  - @NoArgsConstructor → Generates a default no-argument constructor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

    /** Unique identifier for the job application */
    private Long id;

    /** Name of the company where the application was submitted */
    private String companyName;

    /** Role or job title applied for */
    private String role;

    /** Current status of the application (e.g., Applied, Interviewing, Rejected, Selected) */
    private String status;

    /**
     * Date when the application was submitted.
     *
     * @JsonFormat ensures the date is serialized and deserialized
     * in "dd-MM-yyyy" format when exchanged via JSON.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date applicationDate;
}