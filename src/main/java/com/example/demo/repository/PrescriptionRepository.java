package com.example.demo.repository;

import com.example.demo.model.Prescription;
import com.example.demo.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    // Find prescriptions by medical record
    List<Prescription> findByMedicalRecord(MedicalRecord medicalRecord);
}
