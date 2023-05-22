package com.weather.prediction.exception;

public class CountFoundException extends RuntimeException {

    public CountFoundException() {
        super(String.format("Count cannot be empty"));
    }
}
