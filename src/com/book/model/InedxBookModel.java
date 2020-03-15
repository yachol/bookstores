package com.book.model;

import java.io.Serializable;
import java.util.List;

public class InedxBookModel<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer pageSize;
	private Long total;
	private Integer totalPage;
	private Integer currentPage;
	private List<T> list;
	private String keyWord;
	public InedxBookModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InedxBookModel(Integer pageSize, Long total, Integer totalPage, Integer currentPage, List<T> list,
			String keyWord) {
		super();
		this.pageSize = pageSize;
		this.total = total;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.list = list;
		this.keyWord = keyWord;
	}
	@Override
	public String toString() {
		return "PageModel [pageSize=" + pageSize + ", total=" + total + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", list=" + list + ", keyWord=" + keyWord + "]";
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
