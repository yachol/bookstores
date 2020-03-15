package com.book.service.impl;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.book.dao.CartDao;
import com.book.dao.impl.CartDaoImpl;
import com.book.entity.Cart;
import com.book.entity.ShowCount;
import com.book.service.CartService;

public class CartServiceImpl implements CartService {
	private static CartDao cdi = new CartDaoImpl();

	/*
	 * @Override public boolean addCart(String bid, String uid, String bnumber) {
	 * BookDao bdi = new BookDaoImpl(); try { Book book = bdi.queryBookByid(bid); //
	 * book.get } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return false; }
	 */

	@Override
	public boolean addCart(Cart cart) {
		
			int rows = 0;
			try {
				Cart exist = cdi.queryShopByuidpid(cart.getUid(), cart.getBid());
				if (exist == null) {
					rows = cdi.addCart(cart);
				} else {
					rows=cdi.updateShopById(exist.getId(),cart.getBnumber());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rows > 0 ? true : false;
		
	}

	@Override
	public ShowCount findCount(String uid) {
		try {
			BigDecimal numberTotal=cdi.queryBnumberTotalByUid(uid);
			BigDecimal bpriceTotal=cdi.queryBpriceTotalByUid(uid);
			return new ShowCount(numberTotal, bpriceTotal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean changeBnumber(String id, String bnumber) {
		
		try {
			return cdi.updateBnumberByid(id,bnumber)>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cart> showCart(String uid) {
		try {
			return cdi.queryCartByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteCart(String[] array) {
		try {
			
			 return cdi.deleteCartById(array)==array.length?true:false ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cart> confirmOrder(String[] ids) {
		try {
			return cdi.queryCartByIds(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

}
