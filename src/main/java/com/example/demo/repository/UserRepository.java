package com.example.demo.repository;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    private Map<String, String> userMap = new HashMap<>();

    public void createUser(String email, String password){
        userMap.put(email, password);
    }

    public String getPassword(String email){
        return userMap.get(email);
    }
}
