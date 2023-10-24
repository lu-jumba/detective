package com.example.detective.service;

import com.example.detective.entities.SupportOrder;
import com.example.detective.entities.Report;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.SupportOrderRepository;
import com.example.detective.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize; 

@Service
@Transactional
@RequiredArgsConstructor
public class SupportOrderService {
    private final SupportOrderRepository supportOrderRepository;
    private final ReportRepository reportRepository;

    @PreAuthorize("hasRoles('SUPPORT') or hasRoles('SUPERADMIN')")
    public Response listSupportOrders(String reportUuid){
        SupportOrder sOrder = supportOrderRepository.findByReportUuid(reportUuid);

        if(!sOrder.getReportUuid().isEmpty()){
            
            List<SupportOrder> supportOrders = supportOrderRepository.findAll();
            return new Response(supportOrders , ServiceStatus.SUCCESS);
            
        }
        return null;
        
    }

    @PreAuthorize("hasRoles('SUPPORT') or hasRoles('SUPERADMIN')")
    public Response completeSupportOrder(String reportUuid){
        SupportOrder sOrder = supportOrderRepository.findByReportUuid(reportUuid);

        if(sOrder.getReportUuid() == null){
            throw new Error("Could not find the Support Order");
        }

        else {
                        // Marking Request  as ready

            sOrder.setReady(true);          
          
            
             supportOrderRepository.save(sOrder);
        }
    
        // Reflect changes in the corresponding report
        
        Report report = supportOrderRepository.findReportBySupportOrder(sOrder.getIncidentUuid());
        Report r = reportRepository.findByIncidentUuid(report.getIncidentUuid());
        sOrder.getReportUuid();

            if(r.getIncidentUuid()!= null){
                r.setSupported(true);
                Report rp = reportRepository.save(report);
                return new Response(rp, ServiceStatus.SUCCESS);
            }

            return new Response(0, ServiceStatus.SUCCESS);
    
        } 
    
}
