 package com.dubs.core.server.controller;

import com.dubs.core.server.entity.HealthReport;
import com.dubs.core.server.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reports")
@Slf4j
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("save")
    public ResponseEntity<String> saveReport(@RequestBody HealthReport report){
        if(null == report){
            JsonObject response = Json.createObjectBuilder().add("Save Report Error", "Report cannot be null").build();
            return ResponseEntity.badRequest().body(response.toString());
        }
        HealthReport newReport = reportService.saveReport(report);
        JsonObject response = Json.createObjectBuilder().add("Save Report Success",newReport.getId()).build();
        return ResponseEntity.ok().body(response.toString());
    }

    /**
     * Front end should check if json array length is zero. if zero means user did not upload any reports
     * @throws JsonProcessingException
     */
    @GetMapping("/getAll/{userid}")
    public ResponseEntity<String> getAllReportsByUserId(@PathVariable Integer userid) throws JsonProcessingException {

        List<HealthReport> reports = reportService.findAllByUserId(userid);

        return ResponseEntity.ok().body(objectMapper.writeValueAsString(reports));
    }
}
