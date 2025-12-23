package com.codeverse.HospitalManagement.entity;

import com.codeverse.HospitalManagement.entity.Doctor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne
    private Doctor headOfDepartment;

    @ManyToMany
    @JoinTable(
            name = "MY_DEPARTMENT_DOCTOR",
            joinColumns = @jakarta.persistence.JoinColumn(name = "department_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();

}
