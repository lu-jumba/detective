package com.example.detective.controller;



import com.example.detective.entities.IncidentType;
import com.example.detective.handler.Response;
import com.example.detective.service.IncidentTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidentType")
public class IncidentTypeController {  

    @Autowired
    IncidentTypeService incidentTypeService;

    @PostMapping ("/createIncidentType")
    public ResponseEntity <Response <IncidentType>> createIncidentType(@RequestBody IncidentType incidentType){
        Response <IncidentType> response = incidentTypeService.createIncidentType(incidentType);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/uuid/incidentTypes")
    public ResponseEntity <Response<List<IncidentType>>> incidentTypes(@PathVariable("uuid") String uuid,
     @RequestParam IncidentType incidentType){
        Response<List<IncidentType>> response = incidentTypeService.incidentTypes(uuid, incidentType);
            return new ResponseEntity<>(response, HttpStatus.OK);
        
    }


    @PutMapping ("/setActiveIncidentType")
    public ResponseEntity <Response<IncidentType>> setActiveIncidentType(@RequestBody String uuid, boolean active){
       
        Response<IncidentType> response = incidentTypeService.setActiveIncidentType(uuid, active);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }
    
}

