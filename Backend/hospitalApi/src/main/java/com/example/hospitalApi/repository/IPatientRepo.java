package com.example.hospitalApi.repository;

import com.example.hospitalApi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientRepo extends JpaRepository<Patient,String> {
}
