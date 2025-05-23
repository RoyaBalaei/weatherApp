package com.example.weatherApp.service;

import com.example.weatherApp.model.Weather;
import com.example.weatherApp.repository.WeatherRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository repository;

    private String url="https://api.openweathermap.org/data/2.5/weather?q=tehran&units=metric&appid=a53d16e84998f7a5ae36f1bc252a132c";
    private OkHttpClient client;
    private Response response;


    @Scheduled(fixedRate = 60000)
    public void getTemp(){
        Request request=new Request.Builder().url(url).build();
        client=new OkHttpClient();
        try {
            response=client.newCall(request).execute();
            JSONObject obj=new JSONObject(response.body().string());
            JSONObject mainObj=obj.getJSONObject("main");
            int temp=mainObj.getInt("temp");
            Weather weather=new Weather();
            weather.setTemp(temp);
            weather.setDate(new Date(System.currentTimeMillis()));
            weather.setCityName("tehran");
            save(weather);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Weather save(Weather weather){
        return repository.save(weather);
    }

    public List<Weather> getAllWeather() {
        return repository.findAll();
    }
}
