package com.capgemini.wsb.persistance.service;

import com.capgemini.wsb.persistence.to.VisitTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testFindAllByPatientId() {
        Long patientId = 1L; // Zakładając, że mamy pacjenta o ID 1 w data.sql
        List<VisitTO> visits = visitService.findAllByPatientId(patientId);
        assertNotNull(visits);
        assertEquals(2, visits.size()); // Zakładając, że mamy dwie wizyty dla pacjenta o ID 1 w data.sql
    }
}
