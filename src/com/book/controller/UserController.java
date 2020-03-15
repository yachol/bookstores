package com.book.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.core.util.CookieUtil;
import com.book.core.util.EmailUtil;
import com.book.core.util.UserUtil;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;

/**
 * 用户请求的控制器
 * 
 * @author yy
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static UserService usi = new UserServiceImpl();
	private static String code="";
	/**
	 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String uname = req.getParameter("uname");
			String pwd = req.getParameter("pwd");
			String remember = req.getParameter("remember");
			if (!(UserUtil.checkUser(uname) && UserUtil.checkPassword(pwd))) {
				resp.getWriter().write("false");
				return;
			}
			User user = usi.login(new User(uname, pwd));
			if ("true".equals(remember)) {
				Cookie c1 = new Cookie("uname", user.getUname());
				Cookie c2 = new Cookie("pwd", user.getPwd());
				c1.setPath(req.getContextPath());
				c2.setPath(req.getContextPath());
				c1.setMaxAge(60 * 60 * 24 * 7);
				c2.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(c1);
				resp.addCookie(c2);
			} else {
				Cookie c1 = CookieUtil.getCookie("uname", req.getCookies());
				Cookie c2 = CookieUtil.getCookie("pwd", req.getCookies());
				if (c1 != null) {
					c1.setMaxAge(0);
					c1.setPath(req.getContextPath());
					resp.addCookie(c1);
				}
				if (c2 != null) {
					c2.setMaxAge(0);
					c2.setPath(req.getContextPath());
					resp.addCookie(c2);
				}
			}
			if (user != null) {
				HttpSession session = req.getSession();
				user.setPwd(null);
				session.setAttribute("user", user);
				resp.getWriter().write("true");
			} else {
				resp.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/regist")
	public void regist(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String uname = req.getParameter("uname");
			String pwd = req.getParameter("pwd");
			String email = req.getParameter("email");
			String mobile = req.getParameter("mobile");
			String company = req.getParameter("company");
			User user = new User(uname, pwd, email, mobile, company);
			
			if (!(UserUtil.checkUser(uname) && UserUtil.checkPassword(pwd) && UserUtil.checkMobile(mobile)
					&& UserUtil.checkEmail(email) && UserUtil.checkCompany(company))) {
				resp.getWriter().write("{}");
				return;
			}
			boolean flag = usi.registUser(user);
			if (flag) {
				User user2 = new  User(uname, pwd);
				String jsonString = JSON.toJSONString(user2);
				resp.getWriter().write(jsonString);
			} else {
				resp.getWriter().write("{}");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/yanzheng")
	public void yanzheng(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String email = req.getParameter("email");
		code=EmailUtil.getCheckCode();
		System.out.println("code"+code);
		EmailUtil.sendEmail(email,code);
		
	}

	@RequestMapping("/check_uname")
	public void checkUname(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String uname = req.getParameter("uname");
		if (!UserUtil.checkUser(uname)) {
			resp.getWriter().write("false");
			return;
		}
		boolean flag = usi.checkuname(uname);
		if (flag)
			resp.getWriter().write("true");
		else
			resp.getWriter().write("false");
	}

	@RequestMapping("/destroy")
	public String destroy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if (session != null)
			session.removeAttribute("user");
		Cookie c1 = CookieUtil.getCookie("uname", req.getCookies());
		Cookie c2 = CookieUtil.getCookie("pwd", req.getCookies());
		if (c1 != null) {
			c1.setMaxAge(0);
			c1.setPath(req.getContextPath());
			resp.addCookie(c1);
		}
		if (c2 != null) {
			c2.setMaxAge(0);
			c2.setPath(req.getContextPath());
			resp.addCookie(c2);
		}
		return "redirect:/index.jsp";
	}
}
