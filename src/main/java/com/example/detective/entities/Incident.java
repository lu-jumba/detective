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
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
//import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = Incident.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "incident")
@Indexed
public class Incident implements Serializable {
   @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "incident_Id", index = Index.NO, store = Store.YES)
    private Long incidentId;
   
    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    @Field(name = "uuid", index = Index.NO, store = Store.YES)
    private String uuid;

    @NotNull
    @Field(index = Index.NO, store = Store.YES)
    private String incidentTypeUuid;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "username", referencedColumnName = "userId")
    @Field(index = Index.NO, store = Store.YES)
    private String username;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "incident")
    @Field(store = Store.YES)
    private Info info;

        @NotNull
        @Field(store = Store.YES)
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date startDate;
        
        @NotNull
        @Field(store = Store.YES)
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date endDate;


        @Field(store = Store.YES)
        private boolean verified;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "incident")
        @OnDelete(action = OnDeleteAction.CASCADE)
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
}
