package com.example.detective.entities;


import com.example.detective.enums.Roles;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


import jakarta.validation.constraints.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = User.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "users")
@Indexed

public class User implements Serializable {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Field(name = "user_Id", index = Index.NO, store = Store.YES)
    private Long userId;
    
    
    @NotNull
    @Field(store = Store.YES)
    @Column(name = "re_date")
    private Instant date;
    
    @NotNull
    @Column(name = "username", length = 30)
    @Field(store = Store.YES)
    @Pattern(regexp = "/^.*\\.*@$/", message = "Username is your email address")
    private String username;
    
    @NotNull
    @Column(length = 30)
    @Field(store = Store.YES)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name must not contain numbers or special characters")
    private String firstName;

    @NotNull
    @Column(length = 30)
    @Field(store = Store.YES)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name must not contain numbers or special characters")
    private String lastName;


    @NotNull
    @Field(store = Store.YES)
    private String city;


    @NotNull
    @Size(min = 10, max = 10, message = "Phone Number must be only 10 digits!!")
    @Field(store = Store.YES)
    private String phoneNumber;

    @NotNull
    //@Size(min = 10, max = 10, message = "Phone Number must be only 10 digits!!")
    @Field(store = Store.YES)
    private float balance;

    @NotNull
    //@Size(min = 10, max = 10, message = "Phone Number must be only 10 digits!!")
    @Field(store = Store.YES)
    private float freedoms;

    @JsonProperty(access = Access.WRITE_ONLY)
    //@Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    @Field(store = Store.YES)
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    @Column(name = "password")
    private String password;
    
    
   @Enumerated(EnumType.STRING)
   @Column(name = "roles")
   private Roles roles;

  @Field(store = Store.YES)
   @Column(name = "otp")
   private Otp otp;

   
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Incident> incidents;
    
   
    
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

   public float getBalance() {
    return balance;
}

    
    public void setBalance(float balance){
   this.balance = balance;
}

public float getFreedoms() {
    return freedoms;
}
    public void setFreedoms(float freedoms){
   this.freedoms = freedoms;
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
    
}
