package com.forexapp.currencyapp;

import java.math.BigDecimal;

public class RegisteredDevicesDetails {
	
	private String token;
	private BigDecimal rate;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


}
