package com.tfriends.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class LocationVO {
	@JsonIgnore
	private int no;

    private String location;
	private double lat;
	private double lon;
}
