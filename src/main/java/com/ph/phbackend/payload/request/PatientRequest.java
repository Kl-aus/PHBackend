package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Patient;

import java.util.Set;

public class PatientRequest {

    private String firstName;
    private String lastName;
    private Long weight;
    private Long height;
    private String age;
    private String gender;
    private Long userId;
    private Set<Patient> patients;
    private Long patientId;

    public PatientRequest() {}

    public PatientRequest(String firstName, String lastName, Long weight, Long height, String age, String gender, Long userId, Set<Patient> patients, Long patientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.userId = userId;
        this.patients = patients;
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
