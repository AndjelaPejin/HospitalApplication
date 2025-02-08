package com.example.hospitalApi.repository;

import com.example.hospitalApi.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor,String> {
}
