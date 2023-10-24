package com.example.detective.controller;

import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import com.example.detective.handler.Response;
import com.example.detective.service.IncidentService;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incident")

public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @PostMapping ("/createIncident")
    public ResponseEntity createIncident(@RequestBody Incident ic, User user) throws NoSuchAlgorithmException{
        Response response = incidentService.createIncident(ic, user);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/username/uuid/listIncidents")
    public ResponseEntity listIncidents(@PathVariable("uuid") String uuid, String username){
        return new ResponseEntity(incidentService.listIncidents(uuid, username), HttpStatus.OK);
    }

    @GetMapping("/reports/uuid/incidentReports")
    public ResponseEntity incidentReports(@PathVariable("uuid") Report report, String uuid){
            return new ResponseEntity(incidentService.incidentReports(report, uuid), HttpStatus.OK);
        }

    @GetMapping("/user/uuid/incidentUser")
    public ResponseEntity incidentUser(@PathVariable("uuid") User user, String uuid){
            return new ResponseEntity(incidentService.incidentUser(user, uuid), HttpStatus.OK);
        }

    
}
