package com.tfriends.domain;

import java.util.List;

import lombok.Data;

@Data
public class VoirAPI {
    private String reservoirname;
    
    private String jurisdiction;

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