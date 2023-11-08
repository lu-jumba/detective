package com.example.detective.repository;

import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;
import com.example.detective.enums.ReportStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {  
    
    public Report findByIncidentUuid(String caseUuid);
    
    @Query("SELECT r FROM Report r WHERE r.status = ?1")
    public Report findByStatus(ReportStatus status);


   @Query("SELECT i FROM Incident i WHERE i.uuid = ?1")
   public Incident findByUuid(String uuid);
   
   @Query("SELECT i FROM Incident i WHERE i.incidentTypeUuid = ?1")
   public Incident findByIncidentTypeUuid (String incidentTypeUuid);

    @Query("SELECT r FROM Report r WHERE r.username = ?1")
    public List<Report> findReportByUsername (String username);

    @Query("SELECT r FROM Report r WHERE r.isReporting = ?1")
    public List<Report> findReportByReporting (Report isReporting, Sort by);

    @Query("SELECT r FROM Report r WHERE r.isInvestigator = ?1")
    public List<Report> findReportByInvestigator (Report isInvestigator, Sort by);
    
}
