package com.weather.prediction.Models;

import lombok.Data;

@Data
public class Weather {
    int id;
    String main;
    String description;
    String icon;
}
