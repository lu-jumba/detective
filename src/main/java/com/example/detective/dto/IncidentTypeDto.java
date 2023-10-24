package com.example.detective.dto;

import lombok.Getter;

@Getter
public class IncidentTypeDto {
    private Long incidentTypeId;
    private String serviceType;
    private String formulaPerIncident;
    private String description;
    private String conditions;
    private String uuid;
    private boolean active;
    private int minDurationDays;
    private int maxDurationDays;
    
    
    public Long getIncidentTypeId() {
        return incidentTypeId;
        }
    
    public void setIncidentTypeId(Long incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
        }
    
    public String getServiceType() {
        return serviceType;
        }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
        }
    
    public String getFormulaPerIncident() {
        return  formulaPerIncident;
        }
    
    public void setFormulaPerIncident(String formulaPerIncident) {
            this.formulaPerIncident = formulaPerIncident;
        }
        public String getDescription() {
            return description;
                }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getConditions() {
            return conditions;
                }
        public void setConditions(String conditions) {
            this.conditions = conditions;
        }
        
        public String getUuid() {
            return uuid;
                }
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
	
        public boolean active() {
            return active;
                }
        public void setActive(boolean active) {
            this.active = active;
        }
        
        public int getMinDurationDays() {
            return minDurationDays;
                }
        public void setMinDurationDays(int minDurationDays) {
            this.minDurationDays = minDurationDays;
        }
        
        public int getMaxDurationDays() {
            return maxDurationDays;
                }
        public void setMaxDurationDays(int maxDurationDays) {
            this.maxDurationDays = maxDurationDays;
        }
        
public IncidentTypeDto(Long incidentTypeId, String serviceType, String formulaPerIncident, String description, 
                      String conditions, String uuid, boolean active, int minDurationDays, int maxDurationDays) {
    this.incidentTypeId = incidentTypeId;
    this.serviceType = serviceType;
    this.formulaPerIncident = formulaPerIncident;
    this.description = description;
    this.conditions = conditions;
    this.uuid = uuid;
    this.active = false;
    this.minDurationDays = minDurationDays;
    this.maxDurationDays = maxDurationDays;
    
    }
}
