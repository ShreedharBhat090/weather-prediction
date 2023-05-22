package com.weather.prediction.exception;

public class CityNotFoundException  extends RuntimeException {

    public CityNotFoundException() {
        super(String.format("City cannot be empty"));
    }
}
