package org.example.capstoneapi.service;

import org.example.capstoneapi.exception.StudentNotfoundException;
import org.example.capstoneapi.model.Student;
import org.example.capstoneapi.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradeCalculator gradeCalculator;

    public StudentService(StudentRepository studentRepository, GradeCalculator gradeCalculator){
        this.studentRepository = studentRepository;
        this.gradeCalculator = gradeCalculator;

    }

    public Page<Student> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("gpa").descending());
        return studentRepository.findAll(pageable);
    }


    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student findById(Long id){
        return studentRepository.findById(id).orElseThrow(()->new  StudentNotfoundException("Student not found with id"+id));
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public void delete(Long id){
        if(!studentRepository.existsById(id)){
            throw new StudentNotfoundException(
                    "Course not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    public char calculateLetterGrade(double gpa){
        return gradeCalculator.turnAverageToLetterGrade(gpa);
    }


}
