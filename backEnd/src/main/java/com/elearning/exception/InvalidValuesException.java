package com.elearning.exception;

public class InvalidValuesException extends RuntimeException {

    public InvalidValuesException() {
    }

    public InvalidValuesException(String message) {
        super(message);
    }

}
