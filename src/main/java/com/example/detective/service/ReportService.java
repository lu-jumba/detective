package com.example.detective.service;

import com.example.detective.entities.SupportOrder;
import com.example.detective.entities.Report;
import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import com.example.detective.enums.ReportStatus;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.repository.SupportOrderRepository;
import com.example.detective.repository.UserRepository;
import com.example.detective.repository.ReportRepository;
import com.example.detective.repository.IncidentRepository;

import java.util.ArrayList;
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
    private final UserRepository userRepository;



    @PreAuthorize("hasRoles('DETECIVES') or hasRoles('SUPERADMIN')")
        public Response <Incident> reportIncident(Report r) {
        Incident ic = reportRepository.findByUuid(r.getIncidentUuid());
        if(ic == null){
            return new Response<>(null, ServiceStatus.INCIDENT_NOT_FOUND);
        }
           

       return new Response<>(ic, ServiceStatus.SUCCESS);
        
    }
        
    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
    public Response <List<Report>> reports(String incidentUuid, ReportStatus status) {
        
       Report report = reportRepository.findByIncidentUuid(incidentUuid);


        // Skip the processing of the result, if the status
        // does not equal the query status; list all, if unknown
        if(report.getStatus() != status && status != ReportStatus.UNKNOWN) {
            return new Response<>(null, ServiceStatus.INVALID_REPORT_STATUS);
                   }
        List<Report> reports = reportRepository.findAll();
            return new Response <>(reports, ServiceStatus.SUCCESS);  


}

@PreAuthorize("hasRoles('USER')")
public Response <Integer> fileReport(User user, String incidentUuid) {
    
    Report report = new Report();
    report.setStatus(ReportStatus.NEW);

// Check if the Incident exists
Incident ic = reportRepository.findByUuid(report.getIncidentUuid());
	if (ic == null) {
        return new Response<>(null, ServiceStatus.INCIDENT_NOT_FOUND);
	}

	// Persist the report

    ic.getUuid();
    report.getIncidentUuid(); 

    reportRepository.save(report);    

	// Update the report index in the Incident
	ic.getReportIndex().add(report);

    ic.getUsername();
    //report.getIncidentUuid();
    
    incidentRepository.save(ic);

    return new Response <> (0,ServiceStatus.SUCCESS);  

}

@PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
public Response <Report> processReport(String incidentUuid, ReportStatus status) {   
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);

    if(report.getIncidentUuid()== null) {
        return new Response<>(null, ServiceStatus.REPORT_NOT_FOUND);
      }
    Incident ic = reportRepository.findByUuid(report.getIncidentUuid());
    report.getIncidentUuid();
    report.getStatus();
    report.getReportable();
    report.getVerifiable();
    ic.getUuid();
    
    if((!report.isReporting() || !report.isInvestigator()) && report.getStatus() != ReportStatus.NEW) {
      return new Response<>(null, ServiceStatus.CHANGE_NOT_ALLOWED);
    }
    if((report.isReporting() || report.isInvestigator()) && report.getStatus() == ReportStatus.NEW) {
      return new Response<>(null, ServiceStatus.UNCOFIRMED );
    }

  
    report.setStatus(status); // Assigning requested status
    switch(status) {
      case SUPPORT -> {
          if(report.isReporting() /*|| report.isInvestigator()*/) {
              return new Response<>(null, ServiceStatus.REPORT_ERROR);
          }
          report.setReportable(0);

          if(report.isInvestigator()) {
            return new Response<>(null, ServiceStatus.REPORT_ERROR);
          }

          report.setVerifiable(0); 
          
          Incident i = reportRepository.findByUuid(report.getIncidentUuid());
          if(i == null) {
              return new Response<>(null, ServiceStatus.INCIDENT_ERROR);
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
                  return new Response<>(null, ServiceStatus.INCIDENT_ERROR);
              }   

              //find user of in
              //in.getUsername();
              //get user
              User u = incidentRepository.findUserByUsername( in.getUsername());
              float balance = u.getBalance();
              float freedoms = u.getFreedoms();

              //set fees and awarded freedoms
              float fees = reportable;
              float rewardedFreedoms = 1/1000;
              
              //ensure user has enough balance
              if (balance<fees){
                return new Response<>(null, ServiceStatus.LOW_BALANCE);
              }

              //update user's balance and freedoms
              u.setBalance(balance-fees);
              u.setFreedoms(freedoms + rewardedFreedoms);

              //Persist user
              userRepository.save(u);

              // Persist incident

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
                return new Response<>(null, ServiceStatus.INCIDENT_ERROR);
              }
              in.setVerified(true);
              
              
              //in.getUsername();
              //get user
              User u = incidentRepository.findUserByUsername( in.getUsername());
              float balance = u.getBalance();
              float freedoms = u.getFreedoms();

              //set fees and awarded freedoms
              float fees = verifiable;
              float rewardedFreedoms = 1/1000;
              
              //ensure user has enough balance
              if (balance<fees){
                return new Response<>(null, ServiceStatus.LOW_BALANCE);
              }

              //update user's balance and freedoms
              u.setBalance(balance-fees);
              u.setFreedoms(freedoms + rewardedFreedoms);

              //Persist user
              userRepository.save(u);

              // Persist incident
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
        return new Response<>(null, ServiceStatus.STATUS_ERROR);
            }
    }
    
    //Persist the report

    report.getIncidentUuid(); 

    Report savedReport = reportRepository.save(report);

    return new Response <> (savedReport, ServiceStatus.SUCCESS);
  
  }

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
  public Response <List<Report>> listReportingReports(String incidentUuid, ReportStatus status) {
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    
    
    //Filter out the irrelevant reports
    if (!report.isReporting() || report.getStatus() != ReportStatus.NEW) {
        //return null;
        return new Response<>(new ArrayList<>(), ServiceStatus.REPORT_NOT_FOUND);
    }
    
    Incident in = reportRepository.findByUuid(report.getIncidentUuid());
    
    if (in == null) {
        return new Response<>(null, ServiceStatus.ERROR);
        }
    
    in.getUuid();
    report.getIncidentUuid();
    
   
    
    User user = incidentRepository.findUserByUsername(in.getUsername());
    
    if (user == null) {
        return new Response<>(null, ServiceStatus.ERROR);
        }
    
    in.getInfo();
    report.getDescription();
    user.getFirstName();
    user.getLastName();
    
    List<Report> reports = reportRepository.findReportByReporting (report, Sort.by(Sort.Direction.DESC, "isReporting"));
    return new Response <>(reports, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
  public Response <List<Report>> listInvestigatorReports(String incidentUuid, ReportStatus status) {
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    
    
    //Filter out the irrelevant reports
    if (!report.isInvestigator() || report.getStatus() != ReportStatus.NEW) {
        return new Response<>(new ArrayList<>(), ServiceStatus.REPORT_NOT_FOUND);
        }
    
    Incident in = reportRepository.findByUuid(report.getIncidentUuid());
    
    if (in == null) {
        return new Response<>(null, ServiceStatus.ERROR);
        }
    
    in.getUuid();
    report.getIncidentUuid();
    
   
    
    User user = incidentRepository.findUserByUsername(in.getUsername());
    
    if (user == null) {
        return new Response<>(null, ServiceStatus.ERROR);
        }
    
    in.getInfo();
    report.getDescription();
    user.getFirstName();
    user.getLastName();
    
    List<Report> reports = reportRepository.findReportByInvestigator (report, Sort.by(Sort.Direction.DESC, "isInvestigator"));
    return new Response<>(reports, ServiceStatus.SUCCESS);
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
public Response <Report> processReportingReport(String incidentUuid, ReportStatus status, String fileReference) {
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    if (report.getIncidentUuid()== null) {
        return new Response<>(null, ServiceStatus.REPORT_NOT_FOUND);
        }
    
   if (!report.isReporting() && report.getStatus() != ReportStatus.NEW) {
    return new Response<>(null, ServiceStatus.REPORT_INCOMPLETE);
        }
    else if (report.isReporting()) {
        report.setStatus(ReportStatus.CONFIRMED);
    } else {
        report.setStatus(ReportStatus.REJECTED); // by authorities
    }
    report.setFileReference(fileReference);

    Report savedReport = reportRepository.save(report);

	return new Response <> (savedReport, ServiceStatus.SUCCESS); 
}

@PreAuthorize("hasRoles('INVESTIGATOR') or hasRoles('SUPERADMIN')")
public Response <Report> processInvestigatorReport(String incidentUuid, ReportStatus status, String fileReference) {
    
    Report report = reportRepository.findByIncidentUuid(incidentUuid);
    
    if (report.getIncidentUuid()== null) {
        return new Response<>(null, ServiceStatus.REPORT_NOT_FOUND);
        }
    
   if (!report.isInvestigator() && report.getStatus() != ReportStatus.NEW) {
        return new Response<>(null, ServiceStatus.REPORT_INCOMPLETE);
        }
    else if (report.isInvestigator()) {
        report.setStatus(ReportStatus.CONFIRMED);
    } else {
        report.setStatus(ReportStatus.REJECTED); // by authorities
    }
    report.setFileReference(fileReference);

    Report savedReport = reportRepository.save(report);

	return new Response <>(savedReport, ServiceStatus.SUCCESS); 
}
}
