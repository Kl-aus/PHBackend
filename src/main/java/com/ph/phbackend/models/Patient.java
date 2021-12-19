package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        private Integer weight;
        private Integer height;
        private Integer age;
        private String gender;

        public Patient(String firstName, String lastName, Integer weight, Integer height, Integer age, String gender) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.weight = weight;
                this.height = height;
                this.age = age;
                this.gender = gender;
        }

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

        public Integer getWeight() {
                return weight;
        }

        public void setWeight(Integer weight) {
                this.weight = weight;
        }

        public Integer getHeight() {
                return height;
        }

        public void setHeight(Integer height) {
                this.height = height;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
}
