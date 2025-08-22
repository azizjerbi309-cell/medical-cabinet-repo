package com.example.demo.service;

import com.example.demo.model.MedicalRecord;
import com.example.demo.model.Patient;
import com.example.demo.model.Doctor;
import com.example.demo.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public List<MedicalRecord> getRecordsByPatient(Patient patient) {
        return medicalRecordRepository.findByPatient(patient);
    }

    public List<MedicalRecord> getRecordsByDoctor(Doctor doctor) {
        return medicalRecordRepository.findByDoctor(doctor);
    }

    public List<MedicalRecord> getRecordsByDate(LocalDate date) {
        return medicalRecordRepository.findByVisitDate(date);
    }

    public List<MedicalRecord> getRecordsByPatientAndDate(Patient patient, LocalDate date) {
        return medicalRecordRepository.findByPatientAndVisitDate(patient, date);
    }

    public List<MedicalRecord> getRecordsByDoctorAndDate(Doctor doctor, LocalDate date) {
        return medicalRecordRepository.findByDoctorAndVisitDate(doctor, date);
    }
}
