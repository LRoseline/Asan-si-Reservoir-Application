package com.tfriends.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DustStationVO {
    @JsonIgnore
    private int no;

    @JsonIgnore
    private String name;

    @JsonIgnore
    private String type;
    private String pm10v;
    private String pm10g;
    private String pm25v;
    private String pm25g;
}
