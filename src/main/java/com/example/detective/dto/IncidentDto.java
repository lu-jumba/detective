package com.example.detective.dto;

import com.example.detective.entities.Report;
import com.example.detective.entities.Info;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter

public class IncidentDto {
    private Long incidentId;
    private String username;
    private Info info;
    private Date startDate;
    private Date endDate;
    private String incidentTypeUuid;
    private String uuid;
    private boolean verified;
    private List<Report> reportIndex;
    

public Long getIncidentId() {
        return incidentId;
    }
    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Info getInfo() {
        return info;
    }
    
    public void setInfo(Info info) {
        this.info = info;
    } 

     public String getIncidentTypeUuid() {
            return incidentTypeUuid;
        }
    
    public void setIncidentTypeUuid(String incidentTypeUuid) {
        this.incidentTypeUuid = incidentTypeUuid;
    } 

    public String getUuid() {
            return uuid;
        }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    } 
    public Date getStartDate() {
            return startDate;
        }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    } 

    public Date getEndDate() {
            return endDate;
        }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    } 

    public  boolean  verified() {
        return verified;
        }
    
    public void setVerified(boolean  verified) {
        this.verified = verified;
        }
    
    public List<Report> getReports() {
    return reportIndex;
    }
   

    public void setReports(List<Report> reportIndex) {
this.reportIndex = reportIndex;    
    }


        
	
public IncidentDto (Long incidentId, String username, Info info, Date startDate, Date endDate, 
String incidentTypeUuid, String uuid, boolean verified, List<Report> reportIndex) {
    this.incidentId = incidentId;
    this.username = username;
    this.info = info;
    this.startDate = startDate;
    this.endDate = endDate;
    this.uuid = uuid;
    this.incidentTypeUuid = incidentTypeUuid;
    this.verified = false;
    this.reportIndex = reportIndex;  //unsure
    
    }

    
}
