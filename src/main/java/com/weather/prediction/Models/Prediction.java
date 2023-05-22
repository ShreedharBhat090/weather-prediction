package com.weather.prediction.Models;

import com.weather.prediction.weekly.models.WeatherMap;
import lombok.Data;

import java.util.List;
@Data
public class Prediction {
    int cod;
    String message;
    int cnt;
    List<WeatherMap> list;
}
