package com.capgemini.wsb.persistance.service;

import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.repository.VisitRepository;
import com.capgemini.wsb.persistence.to.VisitTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<VisitTO> findAllByPatientId(Long patientId) {
        List<VisitEntity> visits = visitRepository.findAllByPatientId(patientId);
        return visits.stream().map(this::mapToTO).collect(Collectors.toList());
    }

    private VisitTO mapToTO(VisitEntity visitEntity) {
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorId(visitEntity.getDoctor().getId());
        visitTO.setPatientId(visitEntity.getPatient().getId());
        visitTO.setMedicalTreatmentId(visitEntity.getMedicalTreatment().getId());
        visitTO.setDoctorName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());
        visitTO.setPatientName(visitEntity.getPatient().getFirstName() + " " + visitEntity.getPatient().getLastName());
        return visitTO;
    }
}
