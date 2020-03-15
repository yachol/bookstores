package com.book.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderBook;
import com.book.entity.User;
import com.book.model.InedxBookModel;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderController {
	@RequestMapping("/addorder")
	public String addOrder(HttpServletRequest req, HttpServletResponse resp) {

		String[] ids = req.getParameterValues("id");
		String sid = req.getParameter("orderadress");
		/*
		 * String[] bname = req.getParameterValues("bname"); String[] bimg =
		 * req.getParameterValues("bimg");   String[] bprice =
		 * req.getParameterValues("bprice");
		 */
		/*
		 * String[] bid =req.getParameterValues("bid"); for (String string : bid) {
		 * System.out.println(string); } String[] bnumber =
		 * req.getParameterValues("bnumber"); for (String string : bnumber) {
		 * System.out.println(string); } BookDao bdi = new BookDaoImpl();
		 */
		try {
			/*
			 * for (int i=0;i<bid.length;i++) { Book book = bdi.queryBookByid(bid[i]);
			 * Integer number = book.getNumber(); System.out.println("real"+number);
			 * System.out.println("now"+Integer.parseInt(bnumber[i]));
			 * if(Integer.parseInt(bnumber[i])>number) { System.out.println("数量过多");
			 * resp.getWriter().write("<script>alert('数量过多')</script>");
			 * //resp.setHeader(name, value); "redirect:/cart.jsp";
			 * //resp.setHeader("refresh", "0;url=cart.jsp"); return "redirect:/cart.jsp"; }
			 * }
			 */
		
		OrderService osi = new OrderServiceImpl();
		boolean addOrder = osi.addOrder(ids, sid);
		
			if (!addOrder) {
				resp.getWriter().write("<script>alert('结算失败，返回购物车')</script>");
				return "redirect:/cart.jsp";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/order.jsp";
	}
	@RequestMapping("/showorder")
	public void showOrder(HttpServletRequest req, HttpServletResponse resp) {
		String currentPage = req.getParameter("currentPage");
		String orderby = req.getParameter("orderby");
		String keyword = req.getParameter("keyword");
		String pageSize = req.getParameter("pageSize");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		Long uid = user.getId();
		OrderService osi = new OrderServiceImpl();
		if("".equals(keyword)||keyword==null) 
			keyword="";
		if("".equals(orderby)||orderby==null) 
			orderby="DESC";
		if("".equals(pageSize)||pageSize==null) 
			pageSize="2";
		if("".equals(currentPage)||currentPage==null) 
			currentPage="1";
		InedxBookModel<Order> model= osi.findOnePage(keyword,orderby,start,end,currentPage,pageSize,uid);
		try {
			String jsonString = JSON.toJSONString(model);
			System.out.println(jsonString);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/showOrderBook")
	public void showOrderBook(HttpServletRequest req, HttpServletResponse resp) {

		String orderNumber = req.getParameter("orderNumber");
		OrderService osi = new OrderServiceImpl();
		List<OrderBook> ob= osi.findorderNumber(orderNumber);
		try {
			String jsonString = JSON.toJSONString(ob);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/delorder")
	public void delOrder(HttpServletRequest req, HttpServletResponse resp) {
		
		String[] onumber = req.getParameterValues("onumber[]");
		OrderService osi = new OrderServiceImpl();
		boolean flag= osi.delOrder(onumber);
		try {
			resp.getWriter().write(flag?"true":"false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
