package org.example.capstoneapi.mapper;



import org.example.capstoneapi.dto.EnrollmentResponse;

import org.example.capstoneapi.model.Course;
import org.example.capstoneapi.model.Enrollment;
import org.example.capstoneapi.model.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(Student student, Course course){
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        return enrollment;
    }


    public EnrollmentResponse toResponse(Enrollment enrollment){
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId()
        );
    }
}
