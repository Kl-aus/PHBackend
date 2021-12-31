package com.ph.phbackend.services;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DiagnoseService {
    private DiagnoseRepository diagnoseRepository;
    private PatientRepository patientRepository;

    @Autowired
    public DiagnoseService(DiagnoseRepository diagnoseRepository, PatientRepository patientRepository) {
        this.diagnoseRepository = diagnoseRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public List<Diagnose> listDiagnoses() {
        return diagnoseRepository.findAll();
    }

    @Transactional
    public Set<Diagnose> savePatientDiagnoses(DiagnosesRequest diagnoses) {
        patientRepository.findById(diagnoses.getSelectedPatientId()).ifPresent(patient -> {
            Set<Diagnose> newSet = patient.getDiagnoses();
            newSet.addAll(diagnoses.getDiagnose());
            patient.setDiagnoses(newSet);
            patientRepository.save(patient);
        });
        return diagnoses.getDiagnose();
    }

    @Transactional
    public Set<Diagnose> getPatientDiagnoses(long selectedPatientId) {
          Optional<Patient> patient = patientRepository.findById(selectedPatientId);
        return patient.map(Patient::getDiagnoses).orElse(null);
    }
}

