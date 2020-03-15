package com.book.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String uname;
	private String pwd;
	private String email;
	private String mobile;
	private String company;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String uname, String pwd) {
		super();
		this.uname = uname;
		this.pwd = pwd;
	}

	public User(String uname, String pwd, String email, String mobile, String company) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.email = email;
		this.mobile = mobile;
		this.company = company;
	}

	public User(Long id, String uname, String pwd, String email, String mobile, String company) {
		super();
		this.id = id;
		this.uname = uname;
		this.pwd = pwd;
		this.email = email;
		this.mobile = mobile;
		this.company = company;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", email=" + email + ", mobile=" + mobile
				+ ", company=" + company + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
