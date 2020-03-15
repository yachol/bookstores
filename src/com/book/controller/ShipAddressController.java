package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.entity.ShipAdress;
import com.book.entity.User;
import com.book.service.ShipAdressService;
import com.book.service.impl.ShipAdressServiceImpl;

@Controller
@RequestMapping("/shipadress")
public class ShipAddressController {
	@RequestMapping("/addadress")
	public void showBook(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String province1 = req.getParameter("province1");
			String city1 = req.getParameter("city1");
			String district1 = req.getParameter("district1");
			String deatails = req.getParameter("deatails");
			String postCode = req.getParameter("post_code");
			String name = req.getParameter("name");
			String mobile = req.getParameter("mobile");
			String setDefaultAddr = req.getParameter("setDefaultAddr");
			String adress=province1+city1+district1+deatails;
			byte isdefault=(byte) ("true".equals(setDefaultAddr)?1:0);
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
			Long uid = user.getId();
			ShipAdress shipAdress = new ShipAdress(adress, isdefault, mobile, name, postCode, uid);
			ShipAdressService sasi = new ShipAdressServiceImpl();
			boolean flag=sasi.addAress(shipAdress);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(flag?"true":"false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/showaddress")
	public void showAddress(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
			Long uid = user.getId();
			ShipAdressService sasi = new ShipAdressServiceImpl();
			List<ShipAdress> list =sasi.findShipAddress(uid);
			String jsonString = JSON.toJSONString(list);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/showad")
	public void showad(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String sid = req.getParameter("sid");
			ShipAdressService sasi = new ShipAdressServiceImpl();
			ShipAdress sa =sasi.findShipAddress(sid);
			String jsonString = JSON.toJSONString(sa);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String jsonString = JSON.toJSONString(null);
		System.out.println(jsonString);
	}
	
}
