package com.capgemini.wsb.persistance.service;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import com.capgemini.wsb.persistence.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    private PatientEntity patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new PatientEntity();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("john.doe@example.com");
        patient.setPatientNumber("P12345");
        patient.setTelephoneNumber("555-1234");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setActive(true);
    }

    @Test
    void testShouldRemovePatientAndCascadeDeleteVisits() {
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(patientRepository).deleteById(anyLong());

        patientService.delete(patient.getId());

        verify(patientRepository, times(1)).deleteById(patient.getId());
    }

    @Test
    void testShouldThrowExceptionWhenDeletingNonExistentPatient() {
        when(patientRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EmptyResultDataAccessException.class, () -> patientService.delete(999L));

        verify(patientRepository, never()).deleteById(anyLong());
    }

    @Test
    void testShouldThrowExceptionWhenDeletingPatientWithIntegrityViolation() {
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        doThrow(new DataIntegrityViolationException("Constraint violation")).when(patientRepository).deleteById(anyLong());

        assertThrows(DataIntegrityViolationException.class, () -> patientService.delete(patient.getId()));

        verify(patientRepository, times(1)).deleteById(patient.getId());
    }
}
