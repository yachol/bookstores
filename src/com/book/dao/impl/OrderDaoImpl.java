package com.book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.book.core.util.DBUtil;
import com.book.dao.OrderDao;
import com.book.entity.Order;
import com.book.entity.OrderBook;

public class OrderDaoImpl implements OrderDao{

	@Override
	public int insertOrder(Order order,Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		String sql = "insert into t_order(uid,sid,order_number,order_time,pay_time,total_price,state) value(?,?,?,?,?,?,?)";
		return qr.update(conn, sql, order.getUid(),order.getSid(),order.getOrderNumber(),order.getOrderTime(),order.getPrayTime(),order.getTotalPrice(),order.getState());
	}

	@Override
	public int insertOrderBook(List<OrderBook> orderBookList, Connection conn) throws SQLException {
		int row=0;
		for (OrderBook orderBook : orderBookList) {
			row+=insertOrderBook(orderBook, conn);
		}
		return row;
	}

	@Override
	public int insertOrderBook(OrderBook orderBook, Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		String sql = "insert into t_order_books (order_number,bimg,bname,price,bnumber,pay_price,pay_time) value(?,?,?,?,?,?,?)";
		return qr.update(conn, sql, orderBook.getOrderNumber(),orderBook.getBimg(),orderBook.getBname(),orderBook.getPrice(),orderBook.getBnumber(),orderBook.getPayPrice(),orderBook.getPayTime());
	}

	@Override
	public Long findCount(String keyword, String orderby, String start, String end, int pagesize,Long uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		
		
		if("".equals(start)||start==null||"".equals(end)||end==null) {
			String sql ="select count(*) from t_order where order_number like ? and uid=? order by pay_time "+orderby;
			return qr.query(sql,new ScalarHandler<Long>(),"%"+keyword+"%",uid);
		}
		String sql ="select count(*) from t_order where order_number like ? and order_time BETWEEN ? AND ? order by pay_time "+orderby;
		return qr.query(sql,new ScalarHandler<Long>(),"%"+keyword+"%",uid,start,end);

	}

	@Override
	public List<Order> findByPage(String keyword, String orderby, String start, String end, int currentpage,
			int pagesize,Long uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		int start1=(currentpage-1)*pagesize;
		System.out.println(currentpage);
		System.out.println("key"+keyword);
		System.out.println(start1);
		System.out.println(pagesize);
		/*
		 * if(!"".equals(keyword)) { String sql
		 * ="select id,uid,sid,order_number orderNumber,order_time orderTime,total_price totalPrice,state,pay_time prayTime from t_order where order_number like ? and uid=? limit ?,?"
		 * ; return qr.query(sql,new
		 * BeanListHandler<Order>(Order.class),"%"+keyword+"%",uid,start1,pagesize); }
		 */
		if("".equals(start)||start==null||"".equals(end)||end==null) {
			String sql ="select id,uid,sid,order_number orderNumber,order_time orderTime,total_price totalPrice,state,pay_time prayTime from t_order where order_number like ? and uid=? order by pay_time "+orderby+" limit ?,?";
			return qr.query(sql,new BeanListHandler<Order>(Order.class),"%"+keyword+"%",uid,start1,pagesize);
		}
		String sql ="select id,uid,sid,order_number orderNumber,order_time orderTime,total_price totalPrice,state,pay_time prayTime  from t_order where order_number like ? and uid=? and order_time BETWEEN ? AND ? order by pay_time "+orderby+" limit ?,?";
		return qr.query(sql,new BeanListHandler<Order>(Order.class),"%"+keyword+"%",uid,start,end,start1,pagesize);
	}

	@Override
	public List<OrderBook> queryBookByOnumber(String orderNumber) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,order_number orderNumber,bimg,bname,price,bnumber,pay_price payPrice,pay_time payTime from t_order_books where order_number=?";
		return qr.query(sql,new BeanListHandler<OrderBook>(OrderBook.class),orderNumber);
	}

	@Override
	public int delOrderByOnumber(String[] onumber, Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		StringBuilder sb = new StringBuilder("delete from t_order where order_number=? ");
		for (String string : onumber) {
			System.out.println(string);
		}
		for(int i=0;i<onumber.length-1;i++) {
			sb.append("or order_number=?");
		}
		return qr.update(conn, sb.toString(),onumber);
	}

	@Override
	public int delorderBookByByOnumber(String[] onumber, Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		StringBuilder sb = new StringBuilder("delete from t_order_books where order_number=? ");
		for(int i=0;i<onumber.length-1;i++) {
			sb.append("or order_number=?");
		}
		return qr.update(conn, sb.toString(),onumber);
	}
	
}
