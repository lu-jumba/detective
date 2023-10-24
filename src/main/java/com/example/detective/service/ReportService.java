package com.example.detective.service;

import com.example.detective.entities.SupportOrder;
import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import com.example.detective.enums.ReportStatus;
import com.example.detective.enums.Roles;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.SupportOrderRepository;
import com.example.detective.repository.ReportRepository;
import com.example.detective.repository.IncidentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
     private final ReportRepository reportRepository;
        private final IncidentRepository incidentRepository;
        private final SupportOrderRepository followupOrderRepository;


    @PreAuthorize("hasRoles('DETECIVES') or hasRoles('SUPERADMIN')")
        public Response reportIncident(Report r) {
        Incident ic = reportRepository.findByUuid(r.getIncidentUuid());
        if(ic == null){
            return null;
        }
           

       return new Response(ic, ServiceStatus.SUCCESS);
        
    }
        
    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
    public Response reports(String incidentUuid, ReportStatus status) {
        
       Report report = reportRepository.findByIncidentUuid(incidentUuid);


        // Skip the processing of the result, if the status
        // does not equal the query status; list all, if unknown
        if(report.getStatus() != status && status != ReportStatus.UNKNOWN) {
            return null;
                   }
        ArrayList<Report> reports = (ArrayList<Report>) reportRepository.findAll();
            return new Response(reports, ServiceStatus.SUCCESS);  


}

@PreAuthorize("hasRoles('USER')")
public Response fileReport(User user, String incidentUuid) {
    
    Report report = new Report();
    report.setStatus(ReportStatus.NEW);

// Check if the case exists
Incident ic = reportRepository.findByUuid(report.getIncidentUuid());
	if (ic == null) {
		throw new Error("Incident could not be found.");
	}

	// Persist the report

    ic.getUuid();
    report.getIncidentUuid(); 

    reportRepository.save(report);    

	// Update the report index in the case
	ic.setReports(Arrays.asList(report));

    ic.getUsername();
    report.getIncidentUuid();
    
    incidentRepository.save(ic);

    return new Response(0,ServiceStatus.SUCCESS);  

}

@PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
public Response processReport(String incidentUuid, ReportStatus status) {   
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);

    if(report.getIncidentUuid()== null) {
        throw new Error("Report cannot be found.");
      }
    Incident ic = reportRepository.findByUuid(report.getIncidentUuid());
    report.getIncidentUuid();
    report.getStatus();
    report.getReportable();
    report.getVerifiable();
    ic.getUuid();
    
    if((!report.isReporting() || !report.isInvestigator()) && report.getStatus() != ReportStatus.NEW) {
      throw new Error("Cannot change the status of a non-new report.");
    }
    if((report.isReporting() || report.isInvestigator()) && report.getStatus() == ReportStatus.NEW) {
      throw new Error("Reporting or Investigator must first be confirmed by the detectives.");
    }

  
    report.setStatus(status); // Assigning requested status
    switch(status) {
      case SUPPORT -> {
          if(report.isReporting() || report.isInvestigator()) {
              throw new Error("Cannot follow up first time reports.");
          }
          report.setReportable(0);

          if(report.isInvestigator()) {
              throw new Error("Cannot follow up first time reports.");
          }

          report.setVerifiable(0); 
          
          Incident i = reportRepository.findByUuid(report.getIncidentUuid());
          if(i == null) {
              throw new Error("Could not retrieve incident.");
          }

          // Create new follow up order.
 
          String reportUuid = null;
          SupportOrder supportOrder = new SupportOrder();
          supportOrder.setSupportOrderId(Long.MIN_VALUE);
          supportOrder.setIncidentUuid(report.getIncidentUuid());
          supportOrder.setInfo(ic.getInfo());
          supportOrder.setReady(false);
          supportOrder.setReportUuid(reportUuid);
          
          
           followupOrderRepository.save(supportOrder);

         }

  
      case REPORTING -> {
            float reportable = 0;

          // Approve investigation results, and add the sum if necessary
          report.setReportable(reportable);

          // get the incident and save the report
          if(report.isReporting()) {
              Incident in = reportRepository.findByIncidentTypeUuid(report.getIncidentUuid());
              if(in == null) {
                  throw new Error("Could not retrieve incident.");
              }   

              // Persist incident
              in.getUsername();

              incidentRepository.save(in);
          }   
        }
    case VERIFICATION -> {
        float verifiable = 0;

        // Approve investigator  
          report.setVerifiable(verifiable);
          //  mark him/her as verified
          if(report.isInvestigator()) {
              Incident in = reportRepository.findByIncidentTypeUuid(report.getIncidentUuid());
              if(in == null) {
                  throw new Error("Could not retrieve incident.");
              }
              in.setVerified(true);
              
              // Persist incident
              in.getUsername();

              incidentRepository.save(in);
          }   

    }
  
      case REJECTED -> {// Mark as rejected
        if(report.isReporting()) {

            report.setReportable(0);
        }

        if(report.isInvestigator()){

            report.setVerifiable(0);
        }
      }
  
      default -> {
          throw new Error("Unknown status change.");
            }
    }
    
    //Persist the report

    report.getIncidentUuid(); 

    Report savedReport = reportRepository.save(report);

    return new Response(savedReport, ServiceStatus.SUCCESS);
  
  }

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
  public Response listReportingReports(String incidentUuid, ReportStatus status) {
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    
    
    //Filter out the irrelevant reports
    if (!report.isReporting() || report.getStatus() != ReportStatus.NEW) {
        return null;
        }
    
    Incident in = reportRepository.findByUuid(report.getIncidentUuid());
    
    if (in == null) {
        throw new Error("Error acquiring incidents.");
        }
    
    in.getUuid();
    report.getIncidentUuid();
    
   
    
    User user = incidentRepository.findUserByUsername(in.getUsername());
    
    if (user == null) {
        throw new Error("Error acquiring user.");
        }
    
    in.getInfo();
    report.getDescription();
    user.getFirstName();
    user.getLastName();
    
    List<Report> reports = reportRepository.findReportByReporting (report, Sort.by(Sort.Direction.DESC, "isReporting"));
    return new Response(reports, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
  public Response listInvestigatorReports(String incidentUuid, ReportStatus status) {
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    
    
    //Filter out the irrelevant reports
    if (!report.isInvestigator() || report.getStatus() != ReportStatus.NEW) {
        return null;
        }
    
    Incident in = reportRepository.findByUuid(report.getIncidentUuid());
    
    if (in == null) {
        throw new Error("Error acquiring incidents.");
        }
    
    in.getUuid();
    report.getIncidentUuid();
    
   
    
    User user = incidentRepository.findUserByUsername(in.getUsername());
    
    if (user == null) {
        throw new Error("Error acquiring user.");
        }
    
    in.getInfo();
    report.getDescription();
    user.getFirstName();
    user.getLastName();
    
    List<Report> reports = reportRepository.findReportByInvestigator (report, Sort.by(Sort.Direction.DESC, "isInvestigator"));
    return new Response(reports, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
public Response processReportingReport(String incidentUuid, ReportStatus status, String fileReference) {
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    if (report.getIncidentUuid()== null) {
        throw new Error("report cannot be found.");
        }
    
   if (!report.isReporting() && report.getStatus() != ReportStatus.NEW) {
        throw new Error("report is either not completed or has invalid status.");
        }
    else if (report.isReporting()) {
        report.setStatus(ReportStatus.CONFIRMED);
    } else {
        report.setStatus(ReportStatus.REJECTED); // by authorities
    }
    report.setFileReference(fileReference);

    Report savedReport = reportRepository.save(report);

	return new Response(savedReport, ServiceStatus.SUCCESS); 
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
public Response processInvestigatorReport(String incidentUuid, ReportStatus status, String fileReference) {
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    if (report.getIncidentUuid()== null) {
        throw new Error("report cannot be found.");
        }
    
   if (!report.isInvestigator() && report.getStatus() != ReportStatus.NEW) {
        throw new Error("report is either not related to investigator verification or has invalid status.");
        }
    else if (report.isInvestigator()) {
        report.setStatus(ReportStatus.CONFIRMED);
    } else {
        report.setStatus(ReportStatus.REJECTED); // by authorities
    }
    report.setFileReference(fileReference);

    Report savedReport = reportRepository.save(report);

	return new Response(savedReport, ServiceStatus.SUCCESS); 
}
}
