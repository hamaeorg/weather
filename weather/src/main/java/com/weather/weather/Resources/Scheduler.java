package com.weather.weather.Resources;

import com.weather.weather.service.WeatherService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Scheduler {

    private final AtomicInteger testGauge;
    private final WeatherService weatherService;


    public Scheduler(MeterRegistry meterRegistry, WeatherService weatherService) {
        testGauge = meterRegistry.gauge("temperature", new AtomicInteger(0));
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRateString = "5000", initialDelayString = "0")
    public void schedulingTask() {
        testGauge.set((int) weatherService.getWeatherDataByCoordinates(59.43, 24.75).getTemperature());
    }

}
