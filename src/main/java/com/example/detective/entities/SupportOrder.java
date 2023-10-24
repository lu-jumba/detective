package com.example.detective.entities;


import com.example.detective.enums.Roles;
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
        scope = SupportOrder.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "support")
@Indexed
public class SupportOrder implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "supportOrder_Id", index = Index.NO, store = Store.YES)
    private Long supportOrderId;
    
    @NotNull
    @Field(name = "report_Uuid", index = Index.NO, store = Store.YES)
    private String reportUuid;

    @NotNull
    @Field(name = "incident_Uuid", index = Index.NO, store = Store.YES)
    private String incidentUuid;
    
    
    @ManyToMany
    @JoinTable(
            name = "info",
            joinColumns = @JoinColumn(name = "support"),
            inverseJoinColumns = @JoinColumn(name = "incident")
    )
    @NotNull
    @Field(index = Index.NO, store = Store.YES)
    private Info info;;

    
   @Enumerated(EnumType.STRING)
   @Column(name = "roles")
   private Roles roles;


    @NotNull
    @Field(store = Store.YES)
    private boolean ready;

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
}
