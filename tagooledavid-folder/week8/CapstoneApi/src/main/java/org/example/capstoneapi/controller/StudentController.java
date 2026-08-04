package org.example.capstoneapi.controller;


import jakarta.validation.Valid;
import org.example.capstoneapi.dto.StudentRequest;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.facade.StudentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade){
        this.studentFacade = studentFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@Valid @RequestBody StudentRequest request){
        return studentFacade.create(request);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id){
        return studentFacade.getById(id);
    }

    @GetMapping
    public List<StudentResponse> getAll(){
        return studentFacade.getAll();
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        return studentFacade.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        studentFacade.delete(id);
    }

}
