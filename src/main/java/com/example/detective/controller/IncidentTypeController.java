package com.example.detective.controller;


import com.example.detective.entities.IncidentType;
import com.example.detective.handler.Response;
import com.example.detective.service.IncidentTypeService;
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
    public ResponseEntity createIncidentType(@RequestBody IncidentType incidentType){
        Response response = incidentTypeService.createIncidentType(incidentType);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/uuid/incidentTypes")
    public ResponseEntity incidentTypes(@PathVariable("uuid") IncidentType incidentType, String uuid){
        return new ResponseEntity(incidentTypeService.incidentTypes(incidentType, uuid), HttpStatus.OK);
    }


    @PutMapping ("/setActiveIncidentType")
    public ResponseEntity setActiveIncidentType(@RequestBody String uuid, boolean active){
        Response response = incidentTypeService.setActiveIncidentType(uuid, active);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }
    
}

