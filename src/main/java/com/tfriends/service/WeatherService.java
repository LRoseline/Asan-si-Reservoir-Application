package com.tfriends.service;

import java.util.List;

import com.tfriends.domain.LocationVO;
import com.tfriends.domain.WeatherVO;
import com.tfriends.mapper.WeatherMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    
    @Autowired
    private WeatherMapper m;

    public WeatherVO WeatherLoad(int no) {
        return m.allweather(no);
    }

    public WeatherVO WeatherX(String location) {
        return m.voirweather(location);
    }

    public List<LocationVO> LoadDomins() {
        return m.dominions();
    }
}
