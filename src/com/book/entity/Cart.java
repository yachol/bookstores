package com.book.entity;

import java.io.Serializable;
import java.math.BigDecimal;



public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer bid;

	private String bimg;

	private String bname;

	private Integer bnumber;

	private BigDecimal bprice;

	private Integer uid;

	public Cart() {
	}

	public Cart(Integer bid, String bimg, String bname, Integer bnumber, BigDecimal bprice, Integer uid) {
		super();
		this.bid = bid;
		this.bimg = bimg;
		this.bname = bname;
		this.bnumber = bnumber;
		this.bprice = bprice;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", bid=" + bid + ", bimg=" + bimg + ", bname=" + bname + ", bnumber=" + bnumber
				+ ", bprice=" + bprice + ", uid=" + uid + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBimg() {
		return bimg;
	}

	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getBnumber() {
		return bnumber;
	}

	public void setBnumber(Integer bnumber) {
		this.bnumber = bnumber;
	}

	public BigDecimal getBprice() {
		return bprice;
	}

	public void setBprice(BigDecimal bprice) {
		this.bprice = bprice;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}