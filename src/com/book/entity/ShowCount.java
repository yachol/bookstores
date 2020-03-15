package com.book.entity;

import java.math.BigDecimal;

public class ShowCount {
	private BigDecimal numberTotal;
	private BigDecimal bpriceTotal;
	public ShowCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowCount(BigDecimal numberTotal, BigDecimal bpriceTotal) {
		super();
		this.numberTotal = numberTotal;
		this.bpriceTotal = bpriceTotal;
	}
	@Override
	public String toString() {
		return "ShowCount [numberTotal=" + numberTotal + ", bpriceTotal=" + bpriceTotal + "]";
	}
	public BigDecimal getNumberTotal() {
		return numberTotal;
	}
	public void setNumberTotal(BigDecimal numberTotal) {
		this.numberTotal = numberTotal;
	}
	public BigDecimal getBpriceTotal() {
		return bpriceTotal;
	}
	public void setBpriceTotal(BigDecimal bpriceTotal) {
		this.bpriceTotal = bpriceTotal;
	}
	
}
