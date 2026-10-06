package org.example.capstoneapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "reg_number", nullable = false, unique = true)
    private String regNumber;

    @Column(nullable = false)
    private double gpa;

    @OneToMany(mappedBy = "student")
    List<Enrollment> enrollmentList = new ArrayList<>();
}
