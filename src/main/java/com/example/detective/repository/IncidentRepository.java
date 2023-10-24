package com.example.detective.repository;

import com.example.detective.entities.Report;

import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, String> { 
    public Incident findByUuid(String uuid);

    @Query("SELECT i FROM Incident i WHERE i.username = ?1")
     List<Incident> findIncidentByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
     User findUserByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT r FROM Report r WHERE r.case = ?1")
    List<Report> findReportsByIncident(Incident ic);

    @Query("SELECT i FROM I i WHERE i.incidentTypeUuid = ?1")
   Incident findByIncidentTypeUuid(String incidentTypeUuid);

 @Query("SELECT i FROM Incident i WHERE i.username = ?1")
   public Incident findUserIncidentByUsername(String username);
}
