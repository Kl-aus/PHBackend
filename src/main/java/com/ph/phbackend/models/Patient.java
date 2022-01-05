package com.ph.phbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ph.phbackend.payload.request.PatientRequest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "patient_id", nullable = false)
        private Long patientId;

        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        private Long weight;
        private Long height;
        private String age;
        private String gender;

        public Patient(String firstName, String lastName, Long weight, Long height, String age, String gender) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.weight = weight;
                this.height = height;
                this.age = age;
                this.gender = gender;
        }
        @JsonIgnore
        @ManyToMany
        @JoinTable(name = "patient_diagnose_relation",
                joinColumns = @JoinColumn(name = "patient_id"),
                inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
        private Set<Diagnose> diagnoses = new HashSet<>();


        public Patient() {
        }

        public String getFirstName() {
                return firstName;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
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

        public Long getPatientId() {
                return patientId;
        }

        public void setPatientId(Long id) {
                this.patientId = id;
        }

        public Set<Diagnose> getDiagnoses() {
                return diagnoses;
        }

        public void setDiagnoses(Set<Diagnose> diagnoses) {
                this.diagnoses = diagnoses;
        }

}
