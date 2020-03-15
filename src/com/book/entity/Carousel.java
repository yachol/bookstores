package com.book.entity;

public class Carousel {
private Integer id;
private String img;
public Carousel() {
	super();
	// TODO Auto-generated constructor stub
}
public Carousel(Integer id, String img) {
	super();
	this.id = id;
	this.img = img;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}

}
