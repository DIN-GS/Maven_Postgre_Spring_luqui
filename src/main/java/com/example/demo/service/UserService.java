package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(String email, String password){

        String encodedPassword = bCryptPasswordEncoder.encode(password);

        userRepository.createUser(email, password);
    }

    public String getPassword(String email){
        return userRepository.getPassword(email);
    }

    public boolean validateCredentials(String email, String password){
        String encodedPassword = userRepository.getPassword(email);
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}
