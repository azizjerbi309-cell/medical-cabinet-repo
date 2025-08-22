package com.example.demo.service;

import com.example.demo.model.Prescription;
import com.example.demo.model.MedicalRecord;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public List<Prescription> getPrescriptionsByMedicalRecord(MedicalRecord medicalRecord) {
        return prescriptionRepository.findByMedicalRecord(medicalRecord);
    }
}
