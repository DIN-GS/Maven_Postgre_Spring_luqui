package com.example.demo.controller;

import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mappers.StudentMapper;
import com.example.demo.repository.entity.StudentEntity;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<Void> deleteStudentEntityById(@PathVariable Long studentId){
        studentService.deleteStudentEntityById(studentId);
        return ResponseEntity.noContent().build(); //"Student " + /*studentEntity.getName() + */" was deleted";
    }

    /*List<StudentDTO>*/
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<StudentDTO> studentsDTOs = StudentMapper.INSTANCE.studentsToDTO(studentService.findAll());
        return new ResponseEntity<>(studentsDTOs, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        Optional<StudentDTO> studentDTO = Optional.ofNullable(StudentMapper.INSTANCE.toDTO(studentService.findStudentEntityById(studentId)));
        return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/students/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE/*consumes = "application/json"*/)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        StudentEntity studentEntity =   StudentMapper.INSTANCE.toEntity(studentDTO);
        StudentDTO response  = StudentMapper.INSTANCE.toDTO(studentService.createStudent(studentEntity));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

        /*studentService.addStudentEntity(studentEntity);
        return "Student " + studentEntity.getName() + " was updated";*/
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO  studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        try {
            studentEntity = studentService.updateStudent(studentDTO);
        } catch (StudentNotFoundException exception){
            System.out.println("Crashed");
        }

        StudentDTO response = StudentMapper.INSTANCE.toDTO(studentEntity);
        return  ResponseEntity.ok(response);
    }

}
