package com.tfriends.mapper;

import java.util.List;

import com.tfriends.domain.LocationVO;
import com.tfriends.domain.WeatherVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeatherMapper {
    public WeatherVO allweather(@Param("no")int no);

    public List<LocationVO> dominions();
}
