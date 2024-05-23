package com.capgemini.wsb.persistance;

import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.to.VisitTO;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {

    public VisitTO toTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorId(visitEntity.getDoctor().getId());
        visitTO.setPatientId(visitEntity.getPatient().getId());
        visitTO.setMedicalTreatmentId(visitEntity.getMedicalTreatment().getId());

        return visitTO;
    }

    public VisitEntity toEntity(VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());

        // Pamiętaj, że musisz ustawić referencje do innych encji (Doctor, Patient, MedicalTreatment) na podstawie ID.
        // Możesz to zrobić za pomocą odpowiednich repozytoriów w serwisie.

        return visitEntity;
    }
}
