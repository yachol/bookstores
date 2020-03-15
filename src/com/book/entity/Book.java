package com.book.entity;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String bname;
	private String img;
	private Long preTypeId;
	private String author;
	private String country;
	private Date publishDate;
	private String publisher;
	private Double originalPrice;
	private Double currentPrice;
	private String isbn;
	private Integer number;
	private byte istao;
	private String info;
	private Long typeId;
	private Integer view_time;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Book(Long id, String bname, String img, Long preTypeId, String author, String country, Date publishDate,
			String publisher, Double originalPrice, Double currentPrice, String isbn, Integer number, byte istao,
			String info, Long typeId, Integer view_time) {
		super();
		this.id = id;
		this.bname = bname;
		this.img = img;
		this.preTypeId = preTypeId;
		this.author = author;
		this.country = country;
		this.publishDate = publishDate;
		this.publisher = publisher;
		this.originalPrice = originalPrice;
		this.currentPrice = currentPrice;
		this.isbn = isbn;
		this.number = number;
		this.istao = istao;
		this.info = info;
		this.typeId = typeId;
		this.view_time = view_time;
	}





	@Override
	public String toString() {
		return "Book [id=" + id + ", bname=" + bname + ", img=" + img + ", preTypeId=" + preTypeId + ", author="
				+ author + ", country=" + country + ", publishDate=" + publishDate + ", publisher=" + publisher
				+ ", originalPrice=" + originalPrice + ", currentPrice=" + currentPrice + ", isbn=" + isbn + ", number="
				+ number + ", istao=" + istao + ", info=" + info + ", typeId=" + typeId + ", view_time=" + view_time
				+ "]";
	}



	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public byte getIstao() {
		return istao;
	}

	public void setIstao(byte istao) {
		this.istao = istao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getPreTypeId() {
		return preTypeId;
	}

	public void setPreTypeId(Long preTypeId) {
		this.preTypeId = preTypeId;
	}

	public Integer getView_time() {
		return view_time;
	}

	public void setView_time(Integer view_time) {
		this.view_time = view_time;
	}

}
