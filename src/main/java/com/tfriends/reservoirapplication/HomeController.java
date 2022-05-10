package com.tfriends.reservoirapplication;

import com.tfriends.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class HomeController {

    @Autowired
    private WeatherService w;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String requestMethodName(Model mdl) {
        mdl.addAttribute("volt", w.LoadDomins());
        
        return "index";
    }
    
}
