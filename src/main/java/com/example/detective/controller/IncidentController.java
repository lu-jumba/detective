package com.example.detective.controller;

import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import com.example.detective.handler.Response;
import com.example.detective.service.IncidentService;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    public ResponseEntity <Response<Integer>> createIncident(@RequestBody Incident ic, User user) throws NoSuchAlgorithmException{
        Response <Integer> response = incidentService.createIncident(ic, user);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    

     @GetMapping("/username/uuid/listIncidents")
        public ResponseEntity<Response<List<Incident>>> listIncidents(
                @PathVariable("uuid") String uuid,
                @RequestParam String username) {
            Response<List<Incident>> response = incidentService.listIncidents(uuid, username);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    
        @GetMapping("/reports/{uuid}/incidentReports")
        public ResponseEntity<Response<List<Report>>> incidentReports(
                @PathVariable("uuid") String uuid,
                @RequestParam Report report) {
            Response<List<Report>> response = incidentService.incidentReports(report, uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

   
    
    @GetMapping("/user/uuid/incidentUser")
        public ResponseEntity<Response<User>> incidentUser(
                @PathVariable("uuid") String uuid,
                @RequestParam User user) {
            Response<User> response = incidentService.incidentUser(user, uuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    
}
