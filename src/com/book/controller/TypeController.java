package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.entity.Type;
import com.book.service.TypeService;
import com.book.service.impl.TypeServiceImpl;

@Controller
@RequestMapping("/type")
public class TypeController {
	private static TypeService tsi = new TypeServiceImpl();

	@RequestMapping("/bigtype")
	public void bigtype(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			List<Type> list = tsi.showBigType();
			if (list != null) {
				String jsonString = JSON.toJSONString(list);
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().write(jsonString);
			}else {
				resp.getWriter().write("{}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping("/showCaidan")
	public void showCaidan(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String bigtypeid = req.getParameter("bigtypeid");
			List<Type> list = tsi.showLittleType(bigtypeid);
			if (list != null) {
				String jsonString = JSON.toJSONString(list);
				System.out.println(list);
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().write(jsonString);
			} else {
				resp.getWriter().write("{}");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
