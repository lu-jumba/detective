package com.example.detective.service;

import com.example.detective.Utility.Hashing;
import com.example.detective.controller.OtpController;
import com.example.detective.entities.Incident;
import com.example.detective.entities.Otp;
import com.example.detective.entities.User;
import com.example.detective.enums.Roles;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.IncidentRepository;
import com.example.detective.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;



import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


//import org.modelmapper.ModelMapper;



@Service
@Transactional
@RequiredArgsConstructor

public class UserService {
    
    private final UserRepository userRepository;
    
    private final IncidentRepository incidentRepository;


    Random random = new Random(1000);

    @Autowired
	private OtpController otpController;


    @PreAuthorize("hasRole('PARTICIPANT') or hasRole('SUPERADMIN')")
    public Response <Integer> createUser(User user) throws NoSuchAlgorithmException {

        // Validate user input
       // if (user == null || user.getUsername() == null || user.getPassword() == null) {
           // return new Response<>(null, ServiceStatus.INVALID_INPUT);
        //}

        // Check if the user already exists
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.getUsername().isEmpty()) {
            return new Response <>(null, ServiceStatus.USERNAME_ALREADY_EXIST);
        }

        //User does not exist, attempting creation
        //Set user properties and save
        
        user.setUserId(Long.MIN_VALUE);
        user.setFirstName(existingUser.getFirstName());
        user.setLastName(existingUser.getLastName());
        user.setPassword(Hashing.getHashValue(existingUser.getPassword()));
        user.setCity(existingUser.getCity());
        user.setPhoneNumber(existingUser.getPhoneNumber());
        user.setBalance(0);
        user.setFreedoms(0);
        user.setDate(Instant.now());
        user.setRoles(Roles.USER);    
        user.setIncidents(existingUser.getIncidents());

        //persist newly created user
        
        userRepository.save(user);

        //return new Response(savedUser, ServiceStatus.SUCCESS);

        // Return null, if user is newly created
        return new Response<>(0, ServiceStatus.SUCCESS);
    }
      


    @PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN')")
    public Response <Integer> updatePassword(String username, String password) throws NoSuchAlgorithmException{

        User newUser = userRepository.findByUsername(username);

        // Validate user input

        if (newUser == null || newUser.getUsername().isEmpty()) {
            return new Response<>(null, ServiceStatus.USER_NOT_FOUND);
        }

        else if (!newUser.getUsername().isEmpty()) {
            Otp otp =  otpController.getOTP(null);

            newUser.setOtp(otp); 

            if (otpController.verifyOTP(otp) != null) {

                newUser.setDate(Instant.now());
                newUser.setPassword(Hashing.getHashValue(password));

               userRepository.save(newUser);

                
                
                //return new Response("Authentication successful", ServiceStatus.SUCCESS);

            } else {
                return new Response<>(null, ServiceStatus.INVALID_OTP);
            }
        }

        return new Response<>(0, ServiceStatus.SUCCESS);

        //return new Response<>(null, ServiceStatus.ERROR);
    }

    //CHECK HERE
    @PreAuthorize("hasRoles('USER') or hasRoles('SUPERADMIN')")
    public Response <List<Incident>> incidents(String username, Incident i) {

        //User user = userRepository.findByUsername(username);
        
        List<Incident> incidents = incidentRepository.findIncidentByUsername(username);
        
        if (incidents == null){
            return new Response <> (null, ServiceStatus.INCIDENTS_NOT_FOUND);
        }

        incidents.add(i);
        
        return new Response <> (incidents, ServiceStatus.SUCCESS);
    }



    public Response <Boolean> authUser(String username, String password, Otp otp) {


        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new Response <>(null, ServiceStatus.USER_NOT_FOUND);
        }

        boolean authenticated = false;

        if (user.getPassword().equals(password)) {
            //otp =  otpController.getOTP(null);
            //user.setOtp(otp);
            authenticated = (otpController.verifyOTP(otp) != null);

        }

        if (!authenticated) {
            return new Response<>(null, ServiceStatus.AUTHENTICATION_FAILED);
        }

        return new Response<>(authenticated, ServiceStatus.SUCCESS);
    }
    
    
@PreAuthorize("hasRole('DETECTIVES') or hasRole('SUPERADMIN')")
public Response <User> getUser(String username) {

    User user = userRepository.findByUsername(username);
  
    if(user.getUsername().isEmpty()){
        return new Response<>(null, ServiceStatus.USER_NOT_FOUND);
    }
    else {
        user.getUsername();
        user.getFirstName();
        user.getLastName();
    }
    
    return new Response <>(user, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRole('SUPERADMIN')")
public Response <List<User>> users() {
    /*User user = userRepository.findAll();
        
        if (user.getUsername().isEmpty()){
            
            return new Response (null, ServiceStatus.U);

        }*/


        List<User> users = userRepository.findAll();        

        return new Response<>(users, ServiceStatus.SUCCESS);
}

}
