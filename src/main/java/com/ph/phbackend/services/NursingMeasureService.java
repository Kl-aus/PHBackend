package com.ph.phbackend.services;

import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.repository.NursingMeasureRepository;
import com.ph.phbackend.repository.PatientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NursingMeasureService {

    NursingMeasureRepository nursingMeasureRepository;

    @Autowired
    public NursingMeasureService(NursingMeasureRepository nursingMeasureRepository) {
        this.nursingMeasureRepository = nursingMeasureRepository;
    }

    @Transactional
    public Set<NursingMeasure> getAllMeasures() {
        Set<NursingMeasure> measures = new HashSet<>(nursingMeasureRepository.findAll());
        Hibernate.initialize(measures);
        return measures;
    }
}
