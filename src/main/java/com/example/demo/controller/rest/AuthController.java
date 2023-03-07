package com.example.demo.controller.rest;

import com.example.demo.config.TokenProvider;
import com.example.demo.controller.dto.StudentDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;

    private  final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody StudentDTO studentDTO){

        studentService.createStudent(studentDTO.getName(),studentDTO.getEmail(), studentDTO.getPassword());

        return ResponseEntity.ok("User " + studentDTO.getName() + " was created");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody StudentDTO studentDTO) {

        if (studentService.validateCredentials(studentDTO.getEmail(), studentDTO.getPassword())){
            try {
                studentDTO.setId(studentService.findStudentEntityByEmail(studentDTO.getEmail()).getId());
            } catch (Exception e){

            }
            return ResponseEntity.ok(tokenProvider.createToken(studentDTO.getId()));

        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
