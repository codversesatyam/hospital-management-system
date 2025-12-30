package com.codeverse.HospitalManagement.entity;

import java.util.*;

import ch.qos.logback.core.Appender;
import com.codeverse.HospitalManagement.entity.Department;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    private String phoneNo;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();

    public void setDepartment(Department department) {
    }
}
