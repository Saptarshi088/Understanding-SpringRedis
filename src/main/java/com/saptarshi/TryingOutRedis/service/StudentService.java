package com.saptarshi.TryingOutRedis.service;

import com.saptarshi.TryingOutRedis.dto.AddStudentRequest;
import com.saptarshi.TryingOutRedis.entity.Student;
import com.saptarshi.TryingOutRedis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Cacheable("students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Cacheable(cacheNames = "stud_by_id",key = "#id")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
    }

    @CachePut(cacheNames = "stud_by_id",key="#result.id")
    public Student update(Long id, AddStudentRequest request) {
        var student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        student.setName(request.getName());
        student.setMarks(request.getMarks());
        return studentRepository.save(student);
    }

    @CacheEvict(cacheNames = "stud_by_id")
    public Student deleteStudent(Long id) {
        var deleted = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        studentRepository.delete(deleted);
        return deleted;
    }
}
