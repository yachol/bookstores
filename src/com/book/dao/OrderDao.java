package com.book.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.book.entity.Order;
import com.book.entity.OrderBook;

public interface OrderDao {

	int insertOrder(Order order, Connection conn) throws SQLException;

	int insertOrderBook(List<OrderBook> orderBookList, Connection conn) throws SQLException;
	int insertOrderBook(OrderBook orderBook, Connection conn) throws SQLException;

	Long findCount(String keyword, String orderby, String start, String end, int pagesize, Long uid) throws SQLException;

	List<Order> findByPage(String keyword, String orderby, String start, String end, int currentpage, int pagesize, Long uid) throws SQLException;

	List<OrderBook> queryBookByOnumber(String orderNumber) throws SQLException;

	int delOrderByOnumber(String[] onumber, Connection conn) throws SQLException;

	int delorderBookByByOnumber(String[] onumber, Connection conn) throws SQLException;
}
