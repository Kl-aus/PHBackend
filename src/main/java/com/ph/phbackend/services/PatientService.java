package com.ph.phbackend.services;

import com.ph.phbackend.models.*;
import com.ph.phbackend.payload.request.AnamnesisRequest;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.AnamnesisRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.PatientRepository;
import com.ph.phbackend.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
public class PatientService {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private AnamnesisRepository anamnesisRepository;
    private AnamnesisService anamnesisService;
    private DiagnoseService diagnoseService;
    private DiagnoseRepository diagnoseRepository;
    LocalContainerEntityManagerFactoryBean transactionManager;



    @Autowired
    public PatientService(PatientRepository patientRepository,
                          UserRepository userRepository,
                          AnamnesisRepository anamnesisRepository,
                          AnamnesisService anamnesisService,
                          DiagnoseService diagnoseService,
                          DiagnoseRepository diagnoseRepository
//                        LocalContainerEntityManagerFactoryBean transactionManager,

    ) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.anamnesisRepository = anamnesisRepository;
        this.anamnesisService = anamnesisService;
        this.diagnoseService = diagnoseService;
        this.diagnoseRepository = diagnoseRepository;
    }

    @Transactional
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Set<Patient> listPatientsById(long id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()) {
            Set<Patient> patients = user.get().getPatients();
            Hibernate.initialize(patients);
            return patients;
        } else {
            return null;
        }
    }

    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient createPatient(PatientRequest patientRequest) {

        Patient patient = new Patient(patientRequest.getFirstName(),
                patientRequest.getLastName(),
                patientRequest.getHeight(),
                patientRequest.getWeight(),
                patientRequest.getAge(),
                patientRequest.getGender());

        userRepository.findById(patientRequest.getUserId()).ifPresent(user -> {
            Set<Patient> patients = user.getPatients();
            patients.add(patient);
            Hibernate.initialize(patients);
            patientRepository.save(patient);
            userRepository.save(user);
        });
        return patient;
    }

    @Transactional
    public void deletePatientsById(long patientId, long userId) {
        // To avoid Cascade delete & FK constraint errors delete relations manually
        Optional<Patient> patient =  patientRepository.findById(patientId);
        if(patient.isPresent()) {
            //Delete user-patient relation
            User user = userRepository.getById(userId);
            Set<Patient> patients = user.getPatients();
            for(Iterator<Patient> iterator = patients.iterator(); iterator.hasNext();) {
                Long thisPatientId = iterator.next().getPatientId();
                if (patientId == thisPatientId ) {
                    iterator.remove();
                }
            }
            user.setPatients(patients);
            userRepository.save(user);

            //clear patient-diagnoses relation
            Set<Diagnose> diagnoses = patient.get().getDiagnoses();
            diagnoses.clear();
            patient.get().setDiagnoses(diagnoses);
            patientRepository.save(patient.get());

            //Delete Patient
            patientRepository.delete(patient.get());
        }
    }

    @Transactional
    public void deletePatientDiagnoses(Set<Diagnose> diagnoses, long patientId) {
        Patient patient = patientRepository.getById(patientId);
        Set<Diagnose> patientDiagnoses = patient.getDiagnoses();
        patientDiagnoses.removeAll(diagnoses);
        patient.setDiagnoses(patientDiagnoses);
        patientRepository.save(patient);
    }

    @Transactional
    public Set<Anamnesis> saveAnamnesis(AnamnesisRequest anamnesisRequest) {
        Patient patient = this.patientRepository.getById(anamnesisRequest.getPatientId());
        Set<Anamnesis> anamneses = anamnesisRequest.getAnamnesis();
        patient.setAnamneses(anamneses);
        Set<Diagnose> patientDiagnoses = new HashSet<>();
        List<Long> questionIdlist = new ArrayList<>();
        if (anamneses != null) {
            for (Anamnesis anamnesis : anamneses) {
                questionIdlist.add(anamnesis.getQuestionId());
            }
        }
        String q = "select distinct diagnoses_id from anamnesis_diagnoses_relation where question_id in(?1)";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, questionIdlist);
        try {
            List<Object[]> resultList = query.getResultList();
            for (int i = 0; i < resultList.size(); i++) {
                long diagnosesId = Long.parseLong(String.valueOf(resultList.get(i)));
                Optional<Diagnose> diagnose = diagnoseRepository.findById(diagnosesId);
                if (diagnose.isPresent()) {
                    patientDiagnoses.add(diagnose.get());
                } else {
                    System.out.println("diagnoses not found");
                    return null;
                }
            }
            patient.setDiagnoses(patientDiagnoses);
            patientRepository.save(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return anamneses;
    }
}
