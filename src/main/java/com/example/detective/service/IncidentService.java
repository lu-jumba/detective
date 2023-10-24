package com.example.detective.service;

import com.example.detective.Utility.Hashing;
import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;

import com.example.detective.entities.User;
import com.example.detective.enums.Roles;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.IncidentRepository;
import com.example.detective.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;

@Service
@Transactional
@RequiredArgsConstructor

public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;
    
    public Response createIncident(Incident i, User user) throws NoSuchAlgorithmException {
                
                // Create new user if necessary
                User u = new User();
                boolean requestUserCreate = (u.getUsername().contains("username") && u.getPassword().contains("password")); //check later
                u.getUsername();
                if (requestUserCreate) {
                    // Check if a user with the same username exists
                    if(u.getUsername().isEmpty()) {
                        // Create new user
                        user.setUserId(Long.MIN_VALUE);
                        user.setUsername(u.getUsername());
                        user.setFirstName(u.getFirstName());
                        user.setLastName(u.getLastName());
                        user.setPassword(Hashing.getHashValue(u.getPassword()));
                        user.setCity(u.getCity());
                        user.setPhoneNumber(u.getPhoneNumber());
                        user.setDate(u.getDate());
                        user.setRoles(Roles.USER);
                        user.setIncidents(u.getIncidents());
                        
                        
                        
                        // Persist the new user
                        userRepository.save(u);
                    } 
                    else{// Parse the existing user
                        u = incidentRepository.findByUsername(i.getUsername());
                    }
                } 

                else {// Validate if the user with the provided username exists
                    User existingUser = incidentRepository.findByUsername(i.getUsername());

                    if (existingUser.getUsername().isEmpty()) {
                    throw new Error("User with this username does not exist.");
                }  
                }
                
                Incident ic = new Incident ();
                
                i.setIncidentId(Long.MIN_VALUE);
                i.setUsername(ic.getUsername());
                i.setUuid(ic.getUuid());
                i.setIncidentTypeUuid(ic.getIncidentTypeUuid());
                i.setInfo(ic.getInfo());
                i.setStartDate(ic.getStartDate());
                i.setEndDate(ic.getEndDate());
                i.setVerified(false);
                i.getUsername();
                i.getUuid();

         incidentRepository.save(i);


    
        // Return success, if the new user has been created
        // (the user variable "u" should be blank)
        if (!requestUserCreate) {
            return new Response(0,ServiceStatus.SUCCESS);
        }
    
        Map<String, String> response = new HashMap<>();
        response.put("username", u.getUsername());
        response.put("password", u.getPassword());   
        return new Response(response, ServiceStatus.SUCCESS);
    
    }
    
    @PreAuthorize("hasRoles('USER') or hasRoles('SUPERADMIN')")   
    public Response incidentReports(Report report, String uuid) {
               Incident i = incidentRepository.findByUuid(uuid);
               
               List<Report> reports = incidentRepository.findReportsByIncident ((Incident) i.getReportIndex());


         if(report == null) {
         
            return new Response(null, ServiceStatus.ERROR);

         }

         reports.add(report);

         return new Response(reports, ServiceStatus.SUCCESS);

    }
    
@PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
public Response incidentUser(User user, String uuid) {
        Incident i = incidentRepository.findByUuid(uuid);
        
        User u = incidentRepository.findUserByUsername(i.getUsername());
        
        if(u.getUsername().isEmpty()) {

            return new Response(null, ServiceStatus.USER_DOES_NOT_EXIST);
    
        }
        return new Response(u, ServiceStatus.SUCCESS);

    }

    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('USER') or hasRoles('SUPERADMIN')")
    public Response listIncidents(String uuid, String username) {
        
        Incident ic = incidentRepository.findByUuid(uuid);
        
            // Fetch the reports, if the the username parameter is specified

            if (!ic.getUsername().isEmpty() && !ic.getUuid().isEmpty()) {
    
                ic.getUuid();
                ic.getUsername();

                Map<String, Report> reports;
                
                
                List<Report> rs = incidentRepository.findReportsByIncident ((Incident) ic.getReports());
                    
                ic.setReports(rs);
                ic.setReportIndex(null);  //confused
                
                
            }
            List<Incident> incidents = incidentRepository.findIncidentByUsername(ic.getUsername());
            incidents.add(ic);
            return new Response(incidents, ServiceStatus.SUCCESS);

        }
    
}
