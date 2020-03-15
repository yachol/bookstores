package com.book.model;

import java.io.Serializable;

public class BookModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String bname;
	private String img;
	private String author;
	private String country;
	private String publishDate;
	private String publisher;
	private Double originalPrice;
	private Double currentPrice;
	private String isbn;
	private Integer number;
	private String istao;
	private String info;


	public BookModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookModel(Long id, String bname, String img, String author, String country, String publishDate,
			String publisher, Double originalPrice, Double currentPrice, String isbn, Integer number, String istao,
			String info) {
		super();
		this.id = id;
		this.bname = bname;
		this.img = img;
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
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getIstao() {
		return istao;
	}

	public void setIstao(String istao) {
		this.istao = istao;
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




}
