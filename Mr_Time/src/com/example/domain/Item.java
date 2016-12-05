package com.example.domain;

public class Item {
	
	

	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getKind() {
		return kind;
	}



	public void setKind(String kind) {
		this.kind = kind;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getWeek() {
		return week;
	}



	public void setWeek(String week) {
		this.week = week;
	}



	public int getSjd() {
		return sjd;
	}



	public void setSjd(int sjd) {
		this.sjd = sjd;
	}



	private  String time;
	private String kind;
	private String year;
	private String month;
	private String day;
	private String week;
	private int sjd;

	
	
	public String toString() {
		return "Item [time=" + time + " kind=" + kind+"]";
	}
	
	
	
	public Item(String time,String kind,String year,String month, String day,String week,int sjd) {
		super();
		this.time = time;
		this.kind = kind;
		this.year = year;
		this.month = month;
		this.day = day;
		this.week = week;
		this.sjd = sjd;
		
	
	}

	

	}


