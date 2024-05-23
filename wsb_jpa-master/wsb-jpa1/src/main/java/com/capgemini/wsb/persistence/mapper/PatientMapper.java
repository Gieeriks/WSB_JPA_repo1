package com.capgemini.wsb.persistence.mapper;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.to.PatientTO;
import com.capgemini.wsb.persistence.to.VisitTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public PatientTO toTO(PatientEntity patientEntity) {
        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setActive(patientEntity.isActive());
        patientTO.setVisits(patientEntity.getVisits().stream()
                .map(this::toVisitTO)
                .collect(Collectors.toList()));
        return patientTO;
    }

    public PatientEntity toEntity(PatientTO patientTO) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setActive(patientTO.isActive());
        return patientEntity;
    }

    private VisitTO toVisitTO(VisitEntity visitEntity) {
        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());
        visitTO.setPatientName(visitEntity.getPatient().getFirstName() + " " + visitEntity.getPatient().getLastName());
        return visitTO;
    }
}
