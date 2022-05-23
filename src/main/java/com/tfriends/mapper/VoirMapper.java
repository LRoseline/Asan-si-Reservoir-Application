package com.tfriends.mapper;

import java.util.List;

import com.tfriends.domain.VoirAPI;
import com.tfriends.domain.Voirs;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VoirMapper {
    public List<Voirs> vlist(@Param("jurisdiction")String jurisdiction);

    public VoirAPI vinfo(@Param("no")int no);
}
