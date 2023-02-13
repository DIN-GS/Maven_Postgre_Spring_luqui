package com.example.demo.service;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import jakarta.annotation.Nullable;
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

    public StudentEntity updateStudent(StudentDTO studentDTO, Long id) throws StudentNotFoundException{
        StudentEntity studentEntity = StudentMapper.INSTANCE.toEntity(studentDTO);
        StudentEntity student =
                studentRepository.findStudentEntityById(id);
        if (student == null) throw new StudentNotFoundException("Student wasn't found");
        student.setId(id);
        student.setEmail(studentEntity.getEmail());
        student.setName(studentEntity.getName());
        student.setPhoto(student.getPhoto());
        studentRepository.save(studentEntity);
        return  student;
    }

    public StudentEntity addStudentEntity(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }


    public StudentEntity findStudentEntityById(Long id) throws StudentNotFoundException{
        if (studentRepository.findStudentEntityById(id) != null)
        return studentRepository.findStudentEntityById(id);
        else throw new StudentNotFoundException("Student wasn't found");
    }

    public List<StudentEntity> findAll(){
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteStudentEntityById(Long id){
         studentRepository.deleteStudentEntityById(id);
    }
}
