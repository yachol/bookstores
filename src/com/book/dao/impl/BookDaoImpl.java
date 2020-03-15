package com.book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.book.core.util.DBUtil;
import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.entity.Carousel;
import com.book.entity.Cart;

public class BookDaoImpl implements BookDao {

	@Override
	public Long findCount(String keyWord, Long typeid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		if(typeid==0l) {
			String sql ="select count(*) from t_book where bname like ?";
			return qr.query(sql,new ScalarHandler<Long>(),"%"+keyWord+"%");
		}
		String sql ="select count(*) from t_book where bname like ? and pre_type_id=?";
		return qr.query(sql,new ScalarHandler<Long>(),"%"+keyWord+"%",typeid);
	}

	@Override
	public List<Book> findByPage(Integer currentPage, int pageSize, String keyWord, Long typeid) throws SQLException {
		System.out.println(typeid);
		int start=(currentPage-1)*pageSize;
		QueryRunner qr = DBUtil.getQueryRunner();
		if(typeid==0l) {
			String sql ="select id,img,bname,author,current_price currentPrice from t_book where bname like ?limit ?,? ";
			System.out.println("0l");
			return qr.query(sql, new BeanListHandler<Book>(Book.class),"%"+keyWord+"%",start,pageSize);
		}
		String sql ="select id,img,bname,author,current_price,pre_type_id preTypeId,current_price currentPrice from t_book where bname like ? and pre_type_id=? limit ?,? ";
		System.out.println("sdjlf");
		return qr.query(sql, new BeanListHandler<Book>(Book.class),"%"+keyWord+"%",typeid,start,pageSize);
	}

	@Override
	public List<Carousel> queryCarousel() throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,img,bid from t_carousel";
		return qr.query(sql, new BeanListHandler<Carousel>(Carousel.class));
	}

	@Override
	public Book queryBookByid(String bookid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,img,bname,author,country,publish_date publishDate,publisher,original_price originalPrice,current_price currentPrice,isbn,number,istao,info from t_book where id=?";
		return qr.query(sql, new BeanHandler<Book>(Book.class),bookid);
	}

	@Override
	public Book queryBookNumberByBname(String bid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,img,bname,author,country,publish_date publishDate,publisher,original_price originalPrice,current_price currentPrice,isbn,number,istao,info from t_book where id=?";
		return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
	}

	@Override
	public List<Book> queryBooksByid(String[] bids) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		 StringBuilder sql = new StringBuilder("select number,bname from t_book where id=?");
		for(int i=0;i<bids.length-1;i++) {
			sql.append("or id=?");
		}
		return qr.query(sql.toString(),new BeanListHandler<Book>(Book.class),bids);
	}

	
	public int updateNumber(Cart cart,Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="update t_book set number=number-? where id=?";
		return qr.update(sql,cart.getBnumber(),cart.getBid());
	}

	@Override
	public boolean updateNumber(List<Cart> cartList, Connection conn) throws SQLException {
		int row=0;
		for (Cart cart : cartList) {
			row+=updateNumber(cart,conn);
		}
		return row==cartList.size()?true:false;
	}

}
