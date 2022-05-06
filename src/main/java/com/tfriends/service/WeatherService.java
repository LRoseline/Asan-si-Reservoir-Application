package com.tfriends.service;

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
}
