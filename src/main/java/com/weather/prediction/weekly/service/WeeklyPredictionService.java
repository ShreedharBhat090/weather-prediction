package com.weather.prediction.weekly.service;

import com.weather.prediction.Models.Prediction;

import java.util.Map;

public interface WeeklyPredictionService {
    public abstract Prediction getPrediction( Map< String, String> params);
}
