package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> getAllDoctors(Long id, Doctor doctor) {
        return repository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Doctor createDoctors(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        doctor.setId(id);
        return repository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        repository.deleteById(id);
    }



}
