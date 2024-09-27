package com.example.demo.service.interfaces;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.InvalidArgumentsException;
import com.example.demo.exception.StudentDoesNotExistException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.entity.StudentEntity;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface Service {

    public StudentEntity createStudent(StudentEntity studentEntity) throws InvalidArgumentsException;

    public StudentEntity updateStudentById(StudentDTO studentDTO, Long id) throws StudentNotFoundException, StudentDoesNotExistException;



    public StudentEntity findStudentEntityById(Long id) throws StudentNotFoundException;

    public List<StudentEntity> findAll();

    @Transactional
    public void deleteStudentEntityById(Long id);
}
