package com.example.detective.controller;

import com.example.detective.entities.Incident;
import com.example.detective.entities.Report;
import com.example.detective.entities.User;
import com.example.detective.enums.ReportStatus;
import com.example.detective.handler.Response;
import com.example.detective.service.ReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")

public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/incident/uuid/report")
    public ResponseEntity <Response <Incident>> reportIncident(
        @PathVariable("uuid") 
        @RequestParam Report r ){
            Response <Incident> response = reportService.reportIncident(r);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @GetMapping("/status/reports")
    public ResponseEntity <Response <List<Report>>> reports(
        @PathVariable("status") ReportStatus status,  
        @RequestParam String incidentUuid ){
            Response <List<Report>> response = reportService.reports(incidentUuid, status);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @PutMapping ("/fileReport")
    public ResponseEntity <Response <Integer>> fileReport(@RequestBody User user, String incidentUuid){
        Response  <Integer> response = reportService.fileReport(user, incidentUuid);
    
       return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
        }

    
    @PutMapping ("/processReport")
    public ResponseEntity <Response <Report>> processReport(@RequestBody String incidentUuid, ReportStatus status){
        Response <Report> response = reportService.processReport(incidentUuid, status);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
            }
    
    @GetMapping("/incidentUuid/listReportingReports")
        public ResponseEntity <Response <List<Report>>> listReportingReports(
            @PathVariable("incidentUuid") String incidentUuid, 
            @RequestParam ReportStatus status){
                Response <List<Report>> response = reportService.listReportingReports(incidentUuid, status);
                return new ResponseEntity<>(response, HttpStatus.OK);
                }

     @GetMapping("/incidentUuid/listInvestigatorReports")
        public ResponseEntity <Response <List<Report>>> listInvestigatorReports(
            @PathVariable("incidentUuid") String incidentUuid, 
            @RequestParam ReportStatus status){
                Response <List<Report>> response = reportService.listInvestigatorReports(incidentUuid, status);
                return new ResponseEntity<>(response, HttpStatus.OK);
                }

    @PutMapping ("/incidentUuid/processScamorfraudReport")
    public ResponseEntity <Response <Report>> processReportingReport(@RequestBody String incidentUuid, ReportStatus status, String fileReference){
        Response <Report> response = reportService.processReportingReport(incidentUuid, status, fileReference);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
            }

    @PutMapping ("/incidentUuid/processNeedtoknowReport")
    public ResponseEntity <Response <Report>> processInvestigatorReport(@RequestBody String incidentUuid, ReportStatus status, String fileReference){
        Response <Report> response = reportService.processInvestigatorReport(incidentUuid, status, fileReference);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
            }
}
