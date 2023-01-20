package com.example.demo.service;

import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public void createStudent(String name, String email){

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setEmail(email);
        studentRepository.save(studentEntity);
    }

    public List<StudentEntity> findAll(){
        return studentRepository.findAll();
    }
}
