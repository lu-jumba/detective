package com.example.detective.service;


import com.example.detective.entities.IncidentType;
import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;

import com.example.detective.repository.IncidentTypeRepository;
import java.util.ArrayList;
import java.util.List;

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
    public Response <IncidentType> createIncidentType(IncidentType incidentType){ 
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

        return new Response <> (savedIncidentType,ServiceStatus.SUCCESS);

    }
    
    @PreAuthorize("hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
  
    public Response<IncidentType> setActiveIncidentType(String uuid, boolean active) {
        IncidentType incidentType = incidentTypeRepository.findByUuid(uuid);
    
        if (incidentType == null) {
            return new Response<>(null, ServiceStatus.INCIDENT_TYPE_NOT_FOUND);
        } else {
            incidentType.setActive(active);
        }
    
        IncidentType activeIncidentType = incidentTypeRepository.save(incidentType);
        return new Response<>(activeIncidentType, ServiceStatus.SUCCESS);
    }
    


    @PreAuthorize("hasRoles('USER') or hasRoles('DETECTIVES') or hasRoles('SUPERADMIN')")
            
    public Response<List<IncidentType>> incidentTypes(String uuid, IncidentType incidentType) {
        IncidentType it = incidentTypeRepository.findByIncidentTypeUuid(uuid);
        
        boolean callingAsMerchant = true;
        incidentType.getServiceType();
        
        List<IncidentType> incidentTypes = (ArrayList<IncidentType>) incidentTypeRepository.findActiveIncidentTypes(incidentType.getUuid());
    
        // Apply proper filtering, merchants should only see active Incidents
        if (!callingAsMerchant || (it.getServiceType().equals(incidentType.getServiceType()) && it.isActive())) {
            incidentTypes.add(it);
        }
    
        return new Response<>(incidentTypes, ServiceStatus.SUCCESS);
    }
    

    
}

