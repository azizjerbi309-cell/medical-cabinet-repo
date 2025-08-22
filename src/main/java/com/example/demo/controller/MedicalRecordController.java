package com.example.demo.controller;

import com.example.demo.model.MedicalRecord;
import com.example.demo.model.Patient;
import com.example.demo.model.Doctor;
import com.example.demo.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical_records")
@CrossOrigin(origins = "http://localhost:3306")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> record = medicalRecordService.getMedicalRecordById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.saveMedicalRecord(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateRecord(@PathVariable Long id, @RequestBody MedicalRecord recordDetails) {
        Optional<MedicalRecord> recordOptional = medicalRecordService.getMedicalRecordById(id);
        if (recordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MedicalRecord record = recordOptional.get();
        record.setPatient(recordDetails.getPatient());
        record.setDoctor(recordDetails.getDoctor());
        record.setVisitDate(recordDetails.getVisitDate());
        record.setDiagnosis(recordDetails.getDiagnosis());
        record.setTreatment(recordDetails.getTreatment());
        record.setNotes(recordDetails.getNotes());

        MedicalRecord updatedRecord = medicalRecordService.saveMedicalRecord(record);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        Optional<MedicalRecord> record = medicalRecordService.getMedicalRecordById(id);
        if (record.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getRecordsByPatient(@PathVariable Long patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        return medicalRecordService.getRecordsByPatient(patient);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<MedicalRecord> getRecordsByDoctor(@PathVariable Long doctorId) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        return medicalRecordService.getRecordsByDoctor(doctor);
    }

    @GetMapping("/date/{date}")
    public List<MedicalRecord> getRecordsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return medicalRecordService.getRecordsByDate(localDate);
    }
}
