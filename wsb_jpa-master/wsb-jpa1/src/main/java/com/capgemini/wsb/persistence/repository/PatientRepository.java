package com.capgemini.wsb.persistence.repository;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    List<PatientEntity> findByLastName(String lastName);

    @Query("SELECT p FROM PatientEntity p WHERE size(p.visits) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanXVisits(long visitCount);

    @Query("SELECT p FROM PatientEntity p WHERE p.dateOfBirth > :date")
    List<PatientEntity> findPatientsYoungerThanDate(LocalDate date);
}
