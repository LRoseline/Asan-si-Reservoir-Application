package com.tfriends.reservoirapplication;

import java.util.List;

import com.tfriends.domain.Voirs;
import com.tfriends.domain.WeatherVO;
import com.tfriends.service.VoirService;
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
    private VoirService v;
    
    @GetMapping("/weather/{no}")
    public WeatherVO WeatherInfo(@PathVariable("no") int no) {
        WeatherService w = new WeatherService();
        return w.WeatherLoad(no);
    }
    
    @GetMapping("/reservoir/{no}")
    public Voirs VoirDetail(@PathVariable("no") int no) {
        return v.VoirClick(no);
    }

    @GetMapping("/j/{judy}")
    public List<Voirs> ListLocal(@PathVariable("judy") String crygor) {
        List<Voirs> vo = v.VoirLists(crygor);

        return vo;
    }
}
