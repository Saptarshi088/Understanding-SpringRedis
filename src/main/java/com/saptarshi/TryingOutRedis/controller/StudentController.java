package com.saptarshi.TryingOutRedis.controller;


import com.saptarshi.TryingOutRedis.dto.AddStudentRequest;
import com.saptarshi.TryingOutRedis.entity.Student;
import com.saptarshi.TryingOutRedis.repository.StudentRepository;
import com.saptarshi.TryingOutRedis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody AddStudentRequest request) {

        var student = studentRepository.save(Student.builder()
                        .name(request.getName())
                        .marks(request.getMarks())
                .build());
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequest request) {
        return ResponseEntity.ok(studentService.update(id,request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        return ResponseEntity.status(202).body(studentService.deleteStudent(id));
    }
}
