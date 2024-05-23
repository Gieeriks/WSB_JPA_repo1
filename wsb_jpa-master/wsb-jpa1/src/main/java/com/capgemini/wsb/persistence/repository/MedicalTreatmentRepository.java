package com.capgemini.wsb.persistence.repository;

import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalTreatmentRepository extends JpaRepository<MedicalTreatmentEntity, Long> {
}
