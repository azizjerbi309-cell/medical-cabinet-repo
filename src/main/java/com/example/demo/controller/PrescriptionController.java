package com.example.demo.controller;

import com.example.demo.model.Prescription;
import com.example.demo.model.MedicalRecord;
import com.example.demo.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "http://localhost:3306")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Optional<Prescription> prescription = prescriptionService.getPrescriptionById(id);
        return prescription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prescription createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.savePrescription(prescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescriptionDetails) {
        Optional<Prescription> prescriptionOptional = prescriptionService.getPrescriptionById(id);
        if (prescriptionOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Prescription prescription = prescriptionOptional.get();
        prescription.setMedicalRecord(prescriptionDetails.getMedicalRecord());
        prescription.setMedication(prescriptionDetails.getMedication());
        prescription.setDosage(prescriptionDetails.getDosage());
        prescription.setFrequency(prescriptionDetails.getFrequency());
        prescription.setDuration(prescriptionDetails.getDuration());
        prescription.setInstructions(prescriptionDetails.getInstructions());

        Prescription updatedPrescription = prescriptionService.savePrescription(prescription);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        Optional<Prescription> prescription = prescriptionService.getPrescriptionById(id);
        if (prescription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medical_record/{medicalRecordId}")
    public List<Prescription> getPrescriptionsByMedicalRecord(@PathVariable Long medicalRecordId) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(medicalRecordId);
        return prescriptionService.getPrescriptionsByMedicalRecord(medicalRecord);
    }
}
