package com.weather.prediction.weekly.controllers;

import com.weather.prediction.Models.Prediction;
import com.weather.prediction.exception.CityNotFoundException;
import com.weather.prediction.exception.CountFoundException;
import com.weather.prediction.weekly.service.WeeklyPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class WeeklyPredictionController {
    private final WeeklyPredictionService weeklyPredictionService;

    @Autowired
    public WeeklyPredictionController(WeeklyPredictionService weeklyPredictionService) {
        this.weeklyPredictionService = weeklyPredictionService;
    }

    @GetMapping("/weather-prediction")
    public ResponseEntity<Prediction> getPrediction(@RequestParam Map<String, String> allRequestParams) {
        if (!allRequestParams.containsKey("q") || allRequestParams.get("q").isBlank()) {
            throw new CityNotFoundException();
        }else if (!allRequestParams.containsKey("cnt") || allRequestParams.get("cnt").isBlank()) {
            throw new CountFoundException();
        }
        return new ResponseEntity<>(weeklyPredictionService.getPrediction(allRequestParams), HttpStatus.OK);
    }
}
