package com.example.demo.service;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.InvalidArgumentsException;
import com.example.demo.exception.StudentDoesNotExistException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.entity.StudentEntity;
import com.example.demo.service.StudentService;
import com.example.demo.service.interfaces.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FakeStudentService extends StudentService  {

    private HashMap<Long, StudentEntity> students = new HashMap<>();


    private Long l = 1L;

    private StudentEntity studentEntity;

    public FakeStudentService(StudentRepository studentRepository){
        super(studentRepository);
        initialize();
    }

    @Override
    public StudentEntity findStudentEntityById(Long id) throws StudentNotFoundException {
        if (students.containsKey(id)){
            return students.get(id);
        }  else throw new StudentNotFoundException("Student wasn't found");
    }

    public List<StudentEntity> findAll(){
        List<StudentEntity> studentList = new ArrayList<>();
        students.entrySet().forEach(entry -> studentList.add(entry.getValue()));
        return studentList;
    }

    public StudentEntity createStudent(StudentEntity studentEntity) throws InvalidArgumentsException{
        if(studentEntity.getName() != null && studentEntity.getEmail() != null ) {
            students.put(l,studentEntity);
            l++;
        }
        else throw new InvalidArgumentsException("Arguments are null or invalid");
        return studentEntity;
    }

    @Override
    public StudentEntity updateStudentById(StudentDTO studentDTO, Long id) throws StudentDoesNotExistException {
        if (students.containsKey(id)){
            studentEntity = students.get(id);
            studentEntity.setName(studentDTO.getName());
            studentEntity.setEmail(studentDTO.getEmail());
            studentEntity.setId(id);
            studentEntity.setPhoto(studentEntity.getPhoto());
        } else throw new StudentDoesNotExistException("Student doesn't exist");
        return studentEntity;
    }

    private void initialize(){
        studentEntity = new StudentEntity();
        studentEntity.setId(2L);
        studentEntity.setName("Max");
        studentEntity.setEmail("max@gmail.com");
        students.put(2L,studentEntity);
        studentEntity = new StudentEntity();
        studentEntity.setId(3L);
        studentEntity.setName("Stas");
        studentEntity.setEmail("stas@gmail.com");
        students.put(3L,studentEntity);
        studentEntity = new StudentEntity();
        studentEntity.setId(4L);
        studentEntity.setName("Artem");
        studentEntity.setEmail("artem@gmail.com");
        students.put(4L,studentEntity);
    }

    public boolean deleteStudentEntityById(Long student_id)  {
            students.remove(student_id);
            return true;
    }
}
