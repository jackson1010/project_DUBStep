package com.dubs.core.server.repository;

import com.dubs.core.server.entity.HealthReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends MongoRepository<HealthReport,String> {

    List<HealthReport> findAllByUserId(Integer userId);

    Optional<HealthReport> findById(Integer reportId);

    List<HealthReport> findAllByPatientName(String patientName);

    List<HealthReport> findAllByDoctorName(String doctorName);

}
