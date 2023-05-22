package com.weather.prediction.weekly.controllers;

import com.weather.prediction.Models.Prediction;
import com.weather.prediction.exception.CityNotFoundException;
import com.weather.prediction.exception.CountFoundException;
import com.weather.prediction.weekly.service.WeeklyPredictionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeeklyPredictionControllerTest {
    @InjectMocks
    WeeklyPredictionController weeklyPredictionController;
    @Mock
    Prediction prediction;
    @Mock
    WeeklyPredictionService weeklyPredictionService;

    @Test
    public void testAddEmployee() {
        Map<String, String> map = new HashMap<>();
        map.put("q", "london");
        map.put("cnt", "10");
        when(weeklyPredictionService.getPrediction(map)).thenReturn(prediction);
        ResponseEntity<Prediction> responseEntity = weeklyPredictionController.getPrediction(map);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCityNotFoundException() {
        Map<String, String> map = new HashMap<>();
        map.put("cnt", "10");
        Assertions.assertThrows(CityNotFoundException.class,
                () -> weeklyPredictionController.getPrediction(map));
    }

    @Test
    public void testCountFoundException() {
        Map<String, String> map = new HashMap<>();
        map.put("q", "london");
        Assertions.assertThrows(CountFoundException.class,
                () -> weeklyPredictionController.getPrediction(map));
    }
}
