package com.tfriends.reservoirapplication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.tfriends.domain.VoirAPI;
import com.tfriends.domain.VoirDaily;
import com.tfriends.domain.Voirs;
import com.tfriends.domain.WeatherVO;
import com.tfriends.service.SettingService;
import com.tfriends.service.VoirService;
import com.tfriends.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RestCon {

    @Autowired
    private VoirService v;

    @Autowired
    private WeatherService w;

    @Autowired
    private SettingService setting;
    
    @GetMapping("/weather/{no}")
    public WeatherVO WeatherInfo(@PathVariable("no") int no) {
        return w.WeatherLoad(no);
    }
    
    @GetMapping("/reservoir/{no}")
    public VoirAPI VoirDetail(@PathVariable("no") int no) {
        return v.VoirClick(no);
    }

    @PostMapping("/j/{judy}")
    public List<Voirs> ListLocal(@PathVariable("judy") String crygor) {
        List<Voirs> vo = v.VoirLists(crygor);

        return vo;
    }

    @PostMapping("/vw/{location}")
    public WeatherVO WeatherJ(@PathVariable("location") String location) {
        return w.WeatherX(location);
    }

    @PostMapping("/voir/{no}")
    public VoirAPI VoirAPI(@PathVariable("no") int no) throws Exception {
        VoirAPI api = v.VoirClick(no);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		Calendar [] dates = {Calendar.getInstance(), Calendar.getInstance()};
		dates[0].add(Calendar.DATE, -7);

		String [] datesdf = {sdf.format(dates[0].getTime()), sdf.format(dates[1].getTime())};

		String URLComp = "http://apis.data.go.kr/B552149/reserviorWaterLevel/reservoirlevel/?serviceKey="+setting.SettingLoad("datagokr").getValue()+"&pageNo=1&numOfRows="+10+"&fac_code="+api.getCode()+"&date_s="+datesdf[0]+"&date_e="+datesdf[1];

        DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document doc = documentBuilder.parse(URLComp);
        Element element = doc.getDocumentElement();

        NodeList [] voir = {
            element.getElementsByTagName("fac_name"),
            element.getElementsByTagName("check_date"),
            element.getElementsByTagName("rate"),
            element.getElementsByTagName("water_level")
        };

        List<VoirDaily> appmedia = new ArrayList<VoirDaily>();
        for (int i = 0; i < 8; i++) {
            VoirDaily days = new VoirDaily();
            
            days.setDate(voir[1].item(i).getFirstChild().getNodeValue());
            days.setRate(voir[2].item(i).getFirstChild().getNodeValue());
            days.setWlevel(voir[3].item(i).getFirstChild().getNodeValue());
            appmedia.add(i, days);
            api.setDaily(appmedia);
        }

        return api;
    }

    @GetMapping("/voirget/{no}")
    public VoirAPI VoirAPIGet(@PathVariable("no") int no) throws Exception {
        
        VoirAPI vo = v.VoirClick(no);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		Calendar [] dates = {Calendar.getInstance(), Calendar.getInstance()};
		dates[0].add(Calendar.DATE, -7);

		String [] datesdf = {sdf.format(dates[0].getTime()), sdf.format(dates[1].getTime())};

		String xml;
		String URLComp = "http://apis.data.go.kr/B552149/reserviorWaterLevel/reservoirlevel/?serviceKey="+setting.SettingLoad("datagokr")+"&pageNo=1&numOfRows=10&fac_code="+vo.getCode()+"&date_s="+datesdf[0]+"&date_e="+datesdf[1];

        URL url = new URL(URLComp);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setConnectTimeout(10000);
        http.setUseCaches(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
        }
        xml = sb.toString();
        br.close();
        http.disconnect();
        
        if (xml != null) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory
	                .newInstance();
	        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	
	        InputStream is =new ByteArrayInputStream(xml.getBytes());
	        Document doc = documentBuilder.parse(is);
	        Element element = doc.getDocumentElement();

            NodeList [] voir = {
                element.getElementsByTagName("fac_name"),
                element.getElementsByTagName("check_date"),
                element.getElementsByTagName("rate"),
                element.getElementsByTagName("water_level")
            };

            List<VoirDaily> appmedia = new ArrayList<VoirDaily>();
            for (int i = 0; i < 8; i++) {
                VoirDaily days = new VoirDaily();
                
                days.setDate(voir[1].item(i).getFirstChild().getNodeValue());
                days.setRate(voir[2].item(i).getFirstChild().getNodeValue());
                days.setWlevel(voir[3].item(i).getFirstChild().getNodeValue());
                appmedia.add(i, days);
                vo.setDaily(appmedia);
            }
        }
        return vo;
    }
}
