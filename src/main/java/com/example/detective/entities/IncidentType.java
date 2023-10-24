package com.example.detective.entities;


import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
//import org.hibernate.annotations.UuidGenerator;


@Entity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = IncidentType.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "incidentType")
@Indexed
public class IncidentType implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "incidentType_Id", index = Index.NO, store = Store.YES)
    private Long incidentTypeId;
    
    
    @NotNull
    @Field(name = "uuid", index = Index.NO, store = Store.YES)
    private String uuid;


    @Field(index = Index.NO, store = Store.YES)
    private String serviceType;

    @Field(index = Index.NO, store = Store.YES)
    private String formulaPerIncident;
    
    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String description;

    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String conditions;    

    @Field(index = Index.NO, store = Store.YES)
    private boolean active;

    @Field(index = Index.NO, store = Store.YES)
    private int minDurationDays;

    @Field(index = Index.NO, store = Store.YES)
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
}
    