package com.example.detective.entities;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = Email.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "email")
@Indexed


public class Email implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "email_Id", index = Index.NO, store = Store.YES)
    private Long emailId;
    
    @NotNull
    @Field(store = Store.YES)
    private String subject;
    
    
    @NotNull
    @Field(store = Store.YES)
    private String message;

    @NotNull
    @Field(store = Store.YES)
    private String from;

    @NotNull
    @Field(store = Store.YES)
    private String to;
    
    public Long getEmailId() {
        return emailId;
    }
   
   public void setEmailId(Long emailId){
       this.emailId = emailId;
   }

    public String getSubject() {
        return subject;
	}
    
    public void setSubject(String subject){
       this.subject = subject;
   }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message){
       this.message = message;
   }

    public String getFrom() {
        return from;
	}
    public void setFrom(String from){
       this.from = from;
   }

    public String getTo(){
		return to;
	}
    
    public void setTo(String to){
       this.to = to;
   }
}
