package com.book.model;

public class BnameNumber {
	private String bname;
	private Integer number;
	public BnameNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BnameNumber(String bname, Integer number) {
		super();
		this.bname = bname;
		this.number = number;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BnameNumber [bname=" + bname + ", number=" + number + "]";
	}
	
}
