package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;
import com.book.core.util.DateUtil;
import com.book.entity.Book;
import com.book.entity.Carousel;
import com.book.entity.Cart;
import com.book.model.BnameNumber;
import com.book.model.BookModel;
import com.book.model.InedxBookModel;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;

@Controller
@RequestMapping("/book")
public class BookController {
	private static BookService bsi = new BookServiceImpl();

	@RequestMapping("/showBook")
	public void showBook(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String bigtypeid = req.getParameter("bigtypeid");
			String currentPage = req.getParameter("currentPage");
			int pageSize = 4;
			InedxBookModel<Book> inedxBookModel = bsi.findOnePage(bigtypeid, currentPage, pageSize);
			String jsonString = JSON.toJSONString(inedxBookModel);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/querycount")
	public void queryCount(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			
			String bid = req.getParameter("bid");
			int RealNumber=bsi.queryCount(bid);
		
				resp.getWriter().write(RealNumber+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/checkSomeBooknumber")
	public void checkSomeBooknumber(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			
			String[] ids = req.getParameterValues("ids[]");
			System.out.println(ids);
			List<BnameNumber> list=bsi.findBooks(ids);
			String jsonString = JSON.toJSONString(list);
			System.out.println(jsonString);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/bookslist")
	public void booksList(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String bigtypeid = req.getParameter("bigtypeid");
			String currentPage = req.getParameter("currentPage");
			String keyword = req.getParameter("keyword");
			String page = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(page);
			InedxBookModel<Book> inedxBookModel = bsi.findOnePage(bigtypeid, currentPage, keyword,pageSize);
			String jsonString = JSON.toJSONString(inedxBookModel);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@RequestMapping("/carousel")
	public void carousel(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			List<Carousel> list = bsi.findCarousel();
			if (list != null) {
				String jsonString = JSON.toJSONString(list);
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().write(jsonString);
			} else {
				resp.getWriter().write("{}");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/details")
	public void details(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * void 啥也不干 "forward:/add"转发 "redirect:/add"重定向
		 */
		try {
			String bookid = req.getParameter("bookid");
				Book book = bsi.findBook(bookid);
//				System.out.println(book);
				BookModel bookModel = new BookModel(book.getId(), book.getBname(), book.getImg(), book.getAuthor(), book.getCountry(), DateUtil.dateMy(book.getPublishDate()), book.getPublisher(), book.getOriginalPrice(), book.getCurrentPrice(), book.getIsbn(), book.getNumber(),book.getIstao()==1?"是":"否", book.getInfo());
				
//				System.out.println(book.getPublishDate());
				String jsonString = JSON.toJSONString(bookModel);
//				System.out.println(jsonString);
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().write(jsonString);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
