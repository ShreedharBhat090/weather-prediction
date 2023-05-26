package com.weather.prediction.weekly.serviceImpl;

import com.weather.prediction.Models.Prediction;
import com.weather.prediction.PredictionApplication;
import com.weather.prediction.configurations.ServerProperties;
import com.weather.prediction.utils.Utils;
import com.weather.prediction.weekly.models.WeatherMap;
import com.weather.prediction.weekly.service.WeeklyPredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class WeeklyPredictionServiceImpl implements WeeklyPredictionService {

    private static final Logger logger = LoggerFactory.getLogger(WeeklyPredictionServiceImpl.class);
    private final ServerProperties serverProperties;

    @Autowired
    public WeeklyPredictionServiceImpl(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }
    @Override
    @Cacheable(value="prediction", key = "#params.get('q')")
    public Prediction getPrediction(Map<String,String> params) {
        RestTemplate restTemplate = new RestTemplate();
        //todo: need to improve for dynamic params
        String resourceUrl = serverProperties.getApplication().get("url")+"?q={q}&appid={appid}&units={units}&cnt={cnt}";
        params.put("appid",serverProperties.getApplication().get("appid"));
        params.put("units",serverProperties.getApplication().get("units"));

        ResponseEntity<Prediction> response
                = restTemplate.getForEntity(resourceUrl, Prediction.class,params);

        Prediction prediction = addComment(response.getBody());

        //logging example, can be used to crawl/build analytics using different cloud based apps ex: splunk
        logger.info(String.valueOf(prediction));
        return prediction;
    }
    private Prediction addComment(Prediction prediction) {
        //todo: use enum for weather type
        prediction.getList().stream().forEach(
                item -> {
                    if (item.getWeather().get(0).getMain().equalsIgnoreCase("Rain")) {
                        item.setComment("Carry umbrella");
                    } else if (item.getWeather().get(0).getMain().equalsIgnoreCase("Thunderstorms")) {
                        item.setComment("Don’t step out! A Storm is brewing!");
                    } else if (item.getWeather().get(0).getMain().equalsIgnoreCase("Snow")) {
                        item.setComment("It's snowing outside! Do you want to build a snowman?");
                        //added for fun :)
                    }
                    if (item.getMain().getTemp() > 40) {
                        item.setComment("Use sunscreen lotion");
                    }
                    if (Utils.convertMeterspersecToMilesperhour(item.getWind().getSpeed()) > 10) {
                        if(item.getComment()!=null) {
                            item.setComment(item.getComment()+ " and It’s too windy, watch out!");
                        }else{
                            item.setComment("It’s too windy, watch out!");
                        }
                    }
                    if(item.getComment()==null || item.getComment().isEmpty()){
                            item.setComment("Enjoy the hours!");
                    }
                    logger.info(String.valueOf(prediction));
                }
        );
        return prediction;
    }
}
