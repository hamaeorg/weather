package com.weather.weather.service;

import com.weather.weather.model.WeatherData;
import com.weather.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    @Autowired
    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    public WeatherData getWeatherDataByCoordinates(double lat, double lon) {
        String city = "Tallinn";  //TODO: add some logic or to get coordinates output from city input
        double temperature = repository.getTemperatureByCoordinates(lat, lon);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return new WeatherData(lat, lon, city, temperature, time);
    }
}