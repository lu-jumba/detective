package com.example.detective.dto;

import lombok.Getter;


@Getter
public class EmailDto {
    private Long emailId;
	private String subject;
    private String message;
	private String from;
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
    
	
	public EmailDto (Long emailId, String subject, String message, String from, String to) {
		this.emailId = emailId;
		this.subject = subject;
		this.message = message;
		this.from = from;
		this.to = to;
	}

}
