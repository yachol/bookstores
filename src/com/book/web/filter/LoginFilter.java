package com.book.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1.登录验证
		if (request instanceof HttpServletRequest&&response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse)response;
			//1.登录验证
			resp.setContentType("text/html;charset=utf-8");
			HttpSession session = req.getSession(false);
			if(session==null||session.getAttribute("user")==null) {
				resp.getWriter().print("请登录");
			}else {
				System.out.println("登录过滤一次");
				chain.doFilter(req, resp);
			}
		}
	}

}
