package com.tfriends.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherVO extends DustStationVO{
	@JsonIgnore
	private int no;

    private String location;

	private double lat;
	private double lon;

	private String duststation;

	private Long sunrise;
	private Long sunset;

	private String weather0;
	private Long temp0;
	private Long humid0;
	
	private String weather1;
	private Long temp1;
	private Long time1;

	private String weather2;
	private Long temp2;
	private Long time2;

	private String weather3;
	private Long temp3;
	private Long time3;

	private String weather4;
	private Long temp4;
	private Long time4;

	private String weather5;
	private Long temp5;
	private Long time5;
}
