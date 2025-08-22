package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phone;
    private String email;
    private String license_number;


//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Appointment> appointments;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<MedicalRecord> medicalRecords;

}
