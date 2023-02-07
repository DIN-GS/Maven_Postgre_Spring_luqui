package com.example.demo.service;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import jakarta.transaction.Transactional;
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

    public StudentEntity createStudent(StudentEntity studentEntity){
        if(studentEntity.getName() != null && studentEntity.getEmail() != null ) studentRepository.save(studentEntity);
        return studentEntity;
    }

    public StudentEntity updateStudent(StudentDTO studentDTO) throws StudentNotFoundException{
        StudentEntity studentEntity = StudentMapper.INSTANCE.toEntity(studentDTO);
        StudentEntity student =
                studentRepository.findStudentEntityById(studentEntity.getId());
        if (student == null) throw new StudentNotFoundException("Student wasn't found");

        student.setEmail(studentEntity.getEmail());
        student.setName(studentEntity.getName());
        student.setPhoto(studentEntity.getPhoto());
        return  student;
    }

    public StudentEntity addStudentEntity(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }

    public StudentEntity findStudentEntityById(Long id){
        return studentRepository.findStudentEntityById(id);
    }

    public List<StudentEntity> findAll(){
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteStudentEntityById(Long id){
         studentRepository.deleteStudentEntityById(id);
    }
}
