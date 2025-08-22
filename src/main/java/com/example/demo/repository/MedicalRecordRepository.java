package com.example.demo.repository;

import com.example.demo.model.MedicalRecord;
import com.example.demo.model.Patient;
import com.example.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByPatient(Patient patient);
    List<MedicalRecord> findByDoctor(Doctor doctor);
    List<MedicalRecord> findByVisitDate(LocalDate date);
    List<MedicalRecord> findByPatientAndVisitDate(Patient patient, LocalDate date);
    List<MedicalRecord> findByDoctorAndVisitDate(Doctor doctor, LocalDate date);
}
