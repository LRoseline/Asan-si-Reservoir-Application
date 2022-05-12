package com.tfriends.domain;

import java.util.List;

import lombok.Data;

@Data
public class VoirAPI {
    private String jurisdiction;

    private List<VoirDaily> daily;
}