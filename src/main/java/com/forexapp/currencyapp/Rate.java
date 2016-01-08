package com.forexapp.currencyapp;

public class Rate {
	
	String id;
	String Name;
	String Rate;
	String Date;
	String Time;
	String Ask;
	String Bid;
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setRate(String rate) {
		Rate = rate;
	}
	public void setDate(String date) {
		Date = date;
	}
	public void setTime(String time) {
		Time = time;
	}
	public void setAsk(String ask) {
		Ask = ask;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return Name;
	}
	public String getRate() {
		return Rate;
	}
	public String getDate() {
		return Date;
	}
	public String getTime() {
		return Time;
	}
	public String getAsk() {
		return Ask;
	}
	public String getBid() {
		return Bid;
	}
	
	
	

}
