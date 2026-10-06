package org.example.capstoneapi.service;


import org.example.capstoneapi.exception.EnrollmentNotfoundException;
import org.example.capstoneapi.model.Enrollment;
import org.example.capstoneapi.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }


    public Enrollment save(Enrollment enrollment){
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment findById(Long id){
        return enrollmentRepository.findById(id).orElseThrow(()-> new EnrollmentNotfoundException("This enrollment cannot be found"));

    }

    public List<Enrollment> findAll(){
        return enrollmentRepository.findAll();
    }

    public void deleteById(Long id){
        if(!enrollmentRepository.existsById(id)){
            throw new EnrollmentNotfoundException("Enrollement not found..");
        }

        enrollmentRepository.deleteById(id);
    }
}
