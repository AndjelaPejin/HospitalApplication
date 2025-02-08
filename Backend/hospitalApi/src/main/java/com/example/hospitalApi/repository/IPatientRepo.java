package com.example.hospitalApi.repository;

import com.example.hospitalApi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPatientRepo extends JpaRepository<Patient,String> {
    Optional<Patient> findById(String id);
}
