package com.example.demo.controller;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.InvalidArgumentsException;
import com.example.demo.exception.StudentDoesNotExistException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.entity.StudentEntity;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Component
@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {

    private final StudentService studentService;

    /*@DeleteMapping("/studentDelete/{studentId}")
    public String deleteStudentEntityById(@PathVariable Long studentId){
        StudentEntity studentEntity = studentService.findStudentEntityById(studentId);
        studentService.deleteStudentEntityById(studentId);
        return "Student " + studentEntity.getName() + " is deleted";
    }*/

    @RequestMapping(value="/studentDelete/{studentId}", method={RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<StudentDTO> deleteStudentEntityById(@PathVariable Long studentId){
        try {
            studentService.findStudentEntityById(studentId);
        } catch (StudentNotFoundException exception){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        studentService.deleteStudentEntityById(studentId);
        return ResponseEntity.noContent().build(); //"Student " + /*studentEntity.getName() + */" was deleted";
    }



    /*List<StudentDTO>*/
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<StudentDTO> studentsDTOs = StudentMapper.INSTANCE.studentsToDTO(studentService.findAll());
        if (studentsDTOs != null)  return new ResponseEntity<>(studentsDTOs, HttpStatus.OK);
        else return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        try {
            Optional<StudentDTO> studentDTO = Optional.ofNullable(StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(studentId)));
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        } catch (StudentNotFoundException exception){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }


    }

    @PostMapping(value = "/students/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE/*consumes = "application/json"*/)
    public  ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        StudentEntity studentEntity =   StudentMapper.INSTANCE.toEntity(studentDTO);
        try{
            StudentDTO response  = StudentMapper.INSTANCE.toDTO(studentService.createStudent(studentEntity));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (InvalidArgumentsException exception){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        /*studentService.addStudentEntity(studentEntity);
        return "Student " + studentEntity.getName() + " was updated";*/
    }



    @PutMapping("/students/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO  studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        try {
            studentEntity = studentService.updateStudentById(studentDTO, id);
            return  new ResponseEntity<>(StudentMapper.INSTANCE.toDTO(studentEntity) , HttpStatus.OK);
        } catch (StudentNotFoundException exception){
            System.out.println("Crashed");
            return new ResponseEntity<>(studentDTO, HttpStatus.NOT_FOUND);
        }



    }

}
