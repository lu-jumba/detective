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
    
    public Response <Integer> createIncident(Incident i, User user) throws NoSuchAlgorithmException {

        if (requestUserCreate(user)) {
            // Create or retrieve the user
            User existingUser = createUserIfNotExists(user);
            i.setUsername(existingUser.getUsername());
        } else {
            // Validate if the user with the provided username exists
            User existingUser = userRepository.findByUsername(i.getUsername());
            if (existingUser == null) {
                return new Response <> (null, ServiceStatus.USER_DOES_NOT_EXIST);
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


        // Save the incident
        incidentRepository.save(i);

        if (requestUserCreate(user)) {
            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("password", user.getPassword());
            //return new Response<>(response, ServiceStatus.SUCCESS);
        } /*else {
            return new Response(0, ServiceStatus.SUCCESS);
        }*/
       return new Response<>(0, ServiceStatus.SUCCESS);
    }

    private boolean requestUserCreate(User user) {
        return user.getUsername().contains("username") && user.getPassword().contains("password");
    }

    private User createUserIfNotExists(User user) throws NoSuchAlgorithmException {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.getUsername().isEmpty()) {
            user.setUserId(Long.MIN_VALUE);
                        user.setUsername(existingUser.getUsername());
                        user.setFirstName(existingUser.getFirstName());
                        user.setLastName(existingUser.getLastName());
                        user.setPassword(Hashing.getHashValue(existingUser.getPassword()));
                        user.setCity(existingUser.getCity());
                        user.setPhoneNumber(existingUser.getPhoneNumber());
                        user.setDate(existingUser.getDate());
                        user.setRoles(Roles.USER);
                        user.setIncidents(existingUser.getIncidents());
            return userRepository.save(user);
        }
        return existingUser;
    }


    
    @PreAuthorize("hasRoles('USER') or hasRoles('SUPERADMIN')")   
    public  Response <List<Report>> incidentReports(Report report, String uuid) {
               Incident i = incidentRepository.findByUuid(uuid);
               
               List<Report> reports = incidentRepository.findReportsByIncident (i.getReportIndex());


         if(report == null) {
         
            return new Response<>(null, ServiceStatus.ERROR);
         }

         reports.add(report);

         return new Response <> (reports, ServiceStatus.SUCCESS);

    }

    
@PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
public Response <User> incidentUser(User user, String uuid) {
        Incident i = incidentRepository.findByUuid(uuid);
        
        User u = incidentRepository.findUserByUsername(i.getUsername());
        
        if(u.getUsername().isEmpty()) {

            return new Response<>(null, ServiceStatus.USER_DOES_NOT_EXIST);
    
        }
        return new Response<>(u, ServiceStatus.SUCCESS);

    }

    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('USER') or hasRoles('SUPERADMIN')")
    public Response <List<Incident>> listIncidents(String uuid, String username) {
        
        Incident ic = incidentRepository.findByUuid(uuid);

        // Fetch the reports, if the the username parameter is specified

        if (!ic.getUsername().isEmpty() && !ic.getUuid().isEmpty()) {
            /*ic.getUuid();
            ic.getUsername();*/

            //Map<String, Report> reports;

            // Fetch reports related to the incident

            List<Report> rs = incidentRepository.findReportsByIncident (ic.getReports());

            ic.setReports(rs);

            ic.setReportIndex(null);  //confused

            }

            List<Incident> incidents = incidentRepository.findIncidentByUsername(ic.getUsername());

            incidents.add(ic);

            return new Response <>(incidents, ServiceStatus.SUCCESS);

        }
    
}
