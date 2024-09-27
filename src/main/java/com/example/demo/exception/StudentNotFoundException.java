package com.example.demo.exception;

import java.util.function.Supplier;

public class StudentNotFoundException extends Exception{
    public StudentNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Student wasn't found";
    }
}
