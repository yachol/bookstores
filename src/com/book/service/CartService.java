package com.book.service;

import java.util.List;

import com.book.entity.Cart;
import com.book.entity.ShowCount;

public interface CartService {

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 */
	boolean addCart(Cart cart);

	ShowCount findCount(String uid);

	boolean changeBnumber(String bid, String bnumber);

	List<Cart> showCart(String uid);

	boolean deleteCart(String[] array);

	List<Cart> confirmOrder(String[] ids);

}
