package com.dubs.core.server.service;

import com.dubs.core.server.entity.HealthReport;
import com.dubs.core.server.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportService {

    @Autowired
    ReportRepository reportRepository;


    public void saveReport(HealthReport report){
        //should map from a DTO?
        reportRepository.save(report);
    }

    public List<HealthReport> findAllByUserId(Integer userId){
        return reportRepository.findAllByUserId(userId);
    }

    public Optional<HealthReport> findById(Integer reportId){
        return reportRepository.findById(reportId);
    }

    public List<HealthReport> findAllByPatientName(String patientName){
        return reportRepository.findAllByPatientName(patientName);
    }

    public List<HealthReport> findAllByDoctorName(String doctorName){
        return reportRepository.findAllByDoctorName(doctorName);
    }




}
