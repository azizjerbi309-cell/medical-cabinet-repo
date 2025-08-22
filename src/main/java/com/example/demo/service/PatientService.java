package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient updatedPatient = patientOptional.get();
            updatedPatient.setFirstName(patient.getFirstName());
            updatedPatient.setLastName(patient.getLastName());
            updatedPatient.setGender(patient.getGender());
            updatedPatient.setDateOfBirth(patient.getDateOfBirth());
            updatedPatient.setAddress(patient.getAddress());
            updatedPatient.setPhone(patient.getPhone());
            updatedPatient.setEmail(patient.getEmail());
            updatedPatient.setEmergencyContact(patient.getEmergencyContact());
            return patientRepository.save(updatedPatient);
        }
        else return null;

    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
