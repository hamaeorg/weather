package com.weather.weather.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Repository
public class WeatherRepository {

    @Value( "${apiKey}" )
    private String apiKey;
    @Value( "${url}" )
    private String applicationUrl;

    public double getTemperatureByCoordinates(double lat, double lon) {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("%s?lat=%s&lon=%s&appid=%s", applicationUrl, lat, lon, apiKey); // Max 60 calls per minute
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        String data = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        return parseTemperatureJsonData(data);
    }

    public double parseTemperatureJsonData(String responseBody){
        double temp = 0;
        try {
            ObjectMapper objectmapper = new ObjectMapper();
            JsonNode jsonNode = objectmapper.readTree(responseBody);
            double tempInKelvins = jsonNode.get("main").get("temp").asDouble();
            temp = kelvinToCelcius(tempInKelvins);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return temp;
    }

    private double kelvinToCelcius(double kelvins) {
        BigDecimal temperature = new BigDecimal(kelvins - 273.15);
        return temperature.setScale(0, RoundingMode.HALF_UP).doubleValue();
    }
}
