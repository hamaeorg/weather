package com.weather.weather.model;

import java.sql.Timestamp;

public class WeatherData {

    private double latitude;
    private double longitude;
    private String city;
    private double temperature;
    private Timestamp time;

    public WeatherData(double latitude, double longitude, String city, double temperature, Timestamp time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.temperature = temperature;
        this.time = time;
    }

    public static TemperatureBuilder builder() {
        return new TemperatureBuilder();
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getCity() {
        return this.city;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public static class TemperatureBuilder {
        private double latitude;
        private double longitude;
        private String city;
        private double temperature;
        private Timestamp time;

        TemperatureBuilder() {
        }

        public TemperatureBuilder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public TemperatureBuilder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public TemperatureBuilder city(String city) {
            this.city = city;
            return this;
        }

        public TemperatureBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public TemperatureBuilder time(Timestamp time) {
            this.time = time;
            return this;
        }

        public WeatherData build() {
            return new WeatherData(latitude, longitude, city, temperature, time);
        }

        public String toString() {
            return "Temperature.TemperatureBuilder(latitude=" + this.latitude + ", longitude=" + this.longitude + ", city=" + this.city + ", temperature=" + this.temperature + ", time=" + this.time + ")";
        }
    }
}
