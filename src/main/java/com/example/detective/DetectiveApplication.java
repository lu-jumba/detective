package com.example.detective;

import com.example.detective.repository.IncidentTypeRepository;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.detective.entities.IncidentType;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;

@SpringBootApplication
public class DetectiveApplication {
	IncidentTypeRepository incidentTypeRepository;
    
    public  Response listIncidentTypes( IncidentType incidentType) {

        List<IncidentType> incidentTypes = incidentTypeRepository.findAll();
		
        
		return new Response(incidentTypes, ServiceStatus.SUCCESS);
	}

	public static void main(String[] args) {
		SpringApplication.run(DetectiveApplication.class, args);
	}

}
