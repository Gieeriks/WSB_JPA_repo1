package com.capgemini.wsb.persistance.repository;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@Sql("/data.sql")
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void testFindByLastName() {
        List<PatientEntity> patients = patientRepository.findByLastName("Johnson");
        assertEquals(1, patients.size());
    }

    @Test
    void testFindPatientsWithMoreThanXVisits() {
        List<PatientEntity> patients = patientRepository.findPatientsWithMoreThanXVisits(1L);
        assertEquals(1, patients.size());
    }

    @Test
    void testFindPatientsYoungerThanDate() {
        List<PatientEntity> patients = patientRepository.findPatientsYoungerThanDate(LocalDate.of(1988, 1, 1));
        assertFalse(patients.isEmpty());
    }
}
