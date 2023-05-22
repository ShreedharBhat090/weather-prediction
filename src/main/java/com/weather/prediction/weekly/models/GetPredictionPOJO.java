package com.weather.prediction.weekly.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetPredictionPOJO {
    //@NotBlank(message = "Location is mandatory")
    private String q;
    private String cnt;
    private String appid;
    private String units;


}
