package com.example.hospitalApi.controller;

import com.example.hospitalApi.dto.PatientDTO;
import com.example.hospitalApi.model.Patient;
import com.example.hospitalApi.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final IPatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO>create(@RequestBody @Valid PatientDTO patientDTO){
        PatientDTO createdPatient = patientService.create(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }
    @GetMapping
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(patientService.getAll());
    }
    @GetMapping("/fullpage")
    public ResponseEntity<Page<Patient>>getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.ok().body(patientService.getAllByPage(page,size));
    }

    @PutMapping("/{id}")
    ResponseEntity<PatientDTO>update(@RequestBody @Valid PatientDTO patientDTO, @PathVariable String id) {
        PatientDTO updatedPatient = patientService.update(patientDTO,id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable String id)  {
        PatientDTO expenseDTO = patientService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(expenseDTO);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<PatientDTO>delete(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.deleteById(id));
    }
}