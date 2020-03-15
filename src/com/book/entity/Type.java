package com.book.entity;

import java.io.Serializable;

public class Type implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long id;
private String type;
private Long preId;
public Type() {
	super();
	// TODO Auto-generated constructor stub
}
public Type(Long id, String type, Long preId) {
	super();
	this.id = id;
	this.type = type;
	this.preId = preId;
}

@Override
public String toString() {
	return "Type [id=" + id + ", type=" + type + ", preId=" + preId + "]";
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Long getPreId() {
	return preId;
}
public void setPreId(Long preId) {
	this.preId = preId;
}

}
