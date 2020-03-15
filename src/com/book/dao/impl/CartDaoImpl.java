package com.book.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.book.core.util.DBUtil;
import com.book.dao.CartDao;
import com.book.entity.Cart;

public class CartDaoImpl implements CartDao{

	@Override
	public int addCart(Cart cart) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "insert into t_cart (bid,uid,bimg,bname,bprice,bnumber) value(?,?,?,?,?,?)";
		return qr.update(sql,cart.getBid(),cart.getUid(),cart.getBimg(),cart.getBname(),cart.getBprice(),cart.getBnumber());
	}

	@Override
	public Cart queryShopByuidpid(Integer uid, Integer bid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql="select id from t_cart where uid=? and bid=?";
		return qr.query(sql, new BeanHandler<Cart>(Cart.class), uid,bid);
	}

	@Override
	public int updateShopById(Long id, Integer bnumber) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="update t_cart set bnumber=bnumber+? where id=?";
		return qr.update(sql,bnumber,id);
	}

	@Override
	public BigDecimal queryBnumberTotalByUid(String uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql="select sum(bnumber) from t_cart where uid=?";
		return qr.query(sql, new ScalarHandler<BigDecimal>(), uid);
	}

	@Override
	public BigDecimal queryBpriceTotalByUid(String uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql="select sum(bprice*bnumber) from t_cart where uid=?";
		return qr.query(sql, new ScalarHandler<BigDecimal>(), uid);
	}

	@Override
	public int updateBnumberByid(String id, String bnumber) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="update t_cart set bnumber=? where id=?";
		return qr.update(sql,bnumber,id);
	}

	@Override
	public List<Cart> queryCartByUid(String uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql="select id,bid,uid,bimg,bname,bprice,bnumber from t_cart where uid=?";
		return qr.query(sql, new BeanListHandler<Cart>(Cart.class), uid);
	}

	@Override
	public int deleteCartById(String[] array) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		 StringBuilder sql = new StringBuilder("delete from t_cart where id=?");
		for(int i=0;i<array.length-1;i++) {
			sql.append("or id=?");
		}
		return qr.update(sql.toString(), array);
	}

	@Override
	public List<Cart> queryCartByIds(String[] ids) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		 StringBuilder sql = new StringBuilder("select id,bid,uid,bimg,bname,bprice,bnumber from t_cart where id=?");
		for(int i=0;i<ids.length-1;i++) {
			sql.append("or id=?");
		}
		return qr.query(sql.toString(), new BeanListHandler<Cart>(Cart.class), ids);
	}

	@Override
	public int deleteCartById(String[] ids, Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		 StringBuilder sql = new StringBuilder("delete from t_cart where id=?");
		for(int i=0;i<ids.length-1;i++) {
			sql.append("or id=?");
		}
		return qr.update(conn, sql.toString(), ids);
	}

	@Override
	public Cart queryCartById(String id) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql="select id,bid,uid,bimg,bname,bprice,bnumber from t_cart where id=?";
		return qr.query(sql, new BeanHandler<Cart>(Cart.class), id);
	}


}
