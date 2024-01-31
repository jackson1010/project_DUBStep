package com.dubs.core.server.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("healthReports")
@NoArgsConstructor
@AllArgsConstructor
public class HealthReport {

    @Id
    private String id;

    @Max(65535)
    @Min(0)
    private Integer userId;

    private LocalDateTime reportDateTime;
    private String patientName;

    @Min(1)
    private Integer patientAge;

    private String doctorName;

    private String reportComments;
}
