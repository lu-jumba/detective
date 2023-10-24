package com.example.detective.entities;

import com.example.detective.enums.ReportStatus;
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
import java.util.Date;
import lombok.Data;
//import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = Report.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "report")
@Indexed

public class Report implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "report_Id", index = Index.NO, store = Store.YES)
    private Long reportId;
    
   @ManyToOne
    @NotNull
    @JoinColumn(name = "incident", referencedColumnName = "uuid")
    @Field(name = "incident_Uuid", index = Index.NO, store = Store.YES)
    private String incidentUuid;
    
    @NotNull
    @Field(index = Index.NO, store = Store.YES)
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "report_Date")
    private Date date;

    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String description;


    @NotNull
    @Field(store = Store.YES)
    private boolean isReporting;

    @NotNull
    @Field(store = Store.YES)
    private boolean isInvestigator;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;

    @NotNull
    @Field(store = Store.YES)
    private float reportable;

    @NotNull
    @Field(store = Store.YES)
    private float verifiable;

    @NotNull
    @Field(store = Store.YES)
    private boolean supported;

    @NotNull
    @Field(index = Index.NO, store = Store.YES)
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
}
