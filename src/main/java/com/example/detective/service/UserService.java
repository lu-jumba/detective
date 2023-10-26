package com.example.detective.service;

import com.example.detective.Utility.Hashing;
//import com.example.detective.controller.OtpController;
import com.example.detective.entities.Incident;
//import com.example.detective.entities.Otp;
import com.example.detective.entities.User;
import com.example.detective.enums.Roles;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.IncidentRepository;
import com.example.detective.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;



import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
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
	

	//ModelMapper modelMapper = new ModelMapper();

    //@Autowired
	//private EmailService emailService;

    //@Autowired
	//private OtpController otpController;


    //@PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN')")
    public Response createUser(User user) throws NoSuchAlgorithmException {
        User u = userRepository.findByUsername(user.getUsername());

// Check if the user already exists
        if(u.getUsername().isEmpty()){
            
        // User does not exist, attempting creation
        user.setUserId(Long.MIN_VALUE);
        user.setUsername(u.getUsername());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setPassword(Hashing.getHashValue(u.getPassword()));
        user.setCity(u.getCity());
        user.setPhoneNumber(u.getPhoneNumber());
        user.setBalance(0);
        user.setFreedoms(0);
        user.setDate(u.getDate());
        user.setRoles(Roles.USER);
        user.setIncidents(u.getIncidents());
              

        // Return null, if user is newly created
            return new Response(0, ServiceStatus.SUCCESS);

        }
        userRepository.save(u);
        
        Map<String, String> userResponse = new HashMap<>();
	userResponse.put("username", u.getUsername());
	userResponse.put("password", u.getPassword());

       // Return the username and the password of the already existing user
        return new Response(userResponse, ServiceStatus.SUCCESS);

    }
    @PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN')")
    public Response updatePassword(String username, String password) throws NoSuchAlgorithmException{
                User newUser = userRepository.findByUsername(username);
                
                 if(newUser == null || newUser.getUsername().isEmpty()){
                     throw new Error("user not found");
                 }
                 
                 else if (!newUser.getUsername().isEmpty()) {
                
                 
                newUser.setDate(Instant.now());
                newUser.setPassword(Hashing.getHashValue(password));
                      
                User savedUser = userRepository.save(newUser);
                
                return new Response(savedUser, ServiceStatus.SUCCESS);
                 }
                 return new Response(null, ServiceStatus.ERROR);
    }

    @PreAuthorize("hasRoles('USER') or hasRoles('SUPERADMIN')")
    public Response incidents(String username, Incident i) {
        
        User user = userRepository.findByUsername(username);
        
        List<Incident> incidents = userRepository.findIncidentByUsername ((Incident) user.getIncidents());
        
        if (i == null){
            
            return null;

        }
        
        incidents.add(i);        

        return new Response(incidents, ServiceStatus.SUCCESS);
    }

    public Response authUser(String username, String password) {


        User user = userRepository.findByUsername(username);
        
        boolean authenticated = false;

        if(user.getPassword() == null){
            authenticated = false;
            throw new Error("Wrong passwod, you have two attempts left");
        }

        else {
            authenticated = user.getPassword().equals(password);

            /*if (user.getPassword().equals(password)){
                
               //create otp
                otp =  otpController.getOTP(null);

                //EmailService.sendEmail(username, "OTP for verification " + otp);

                //user.setOtp(otp);

                //User map = modelMapper.map(username, User.class);
                user.setOtp(otp); 
                //verify otp
                otpController.verifyOTP(otp);
                
                if (user.getOtp() == otp) {

                    authenticated = true;
                }
                throw new Error("OTP is Incorrect");
            }*/
        }

        return new Response(authenticated, ServiceStatus.SUCCESS);
    }
    
    
@PreAuthorize("hasRole('DETECTIVES') or hasRole('SUPERADMIN')")
public Response getUser(String username) {

    User user = userRepository.findByUsername(username);
  
    if(user.getUsername().isEmpty()){
        return new Response(null, ServiceStatus.USER_NOT_FOUND);
    }
    else if(!user.getUsername().isEmpty()){
        user.getUsername();
        user.getFirstName();
        user.getLastName();
    }
    else {
        return new Response(null, ServiceStatus.ERROR);
    }
    return new Response(user, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRole('SUPERADMIN')")
public Response users(Long userId) {
    User user = userRepository.findByUserId(userId);
        
        if (user.getUsername().isEmpty()){
            
            return null;

        }


        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();        

        return new Response(users, ServiceStatus.SUCCESS);
}

}
