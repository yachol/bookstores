package com.book.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long sid;
	private String orderNumber;
	private BigDecimal totalPrice;
	private Date orderTime;
	private Date prayTime;
	private Long uid;
	private byte state;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Long id, Long sid, String orderNumber, BigDecimal totalPrice, Date orderTime, Long uid, byte state) {
		super();
		this.id = id;
		this.sid = sid;
		this.orderNumber = orderNumber;
		this.totalPrice = totalPrice;
		this.orderTime = orderTime;
		this.uid = uid;
		this.state = state;
	}
	public Date getPrayTime() {
		return prayTime;
	}
	public void setPrayTime(Date prayTime) {
		this.prayTime = prayTime;
	}
	public Order(Long sid, String orderNumber, BigDecimal totalPrice, Date orderTime,Date prayTime, Long uid, byte state) {
		super();
		this.sid = sid;
		this.orderNumber = orderNumber;
		this.totalPrice = totalPrice;
		this.orderTime = orderTime;
		this.prayTime = prayTime;
		this.uid = uid;
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", sid=" + sid + ", orderNumber=" + orderNumber + ", totalPrice=" + totalPrice
				+ ", orderTime=" + orderTime + ", uid=" + uid + ", state=" + state + "]";
	}

	
}
