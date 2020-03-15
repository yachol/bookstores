package com.book.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.book.entity.Book;
import com.book.entity.Carousel;
import com.book.entity.Cart;

public interface BookDao {
	/**
	 * 查询总数
	 * 
	 * @param keyWord
	 * @param typeid
	 * @return
	 * @throws SQLException
	 */
	Long findCount(String keyWord, Long typeid) throws SQLException;

	/**
	 * 条件查询书籍
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param keyWord
	 * @param typeid
	 * @return
	 * @throws SQLException
	 */
	List<Book> findByPage(Integer currentPage, int pageSize, String keyWord, Long typeid) throws SQLException;

	/**
	 * 轮播图
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Carousel> queryCarousel() throws SQLException;

	/**
	 * 展示图书详情
	 * 
	 * @param bookid
	 * @return
	 * @throws SQLException 
	 */
	Book queryBookByid(String bookid) throws SQLException;

	Book queryBookNumberByBname(String bid) throws SQLException;

	List<Book> queryBooksByid(String[] bids) throws SQLException;

	boolean updateNumber(List<Cart> cartList, Connection conn) throws SQLException;

}
