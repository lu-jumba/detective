package com.example.detective.dto;

import java.util.Date;

import com.example.detective.enums.ReportStatus;

import lombok.Getter;


@Getter
public class ReportDto {
    private Long reportId;
    private String incidentUuid;
    private Date date;
    private String description;
    private boolean isReporting;
    private boolean isInvestigator;
    private ReportStatus status;
    private float reportable;
    private boolean supported;
    private float verifiable;
    private String fileReference;
    
    
    public  Long getReportId() {
        return reportId;
        }
    
    public void setReportId(Long reportId) {
        this.reportId = reportId;
        }
    
    public  String getIncidentUuid() {
        return incidentUuid;
        }
    
    public void setIncidentUuid(String incidentUuid) {
        this.incidentUuid = incidentUuid;
        }
    
    public  Date getDate() {
        return date;
        }
    
    public void setDate(Date date) {
        this.date = date;
        }
    
    public  String  getDescription() {
        return description;
        }
    
    public void setDescription(String description) {
        this.description = description;
        
        }
    
    public  boolean  isReporting() {
        return isReporting;
        }
    
    public void setReporting(boolean  isReporting) {
        this.isReporting = isReporting;
        }

        public  boolean  isInvestigator() {
        return isInvestigator;
        }
    
    public void setInvestigator(boolean  isInvestigator) {
        this.isInvestigator = isInvestigator;
        }

        
    
    
    public  ReportStatus getStatus() {
        return status;
        }
    
    public void setStatus(ReportStatus   status) {
        this.status = status;
        }
     public  float getReportable() {
        return reportable;
        }
    
    public void setReportable(float reportable) {
        this.reportable = reportable;
        }
    

        public  float getVerifiable() {
        return verifiable;
        }
    
    public void setVerifiable(float verifiable) {
        this.verifiable = verifiable;
        }
    
    public  boolean  supported() {
        return supported;
        }
        
        public void setSupported(boolean supported) {
        this.supported = supported;
    }

        public  String  getFileReference() {
        return fileReference;
        }
        
        public void setFileReference(String  fileReference) {
        this.fileReference = fileReference;
    }
	
public ReportDto(Long reportId, String incidentUuid, Date date, String description, ReportStatus status, 
float verifiable, float reportable, boolean isReporting, boolean isInvestigator, boolean verified, 
boolean supported, String fileReference) {
    this.reportId = reportId;
    this.incidentUuid = incidentUuid;
    this.date = date;
    this.description = description;
    this.isReporting = false;
    this.isInvestigator = false;
    this.status = status;
    this.reportable = reportable;
    this.verifiable = verifiable;
    this.supported = false;
    this.fileReference = fileReference;      
    
	}
    
}
