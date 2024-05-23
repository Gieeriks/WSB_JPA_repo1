package com.capgemini.wsb.persistence.service;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import com.capgemini.wsb.persistence.mapper.PatientMapper;
import com.capgemini.wsb.persistence.to.PatientTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toTO)
                .collect(Collectors.toList());
    }

    public PatientTO findById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toTO)
                .orElse(null);
    }

    public PatientTO save(PatientTO patientTO) {
        PatientEntity patientEntity = patientMapper.toEntity(patientTO);
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return patientMapper.toTO(savedPatient);
    }

    public void delete(Long id) {
        if (patientRepository.existsById(id)) {
            try {
                patientRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("Cannot delete patient with existing visits.", e);
            }
        } else {
            throw new EmptyResultDataAccessException("No patient found with id: " + id, 1);
        }
    }
}
