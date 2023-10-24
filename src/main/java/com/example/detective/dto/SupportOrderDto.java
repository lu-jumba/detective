package com.example.detective.dto;

import com.example.detective.entities.Info;
import com.example.detective.enums.Roles;
import lombok.Getter;


@Getter
public class SupportOrderDto {
    private Long supportOrderId;
    private String reportUuid;
    private String incidentUuid;
    private Info info;
    private boolean ready;
    private Roles roles;
    
    
    public Long getSupportOrderId() {
        return supportOrderId;
    }
        public void setSupportOrderId(Long supportOrderId) {
        this.supportOrderId = supportOrderId;
    }
        
        public String getReportUuid() {
        return reportUuid;
    }
        public void setReportUuid(String reportUuid) {
        this.reportUuid = reportUuid;
    }
         public String getIncidentUuid() {
        return incidentUuid;
    }
        public void setIncidentUuid(String incidentUuid) {
        this.incidentUuid = incidentUuid;
    }
        public Info getInfo() {
        return info;
    }
        public void setInfo(Info info) {
        this.info = info;
    }
        
        public boolean ready() {
        return ready;
    }
        public void setReady(boolean ready) {
        this.ready = ready;
    }
        public Roles  getRoles() {
        return roles;
    }
        public void setRoles(Roles roles) {
        this.roles = roles;
    }
	
public SupportOrderDto(Long supportOrderId, String reportUuid, String incidentUuid, Info info, boolean ready, Roles roles) {
    this.supportOrderId = supportOrderId;
    this.reportUuid = reportUuid;
    this.incidentUuid = incidentUuid;
    this.info = info;
    this.ready = false;
    this.roles = roles;
    }
}
