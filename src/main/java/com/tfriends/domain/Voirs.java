package com.tfriends.domain;

import lombok.Data;

@Data
public class Voirs {
    public int no;
    public String resername;
    private String jurisdiction;
    private Long code;
    private double lat;
    private double lon;
}
