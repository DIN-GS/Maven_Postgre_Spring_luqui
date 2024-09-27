package com.example.demo.exception;

public class InvalidArgumentsException extends Exception{
    public InvalidArgumentsException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Arguments are null or invalid";
    }
}
