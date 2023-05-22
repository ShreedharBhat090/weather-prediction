package com.weather.prediction.Models;

import lombok.Data;

@Data
public class City {
    Long id;
    String name;
    String country;
    long population;
    String timezone;
    long sunrise;
    long sunset;
}
