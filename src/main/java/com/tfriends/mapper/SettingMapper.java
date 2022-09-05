package com.tfriends.mapper;

import com.tfriends.domain.SettingVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingMapper {
    public SettingVO selectVal(String type);
}
