package com.example.demo.exception;

public class StudentDoesNotExistException extends StudentNotFoundException {
    public StudentDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Student doesn't exist";
    }
}