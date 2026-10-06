package org.example.capstoneapi.facade;

import org.example.capstoneapi.dto.ApiResponse;
import org.example.capstoneapi.dto.PageInfo;
import org.example.capstoneapi.dto.StudentRequest;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.mapper.StudentMapper;
import org.example.capstoneapi.model.Student;
import org.example.capstoneapi.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentFacade {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentFacade(StudentMapper studentMapper, StudentService studentService){
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }

    public ApiResponse<List<StudentResponse>> findPage(int page, int size) {
        Page<Student> studentPage = studentService.findPage(page, size);

        List<StudentResponse> data = studentPage.getContent().stream()
                .map(studentMapper::toResponse)
                .toList();

        PageInfo pageInfo = new PageInfo(
                studentPage.getTotalElements(),
                studentPage.getNumber() + 1,
                studentPage.getTotalPages()
        );

        return new ApiResponse<>("SUCCESS", "Students fetched successfully", data, pageInfo);
    }

    @Transactional
    public StudentResponse create(StudentRequest request){
        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentService.save(student);
        return studentMapper.toResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public StudentResponse getById(Long id){
        Student student = studentService.findById(id);
        return studentMapper.toResponse(student);
    }


    @Transactional(readOnly = true)
    public List<StudentResponse> getAll(){
        return studentService.findAll().stream().map(studentMapper::toResponse).toList();
    }

    @Transactional
    public StudentResponse update(Long id, StudentRequest request){
        Student existingStudent = studentService.findById(id);
        existingStudent.setName(request.name());
        existingStudent.setRegNumber(request.regNumber());
        existingStudent.setGpa(request.gpa());

        Student updatedStudent = studentService.save(existingStudent);
        return studentMapper.toResponse(updatedStudent);
    }

    @Transactional
    public void delete(Long id){
        studentService.delete(id);
    }
}
