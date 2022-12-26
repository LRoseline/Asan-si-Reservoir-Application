package com.tfriends.service;

import java.util.Arrays;
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
        WeatherVO vo =  m.voirweather("number","",no);

		double [] arrdrop = {vo.getDrop1(),vo.getDrop2(),vo.getDrop3(),vo.getDrop4(),vo.getDrop5()};
        
        double max = Arrays.stream(arrdrop).max().getAsDouble();
        int maxdrop = Double.valueOf(max).intValue();
        vo.setMaxdrop(maxdrop);
        
		return vo;
    }

    public WeatherVO WeatherX(String type, String location, int no) {
        return m.voirweather(type, location, no);
    }

    public List<LocationVO> LoadDomins() {
        return m.dominions();
    }
}
