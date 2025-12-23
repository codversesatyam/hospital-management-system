package com.codeverse.HospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.codeverse.HospitalManagement.Enum.BloodGroup;

@Entity
@Getter
@Setter
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @NotNull(message = "Email is required")
    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @OneToOne(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    @JoinColumn(name = "patient_insurance_id")
    @JsonManagedReference
    private Insurance insurance;

    @OneToMany(mappedBy = "patient" , cascade = {CascadeType.REMOVE})
    private List<Appointment> appointments;

}