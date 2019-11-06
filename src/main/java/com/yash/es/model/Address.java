package com.yash.es.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonPropertyOrder({ "cityName", "state", "country", "pin","mobileNo"})
@JsonInclude(Include.NON_EMPTY)
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9124197658231120933L;

	@NotNull
	private String cityName;

	@NotNull
	private String state;

	@NotNull
	private String country;

	@NotNull
	private String pin;

	@NotNull
	private String mobileNo;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Address [cityName=" + cityName + ", state=" + state + ", country=" + country + ", pin=" + pin
				+ ", mobileNo=" + mobileNo + "]";
	}


}
