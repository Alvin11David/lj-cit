package org.example.capstoneapi.mapper;


import org.example.capstoneapi.dto.StudentRequest;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request){
        Student student = new Student();
        student.setName(request.name());
        student.setRegNumber(request.regNumber());
        student.setGpa(request.gpa());

        return student;
    }


    public StudentResponse toResponse(Student student){
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getRegNumber(),
                student.getGpa()
        );
    }
}
