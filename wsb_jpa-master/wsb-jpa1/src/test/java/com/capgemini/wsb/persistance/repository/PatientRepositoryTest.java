package com.capgemini.wsb.persistance.repository;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testFindByLastName() {
        List<PatientEntity> patients = patientRepository.findByLastName("Johnson");
        assertNotNull(patients);
        assertEquals(1, patients.size()); // Zakładając, że mamy jednego pacjenta o nazwisku "Johnson" w data.sql
    }

    @Test
    public void testFindPatientsWithMoreThanXVisits() {
        long visitCount = 1L;
        List<PatientEntity> patients = patientRepository.findPatientsWithMoreThanXVisits(visitCount);
        assertNotNull(patients);
        assertEquals(2, patients.size()); // Zakładając, że mamy dwóch pacjentów z więcej niż jedną wizytą w data.sql
    }

    @Test
    public void testFindPatientsYoungerThanDate() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        List<PatientEntity> patients = patientRepository.findPatientsYoungerThanDate(date);
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        assertTrue(patients.stream().allMatch(p -> p.getDateOfBirth().isAfter(date)));
    }
}
