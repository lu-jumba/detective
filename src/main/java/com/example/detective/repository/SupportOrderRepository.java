package com.example.detective.repository;

import com.example.detective.entities.SupportOrder;
import com.example.detective.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportOrderRepository extends JpaRepository<SupportOrder, String>{ 
    public SupportOrder findByReportUuid(String reportUuid); 


    @Query("SELECT s FROM SupportOrder s WHERE s.reportUuid = ?1")
    List<SupportOrder> findSupportOrderByReportUuid (SupportOrder supportOrder, String reportUuid);

    @Query("SELECT s FROM SupportOrder s WHERE s.reportUuid = ?1")
    List<SupportOrder> findSupportOrderOrderByReport(String reportUuid);

    @Query("SELECT r FROM Report r WHERE r.incidentUuid = ?1")
    Report findReportBySupportOrder(String incidentUuid);
}
