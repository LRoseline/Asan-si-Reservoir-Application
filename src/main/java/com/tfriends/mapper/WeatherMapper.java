package com.tfriends.mapper;

import java.util.List;

import com.tfriends.domain.LocationVO;
import com.tfriends.domain.WeatherVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeatherMapper {
    public WeatherVO allweather(@Param("no") int paramInt);
  
    public WeatherVO voirweather(@Param("location") String paramString);
    
    public List<LocationVO> dominions();
}
