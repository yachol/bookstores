package com.book.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.book.core.util.DBUtil;
import com.book.dao.BookDao;
import com.book.dao.CartDao;
import com.book.dao.OrderDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.CartDaoImpl;
import com.book.dao.impl.OrderDaoImpl;
import com.book.entity.Cart;
import com.book.entity.Order;
import com.book.entity.OrderBook;
import com.book.model.InedxBookModel;
import com.book.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public boolean addOrder(String[] ids, String sid) {
		CartDao csi = new CartDaoImpl();
		Connection conn = DBUtil.getConnection();
		try {
			List<Cart> cartList = csi.queryCartByIds(ids);
			BigDecimal totalPrice = new BigDecimal("0");
			Integer uid = cartList.get(0).getUid();
			Date orderTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 将当前时间转化为订单号
			String orderNumber = sdf.format(orderTime) + uid;
			List<OrderBook> orderBookList = new ArrayList<OrderBook>();
			for (Cart cart : cartList) {
				BigDecimal a = new BigDecimal(cart.getBnumber() + "");
				BigDecimal payPrice = a.multiply(cart.getBprice());
				totalPrice = totalPrice.add(payPrice);
				orderBookList.add(new OrderBook(orderNumber, cart.getBimg(), cart.getBname(), cart.getBprice(),
						cart.getBnumber(), payPrice, orderTime, uid.longValue()));
			}
			byte state = 1;
			Order order = new Order(Long.parseLong(sid), orderNumber, totalPrice, orderTime, orderTime,uid.longValue(), state);
			OrderDao odi = new OrderDaoImpl();
			conn.setAutoCommit(false);// 开启事务
			// 订单
			int row = odi.insertOrder(order, conn);
			if (row > 0) {
				// 订单书
				int row1 = odi.insertOrderBook(orderBookList, conn);
				if (row1 == orderBookList.size()) {
					// 删除购物车
					int row2 = csi.deleteCartById(ids, conn);
					if (row1 == row2) {
						BookDao bdi = new BookDaoImpl();
						// 更新图书数量
						boolean flag = bdi.updateNumber(cartList, conn);
						if (flag) {
							conn.commit();
							return true;
						}
						conn.rollback();
						return false;
					}
					conn.rollback();
					return false;
				}
				conn.rollback();
				return false;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public InedxBookModel<Order> findOnePage(String keyword, String orderby, String start, String end, String currentPage,
			String pageSize,Long uid) {
		try {
		OrderDao odi = new OrderDaoImpl();
		int pagesize = Integer.parseInt(pageSize);
		int currentpage = Integer.parseInt(currentPage);
		Long total = odi.findCount(keyword,orderby,start,end,pagesize,uid);
		int totalPage = (int) Math.ceil(total * 1.0 / pagesize);
		List<Order> orderList = odi.findByPage(keyword,orderby,start,end,currentpage,pagesize,uid);
		return new InedxBookModel<Order>(pagesize,total,totalPage,currentpage,orderList,keyword);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderBook> findorderNumber(String orderNumber) {
		OrderDao odi = new OrderDaoImpl();
		orderNumber=orderNumber.substring(2);
		System.out.println(2+"number"+orderNumber);
		try {
			return odi.queryBookByOnumber(orderNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delOrder(String[] onumber) {
		Connection conn = DBUtil.getConnection();
		OrderDao odi = new OrderDaoImpl();
		//开启事务
		try {
			conn.setAutoCommit(false);
			int row = odi.delOrderByOnumber(onumber,conn);
			if(row==onumber.length) {
				int row2 = odi.delorderBookByByOnumber(onumber,conn);
				if(row2>0) {
					conn.commit();
					return true;
				}
				conn.rollback();
				return false;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
