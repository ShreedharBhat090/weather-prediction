package com.weather.prediction.Models;

import lombok.Data;

@Data
public class Main {
    double temp;
    double feels_like;
    double temp_min;
    double temp_max;
    long pressure;
    long sea_level;
    long grnd_level;
    long humidity;
     long temp_kf;
}
