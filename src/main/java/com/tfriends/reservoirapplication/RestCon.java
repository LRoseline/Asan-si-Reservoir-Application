package com.tfriends.reservoirapplication;

import com.tfriends.domain.WeatherVO;
import com.tfriends.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestCon {

    @Autowired
    private WeatherService w;
    
    @GetMapping("/weather/{no}")
    public WeatherVO WeatherInfo(@PathVariable int no) {
        return w.WeatherLoad(no);
    }
}
