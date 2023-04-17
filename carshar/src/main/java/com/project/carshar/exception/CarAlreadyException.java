package com.project.carshar.exception;

public class CarAlreadyException extends RuntimeException{
    private String nameException;
    public CarAlreadyException(String nameException) {
        this.nameException = nameException;
    }
}
