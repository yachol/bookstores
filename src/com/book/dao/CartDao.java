package com.book.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.book.entity.Cart;

public interface CartDao {

	int addCart(Cart cart) throws SQLException;

	Cart queryShopByuidpid(Integer uid, Integer bid) throws SQLException;

	int updateShopById(Long id, Integer bnumber) throws SQLException;

	BigDecimal queryBnumberTotalByUid(String uid) throws SQLException;

	BigDecimal queryBpriceTotalByUid(String uid) throws SQLException;

	int updateBnumberByid(String id, String bnumber) throws SQLException;

	List<Cart> queryCartByUid(String uid) throws SQLException;

	int deleteCartById(String[] array) throws SQLException;

	List<Cart> queryCartByIds(String[] ids) throws SQLException;

	int deleteCartById(String[] ids, Connection conn) throws SQLException;

	Cart queryCartById(String id) throws SQLException;

}
