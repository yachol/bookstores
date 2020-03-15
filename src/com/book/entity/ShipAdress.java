package com.book.entity;

import java.io.Serializable;

public class ShipAdress implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String adress;

	private byte isdefault;

	private String mobile;

	private String name;

	private String postCode;

	private Long uid;

	public ShipAdress() {
	}

	public ShipAdress(String adress, byte isdefault, String mobile, String name, String postCode, Long uid) {
		super();
		this.adress = adress;
		this.isdefault = isdefault;
		this.mobile = mobile;
		this.name = name;
		this.postCode = postCode;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "ShipAdress [id=" + id + ", adress=" + adress + ", isdefault=" + isdefault + ", mobile=" + mobile
				+ ", name=" + name + ", postCode=" + postCode + ", uid=" + uid + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public byte getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(byte isdefault) {
		this.isdefault = isdefault;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}