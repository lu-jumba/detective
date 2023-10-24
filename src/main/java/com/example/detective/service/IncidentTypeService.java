package com.example.detective.service;


import com.example.detective.entities.IncidentType;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;

import com.example.detective.repository.IncidentTypeRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

@Service
@Transactional
@RequiredArgsConstructor
public class IncidentTypeService {
    private IncidentTypeRepository incidentTypeRepository;
    
    
    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")	
    public Response createIncidentType(IncidentType incidentType){ 
       /* ContractType ct= contractTypeRepository.findByUuid(contractType.getUuid());

        if(ct.getUuid().isEmpty()){*/
            IncidentType iType = new IncidentType();
            incidentType.setIncidentTypeId(Long.MIN_VALUE);
            incidentType.setUuid(iType.getUuid());
            incidentType.setDescription(iType.getDescription());
            incidentType.setConditions(iType.getConditions());
            incidentType.setServiceType(iType.getServiceType());
            incidentType.setMinDurationDays(360);
            incidentType.setMaxDurationDays(1080);
            incidentType.setActive(true);
                    //}

        IncidentType savedIncidentType = incidentTypeRepository.save(iType);        

        return new Response(savedIncidentType,ServiceStatus.SUCCESS);

    }
    
    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
    public Response setActiveIncidentType(String uuid, boolean active){
       IncidentType incidentType = incidentTypeRepository.findByUuid(uuid);


        if(incidentType.getUuid() == null){
            throw new Error( "Incident Type could not be found" );
        }
        
        else {
            incidentType.setActive(true);
            

        }
        IncidentType activeIncidentType = incidentTypeRepository.save(incidentType);
        //contractTypeRepository.save(ct);
        return new Response(activeIncidentType,ServiceStatus.SUCCESS);
    }


    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
    public Response incidentTypes(IncidentType incidentType, String uuid){
        IncidentType it = incidentTypeRepository.findByIncidentTypeUuid(uuid);

        boolean callingAsMerchant = true;
	incidentType.getServiceType();
        /*if (callingAsMerchant) {
                    
                 return new Response(0, ServiceStatus.SUCCESS);  
                 
        }*/
        
        ArrayList <IncidentType> incidentTypes = (ArrayList <IncidentType>) incidentTypeRepository.findActiveIncidentTypes(incidentType.getUuid());

        // Apply proper filtering, merchants should only see active Incidents
         if (!callingAsMerchant || (it.getServiceType().equals(incidentType.getServiceType()) && it.equals(it.active()))) {
		incidentTypes.add(it);
		} 

        return new Response(incidentTypes, ServiceStatus.SUCCESS);
    }            


    
}

