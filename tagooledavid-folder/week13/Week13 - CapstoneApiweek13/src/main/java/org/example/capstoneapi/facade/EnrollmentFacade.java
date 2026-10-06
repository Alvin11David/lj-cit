package org.example.capstoneapi.facade;


import org.example.capstoneapi.dto.EnrollmentRequest;
import org.example.capstoneapi.dto.EnrollmentResponse;
import org.example.capstoneapi.mapper.EnrollmentMapper;
import org.example.capstoneapi.model.Course;
import org.example.capstoneapi.model.Enrollment;
import org.example.capstoneapi.model.Student;
import org.example.capstoneapi.service.CourseService;
import org.example.capstoneapi.service.EnrollmentService;
import org.example.capstoneapi.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentFacade {
    private final EnrollmentService enrollmentService;
    private  final EnrollmentMapper enrollmentMapper;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrollmentFacade(EnrollmentService enrollmentService, EnrollmentMapper enrollmentMapper, CourseService courseService,StudentService studentService){
        this.enrollmentService = enrollmentService;
        this.enrollmentMapper = enrollmentMapper;
        this.courseService = courseService;
        this.studentService = studentService;
    }


    @Transactional
    public EnrollmentResponse save(EnrollmentRequest enrollmentRequest){
        Student student = studentService.findById(enrollmentRequest.studentId());
        Course course = courseService.findById(enrollmentRequest.courseId());

        Enrollment studentEnrollment = enrollmentMapper.toEntity(student,course);
        Enrollment savedStudentEnrollment = enrollmentService.save(studentEnrollment);
        return enrollmentMapper.toResponse(savedStudentEnrollment);
    }


    @Transactional(readOnly = true)
    public EnrollmentResponse findById(Long id){
        Enrollment enrollment= enrollmentService.findById(id);
        return enrollmentMapper.toResponse(enrollment);
    }

    @Transactional(readOnly = true)
    public List<EnrollmentResponse> findAll(){
        return enrollmentService.findAll().stream().map(enrollmentMapper::toResponse).toList();
    }

    @Transactional
    public EnrollmentResponse update(Long id , EnrollmentRequest enrollmentRequest){
        Enrollment enrollment = enrollmentService.findById(id);
        Student student = studentService.findById(enrollmentRequest.studentId());
        Course course = courseService.findById(enrollmentRequest.courseId());

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedStudentEnrollment = enrollmentService.save(enrollment);
        return enrollmentMapper.toResponse(savedStudentEnrollment);
    }

    @Transactional
    public void delete(Long id){
        enrollmentService.deleteById(id);
    }




}
