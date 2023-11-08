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
    public Response <List<SupportOrder>> listSupportOrders(String reportUuid){
        SupportOrder sOrder = supportOrderRepository.findByReportUuid(reportUuid);

        if(!sOrder.getReportUuid().isEmpty()){
            
            List<SupportOrder> supportOrders = supportOrderRepository.findAll();
            return new Response <>(supportOrders , ServiceStatus.SUCCESS);
            
        }
        return new Response <> (null, ServiceStatus.ERROR);
        
    }

    @PreAuthorize("hasRoles('SUPPORT') or hasRoles('SUPERADMIN')")
          public Response<Integer> completeSupportOrder(String reportUuid) {
            // Fetch the support order based on the report UUID
            SupportOrder sOrder = supportOrderRepository.findByReportUuid(reportUuid);

            if (sOrder == null) {
                // Handle the case where the support order is not found

                return new Response<>(null, ServiceStatus.SUPPORT_ORDER_NOT_FOUND);

                } else {
                    // Mark the support order as ready
                    sOrder.setReady(true);
                    supportOrderRepository.save(sOrder);
                    
                    // Fetch the corresponding report

                    Report report = supportOrderRepository.findReportBySupportOrder(sOrder.getIncidentUuid());

                    if (report == null) {

                        // Handle the case where the report is not found
                        return new Response<>(null, ServiceStatus.REPORT_NOT_FOUND);

                        } else {
                            // Mark the report as supported
                            report.setSupported(true);
                            reportRepository.save(report);
                    }

                    // Return a success response

                    return new Response<>(0, ServiceStatus.SUCCESS);
                }
            }


    /*public Response <Integer> completeSupportOrder(String reportUuid){
        SupportOrder sOrder = supportOrderRepository.findByReportUuid(reportUuid);

        if(sOrder.getReportUuid() == null){
            return new Response <> (null, ServiceStatus.SUPPORT_ORDER_NOT_FOUND);
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
                reportRepository.save(report);
                //return new Response<>(rp, ServiceStatus.SUCCESS);
            }

            return new Response<>(0, ServiceStatus.SUCCESS);
    
        } 
         */
    
}
