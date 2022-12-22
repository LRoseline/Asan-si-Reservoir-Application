package com.tfriends.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames=true)
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
	private double drop;
	private int maxdrop;
	
	private String weather1;
	private double drop1;
	private Long temp1;
	private Long time1;

	private String weather2;
	private double drop2;
	private Long temp2;
	private Long time2;

	private String weather3;
	private double drop3;
	private Long temp3;
	private Long time3;

	private String weather4;
	private double drop4;
	private Long temp4;
	private Long time4;

	private String weather5;
	private double drop5;
	private Long temp5;
	private Long time5;
}
