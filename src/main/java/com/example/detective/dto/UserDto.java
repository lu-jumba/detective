package com.example.detective.dto;

import com.example.detective.entities.Incident;
import com.example.detective.entities.Otp;
import com.example.detective.enums.Roles;
import java.util.List;
import lombok.Getter;


@Getter
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private Otp otp;
    private String firstName;
    private String lastName;
    private String city;
    private String phoneNumber;
    private List<Incident> incidents;
    private Roles roles;
   

   public Long getUserId() {
        return userId;
    }
   
   public void setUserId(Long userId){
       this.userId = userId;
   }
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password){
       this.password = password;
   }

    public String getFirstName() {
        return firstName;
	}
    
    public void setFirstName(String firstName){
       this.firstName = firstName;
   }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName){
       this.lastName = lastName;
   }

	public String getUsername() {
        return username;
	}
        
        public void setUsername(String username){
       this.username = username;
   }

	public String getCity() {
        return city;
	}
        
        public void setCity(String city){
       this.city = city;
   }
        public String getPhoneNumer() {
        return phoneNumber;
	}
    
        
        public void setPhoneNumber(String phoneNumber){
       this.phoneNumber = phoneNumber;
   }
   public Otp getOtp() {
    return otp;
}

public void setOtp(Otp otp) {
    this.otp = otp;
}
public List<Incident> getIncidents() {
    return incidents;
}
    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }
    
     public Roles getRoles() {
        return roles;
    }
   
   public void setRoles(Roles roles){
       this.roles = roles;
   }
    
    public UserDto(Long userId, String username, String password, String firstName, String lastName, String city, 
    String phoneNumber, Otp otp, List<Incident> incidents, Roles roles) {
        this.userId = userId;    
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.otp = otp;
        this.incidents = incidents;
        this.roles = roles;
	}
    
}
