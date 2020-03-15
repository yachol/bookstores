package com.book.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.entity.Cart;
import com.book.entity.ShowCount;
import com.book.entity.User;
import com.book.service.CartService;
import com.book.service.impl.CartServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {
	private static CartService csi = new CartServiceImpl();

	@RequestMapping("/init")
	public void Init(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
	}

	@RequestMapping("/delcart")
	public void delCart(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String[] array = req.getParameterValues("array[]");
			boolean flag = csi.deleteCart(array);
			if (flag) {
				resp.getWriter().write("true");
			} else {
				resp.getWriter().write("false");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/confirmorder")
	public String confirmOrder(HttpServletRequest req, HttpServletResponse resp) {

		String[] ids = req.getParameterValues("cks");
		try {
			if (ids == null) {
				resp.getWriter().write("<script>alert('结算失败，返回购物车')</script>");
				return "redirect:/cart.jsp";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Cart> list = csi.confirmOrder(ids);
		double totalPraice=0d;
		for (Cart cart : list) {
			BigDecimal a = new BigDecimal(cart.getBnumber()+"");
			totalPraice+=a.multiply(cart.getBprice()).doubleValue();
		}
		req.setAttribute("totalPraice", totalPraice);
		req.setAttribute("list", list);
		return "forward:/confirm_order.jsp";

	}

	@RequestMapping("/changbnumber")
	public void changBnumber(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String id = req.getParameter("id");
			System.out.println(id);
			String bnumber = req.getParameter("bnumber");
			boolean flag = csi.changeBnumber(id, bnumber);
			if (!flag) {
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().write("数量有误");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/showcart")
	public void showCart(HttpServletRequest req, HttpServletResponse resp) {

		try {
			String uid = req.getParameter("uid");
			List<Cart> list = csi.showCart(uid);
			String jsonString = JSON.toJSONString(list);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/showcount")
	public void showCount(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
			Long uid = user.getId();
			ShowCount sc = csi.findCount(uid+"");
			if(sc.getBpriceTotal()==null)
				sc=new ShowCount(new BigDecimal("0"),new BigDecimal("0"));
			String jsonString = JSON.toJSONString(sc);
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/addcart")
	public void addCart(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String bid = req.getParameter("bid");
			String uid = req.getParameter("uid");
			String bnumber = req.getParameter("bnumber");
			String bimg = req.getParameter("bimg");
			String bname = req.getParameter("bname");
			String bprice = req.getParameter("bprice");
			if (Pattern.matches("\\d+", bid) && Pattern.matches("\\d+", uid) && Pattern.matches("\\d+", bnumber)) {
				Cart cart = new Cart(Integer.valueOf(bid), bimg, bname, Integer.valueOf(bnumber),
						new BigDecimal(bprice), Integer.valueOf(uid));
				boolean flag = csi.addCart(cart);
				if (flag) {
					resp.getWriter().write("true");
					return;
				}
			}
			resp.getWriter().write("false");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
