package com.weather.weather.controller;

import com.weather.weather.model.WeatherData;
import com.weather.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherData weather(@RequestParam(value = "city", defaultValue = "Tallinn") String city) {
        WeatherData temp = weatherService.getWeatherDataByCoordinates(59.43, 24.75);
        return temp;
    }
}
