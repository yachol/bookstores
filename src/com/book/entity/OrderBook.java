package com.book.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class OrderBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderNumber;
	private Long id;
	private String bimg;
	private String bname;
	private BigDecimal price;
	private Integer bnumber;
	private BigDecimal payPrice;
	private Date payTime;
	private Long uid;
	public OrderBook() {
	}
	
	



	
	public OrderBook(String orderNumber, String bimg, String bname, BigDecimal price, Integer bnumber,
			BigDecimal payPrice, Date payTime, Long uid) {
		super();
		this.orderNumber = orderNumber;
		this.bimg = bimg;
		this.bname = bname;
		this.price = price;
		this.bnumber = bnumber;
		this.payPrice = payPrice;
		this.payTime = payTime;
		this.uid = uid;
	}






	public OrderBook(String orderNumber, Long id, String bimg, String bname, BigDecimal price, Integer bnumber,
			BigDecimal payPrice, Date payTime, Long uid) {
		super();
		this.orderNumber = orderNumber;
		this.id = id;
		this.bimg = bimg;
		this.bname = bname;
		this.price = price;
		this.bnumber = bnumber;
		this.payPrice = payPrice;
		this.payTime = payTime;
		this.uid = uid;
	}






	public String getOrderNumber() {
		return orderNumber;
	}






	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getBnumber() {
		return bnumber;
	}






	public void setBnumber(Integer bnumber) {
		this.bnumber = bnumber;
	}






	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}






	@Override
	public String toString() {
		return "OrderBook [orderNumber=" + orderNumber + ", id=" + id + ", bimg=" + bimg + ", bname=" + bname
				+ ", price=" + price + ", bnumber=" + bnumber + ", payPrice=" + payPrice + ", payTime=" + payTime
				+ ", uid=" + uid + "]";
	}
	

}