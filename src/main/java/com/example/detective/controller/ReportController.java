package com.example.detective.controller;

import com.example.detective.entities.Report;
import com.example.detective.entities.User;
import com.example.detective.enums.ReportStatus;
import com.example.detective.handler.Response;
import com.example.detective.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")

public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/case/uuid/report")
    public ResponseEntity reportIncident(@PathVariable("uuid") Report r ){
            return new ResponseEntity(reportService.reportIncident(r), HttpStatus.OK);
        }

    @GetMapping("/status/reports")
    public ResponseEntity reports(@PathVariable("status") String incidentUuid, ReportStatus status){
            return new ResponseEntity(reportService.reports(incidentUuid, status), HttpStatus.OK);
        }

    @PutMapping ("/fileReport")
    public ResponseEntity fileReport(@RequestBody User user, String incidentUuid){
        Response response = reportService.fileReport(user, incidentUuid);
    
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
        }
    
    @PutMapping ("/processReport")
    public ResponseEntity processReport(@RequestBody String caseUuid, ReportStatus status){
        Response response = reportService.processReport(caseUuid, status);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
            }
    
    @GetMapping("/caseUuid/listReportingReports")
        public ResponseEntity listReportingReports(@PathVariable("incidentUuid") String incidentUuid, ReportStatus status){
                return new ResponseEntity(reportService.listReportingReports(incidentUuid, status), HttpStatus.OK);
                }
     @GetMapping("/caseUuid/listInvestigatorReports")
        public ResponseEntity listInvestigatorReports(@PathVariable("incidentUuid") String incidentUuid, ReportStatus status){
                return new ResponseEntity(reportService.listInvestigatorReports(incidentUuid, status), HttpStatus.OK);
                }

    @PutMapping ("/contractUuid/processScamorfraudReport")
    public ResponseEntity processReportingReport(@RequestBody String incidentUuid, ReportStatus status, String fileReference){
        Response response = reportService.processReportingReport(incidentUuid, status, fileReference);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
            }

    @PutMapping ("/contractUuid/processNeedtoknowReport")
    public ResponseEntity processInvestigatorReport(@RequestBody String incidentUuid, ReportStatus status, String fileReference){
        Response response = reportService.processInvestigatorReport(incidentUuid, status, fileReference);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
            }
}
