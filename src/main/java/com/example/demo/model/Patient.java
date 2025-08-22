package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String emergencyContact;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Appointment> appointments;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<MedicalRecord> medicalRecords;

}
