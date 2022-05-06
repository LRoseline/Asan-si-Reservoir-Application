package com.tfriends.mapper;

import com.tfriends.domain.WeatherVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeatherMapper {
    public WeatherVO allweather(@Param("no")int no);
}
