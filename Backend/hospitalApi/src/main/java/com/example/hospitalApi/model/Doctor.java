package com.example.hospitalApi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Doctor extends BaseEntity{
    @JsonManagedReference(value = "patient-doctors")
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    private List<Patient> patients = new ArrayList<>();
}
