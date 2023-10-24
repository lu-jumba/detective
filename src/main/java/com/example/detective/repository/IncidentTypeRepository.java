package com.example.detective.repository;


import com.example.detective.entities.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidentTypeRepository extends JpaRepository<IncidentType, String>{ 
    public IncidentType findByUuid(String uuid);
    

    @Query("SELECT it FROM IncidentType it WHERE it.uuid = ?1")
    List<IncidentType> findActiveIncidentTypes(String uuid);
    
    @Query("SELECT it FROM IncidentType it WHERE it.uuid = ?1")
    IncidentType findByIncidentTypeUuid(String uuid);



} 