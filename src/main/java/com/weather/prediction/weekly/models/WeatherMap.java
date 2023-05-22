package com.weather.prediction.weekly.models;

import com.weather.prediction.Models.*;
import lombok.Data;

import java.util.List;
@Data
public class WeatherMap {
    long dt;
    long visibility;
    long pop;
    String dt_txt;
    String comment;
    Main main;
    List<Weather> weather;
    Clouds clouds;
    Wind wind;
    Sys sys;
}
