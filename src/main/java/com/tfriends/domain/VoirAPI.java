package com.tfriends.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class VoirAPI {
    public int no;
    public String resername;
    private String jurisdiction;

    @JsonIgnore
    private Long code;

    private double lat;
    
    private double lon;

    // 홍수위
    private String flood;

    // 만수위
    private String full;

    // 사수위
    private String defense;

    // 총 저수량
    private String total;

    // 유효저수량
    private String effec;

    private List<VoirDaily> daily;
}