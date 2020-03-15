package com.book.service;

import java.util.List;

import com.book.entity.Book;
import com.book.entity.Carousel;
import com.book.model.BnameNumber;
import com.book.model.InedxBookModel;

public interface BookService {
	/**
	 * 分页
	 * 
	 * @param bigtypeid
	 * @param currentPage
	 * @param keyWord
	 * @param pageSize
	 * @return
	 */
	InedxBookModel<Book> findOnePage(String bigtypeid, String currentPage, String keyWord, int pageSize);

	/**
	 * 
	 * @param bigtypeid
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	InedxBookModel<Book> findOnePage(String bigtypeid, String currentPage, int pageSize);

	/**
	 * 轮播图
	 * 
	 * @return
	 */
	List<Carousel> findCarousel();

	Book findBook(String bookid);

	int queryCount(String bid);

	List<BnameNumber> findBooks(String[] bids);

}
