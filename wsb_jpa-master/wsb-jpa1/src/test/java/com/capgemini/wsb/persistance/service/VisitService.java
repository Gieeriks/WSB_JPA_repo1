package com.capgemini.wsb.persistance.service;

import com.capgemini.wsb.persistence.repository.VisitRepository;
import com.capgemini.wsb.persistance.VisitMapper;
import com.capgemini.wsb.persistence.to.VisitTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Autowired
    public VisitService(VisitRepository visitRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
    }

    public List<VisitTO> findAllByPatientId(Long patientId) {
        return visitRepository.findAllByPatientId(patientId).stream()
                .map(visitMapper::toTO)
                .collect(Collectors.toList());
    }
}
