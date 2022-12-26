package com.tfriends.reservoirapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import com.tfriends.domain.DustStationVO;
import com.tfriends.domain.WeatherVO;
import com.tfriends.service.SettingService;
import com.tfriends.service.WeatherService;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherSync {

    @Autowired
    private WeatherService service;

    @Autowired
    private SettingService setting;

    public void WeatherRain() throws Exception {
        // SimpleDateFormat dtformat = new SimpleDateFormat("YYYY-MM-dd a hh:mm", Locale.KOREA);
        // SimpleDateFormat forecast = new SimpleDateFormat("MM.dd", Locale.KOREA);

        // for (int a = 0; a < 27; a++) {
            int a = 11;
            WeatherVO vo = service.WeatherReady(a+1);
    
            String OnecallURL = "http://api.openweathermap.org/data/2.5/onecall?&lang=kr&units=metric&lat="+vo.getLat()+"&lon="+vo.getLon()+"&appid="+setting.SettingLoad("openweathermap2").getValue();
    
            BufferedReader weatherbf;
            URL urlweather = new URL(OnecallURL);
            String weatherline = "";
            String weatherresult = "";
            // SimpleDateFormat ForeTimeStamp = new SimpleDateFormat("a hh:mm", Locale.KOREA);
            
            weatherbf = new BufferedReader(new InputStreamReader(urlweather.openStream()));		
            while((weatherline=weatherbf.readLine())!=null) {
                weatherresult = weatherresult.concat(weatherline);
            }
    
            JSONObject weatherobj = new JSONObject(weatherresult);
            
            // Weather Area -----------------------------
            JSONObject currentObject = weatherobj.getJSONObject("current");
            
            // Common
            Long DTime = Long.parseLong(currentObject.getLong("dt")+"000");
    
            // Long SunriseJ = Long.parseLong(currentObject.getLong("sunrise")+"000");
            // Long SunsetJ = Long.parseLong(currentObject.getLong("sunset")+"000");

            Long Sunrise = currentObject.getLong("sunrise");
            Long Sunset = currentObject.getLong("sunset");
    
            Long temp = currentObject.getLong("temp");
            Long humidity = currentObject.getLong("humidity");
    
            JSONArray weatherarray = currentObject.getJSONArray("weather");
            JSONObject warray = weatherarray.getJSONObject(0);
    
            String main = warray.getString("main");
            // String descript = warray.getString("description");
            String icon = warray.getString("icon");

            double raindrop = 0;
            if (main.equals("Thunderstorm") || main.equals("Drizzle") || main.equals("Rain")) {
                JSONObject rain1h = currentObject.getJSONObject("rain");
                raindrop = rain1h.getDouble("1h");
                System.out.println("강수량 : "+raindrop+"mm");
            }
    
            if (main.equals("Snow")) {
                try {
                    JSONObject snow1h = currentObject.getJSONObject("snow");
                    raindrop = snow1h.getDouble("1h");
                    System.out.println("적설량 : "+raindrop+"mm");
                } catch (Exception e) {
                    JSONObject rain1h = currentObject.getJSONObject("rain");
                    raindrop = rain1h.getDouble("1h");
                    System.out.println("강수량 : "+raindrop+"mm");
                }
            }

            vo.setDrop(raindrop);

            vo.setSunrise(Sunrise);
            vo.setSunset(Sunset);

            vo.setWeather0(icon);
            vo.setTemp0(temp);
            vo.setHumid0(humidity);
    
            // Daily Area
            JSONArray dailyArray = weatherobj.getJSONArray("daily");
            
            for (int i = 0; i < 6; i++) {
                JSONObject dailyObject = dailyArray.getJSONObject(i);
    
                DTime = dailyObject.getLong("dt");
    
                // SunriseJ = Long.parseLong(dailyObject.getLong("sunrise")+"000");
                // SunsetJ = Long.parseLong(dailyObject.getLong("sunset")+"000");
        
                JSONObject tempObj = dailyObject.getJSONObject("temp");
    
                // Long dailyTempMin = tempObj.getLong("min");
                // Long dailyTempMax = tempObj.getLong("max");
    
                // Long dailyTempMorning = tempObj.getLong("morn");
                Long dailyTempDay = tempObj.getLong("day");
                // Long dailyTempEvening = tempObj.getLong("eve");
                // Long dailyTempNight = tempObj.getLong("night");
    
                humidity = dailyObject.getLong("humidity");
        
                weatherarray = dailyObject.getJSONArray("weather");
                warray = weatherarray.getJSONObject(0);
        
                main = warray.getString("main");
                // descript = warray.getString("description");
                icon = warray.getString("icon");
    
                // System.out.println("예보일자 : "+dtformat.format(DTime));
                // System.out.println("일출시간 : "+ForeTimeStamp.format(SunriseJ));
                // System.out.println("일몰시간 : "+ForeTimeStamp.format(SunsetJ));
                // System.out.println("최저최고 : "+dailyTempMin+" ~ "+dailyTempMax);
                // System.out.println("기온변화 : "+dailyTempMorning+"=>"+dailyTempDay+"=>"+dailyTempEvening+"=>"+dailyTempNight);
                // System.out.println("현재습도 : "+humidity+"%");
                // System.out.println("현재날씨 : "+main);
                // System.out.println("날씨설명 : "+descript);
                // System.out.println("현재기호 : "+icon);

                raindrop = 0;

                if (main.equals("Thunderstorm") || main.equals("Drizzle") || main.equals("Rain")) {
                    raindrop = dailyObject.getDouble("rain");
                    System.out.println("강수량 : "+raindrop+"mm");
                } else if (main.equals("Snow")) {
                    raindrop = dailyObject.getDouble("snow");
                    System.out.println("적설량 : "+raindrop+"mm");
                }
                
                if (i == 1) {
                    vo.setTime1(DTime);
                    vo.setWeather1(icon);
                    vo.setTemp1(dailyTempDay);
                    vo.setDrop1(raindrop);
                }
                if (i == 2) {
                    vo.setTime2(DTime);
                    vo.setWeather2(icon);
                    vo.setTemp2(dailyTempDay);
                    vo.setDrop2(raindrop);
                }
                if (i == 3) {
                    vo.setTime3(DTime);
                    vo.setWeather3(icon);
                    vo.setTemp3(dailyTempDay);
                    vo.setDrop3(raindrop);
                }
                if (i == 4) {
                    vo.setTime4(DTime);
                    vo.setWeather4(icon);
                    vo.setTemp4(dailyTempDay);
                    vo.setDrop4(raindrop);
                }
                if (i == 5) {
                    vo.setTime5(DTime);
                    vo.setWeather5(icon);
                    vo.setTemp5(dailyTempDay);
                    vo.setDrop5(raindrop);
                }
            }
            service.WUpdate(vo);
            weatherbf.close();
        // }
        System.out.println("날씨정보 업데이트");
    }

    public void DustStation() throws Exception {
		for (int d = 0; d < 7; d++) {
            DustStationVO vo = service.DustLoad(d+1);
			String station = URLEncoder.encode(vo.getName(), "UTF-8");
            String dustcom = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?returnType=json&numOfRows=1&pageNo=1&stationName="+station+"&dataTerm=DAILY&ver=1.0&serviceKey="+setting.SettingLoad("datagokr").getValue();
            URL urldust = new URL(dustcom);
            
            String dustline = "";
            String dustresult = "";
            BufferedReader dustbf = new BufferedReader(new InputStreamReader(urldust.openStream()));
            
            dustline = dustbf.readLine();
            dustresult = dustresult.concat(dustline);
            
            JSONObject dustobj = new JSONObject(dustresult);
            JSONObject DustResponse = dustobj.getJSONObject("response");
            JSONObject DustBody = DustResponse.getJSONObject("body");
            JSONArray DustArray = DustBody.getJSONArray("items");
            JSONObject LocalObject = DustArray.getJSONObject(0);
            
            // Dust Area --------------------------------
            String pm10v;
            String pm25v;
			String pm10g;
			String pm25g;
            
            try {
                pm10v = LocalObject.getString("pm10Value").toString();
            } catch (Exception e1) {
                pm10v = "-";
            }
    
            try {
                pm25v = LocalObject.getString("pm25Value").toString();
            } catch (Exception e1) {
                pm25v = "-";
            }
            
			try {
				pm10g = LocalObject.getString("pm10Grade").toString();
			} catch (Exception e1) {
                pm10g = "-";
            }

			try {
				pm25g = LocalObject.getString("pm25Grade").toString();
			} catch (Exception e1) {
				pm25g = "-";
            }
    
            vo.setPm10g(pm10g);
            vo.setPm25g(pm25g);
            vo.setPm10v(pm10v);
            vo.setPm25v(pm25v);
            service.DustUpdate(vo);

            dustbf.close();
        }
        System.out.println("미세먼지 업데이트");
    }
}