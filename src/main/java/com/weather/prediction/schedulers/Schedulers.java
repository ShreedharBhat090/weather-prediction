package com.weather.prediction.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedulers {
    @Autowired
    CacheManager cacheManager;
    @Scheduled(fixedRate = 1000*60*60*3)
    public void evictWeatherPrediction() {
        cacheManager.getCache("prediction").clear();
    }
}
