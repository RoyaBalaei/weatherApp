package com.example.weatherApp.controller;

import com.example.weatherApp.model.Weather;
import com.example.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<Weather> weatherList(){
        return weatherService.getAllWeather();
    }
}
