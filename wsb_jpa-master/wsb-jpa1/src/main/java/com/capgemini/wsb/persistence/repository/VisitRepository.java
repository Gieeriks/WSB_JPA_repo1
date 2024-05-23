package com.capgemini.wsb.persistence.repository;

import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    List<VisitEntity> findAllByPatientId(Long patientId);
}
