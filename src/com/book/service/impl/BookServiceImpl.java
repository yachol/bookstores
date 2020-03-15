package com.book.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BookDao;
import com.book.dao.CartDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.CartDaoImpl;
import com.book.entity.Book;
import com.book.entity.Carousel;
import com.book.entity.Cart;
import com.book.model.BnameNumber;
import com.book.model.InedxBookModel;
import com.book.service.BookService;


public class BookServiceImpl implements BookService {
private static BookDao bdi = new BookDaoImpl();
	

	@Override
	public InedxBookModel<Book> findOnePage(String bigtypeid, String currentPage, String keyWord, int pageSize) {
		try {
			Long typeid=0l;
			if(!"".equals(bigtypeid)){
				typeid = Long.parseLong(bigtypeid.substring(1));
			}
		Long total = bdi.findCount(keyWord,typeid);
		Integer current=Integer.parseInt(currentPage);
		List<Book> bookList = bdi.findByPage(current, pageSize, keyWord,typeid);
		System.out.println(bookList);
		int totalPage = (int) Math.ceil(total * 1.0 / pageSize);
		return new InedxBookModel<Book>(pageSize,total,totalPage,current,bookList,keyWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
	}

	@Override
	public InedxBookModel<Book> findOnePage(String bigtypeid, String currentPage, int pageSize) {
		return findOnePage(bigtypeid, currentPage,"",pageSize);
		
	}

	@Override
	public List<Carousel> findCarousel() {
		try {
			return bdi.queryCarousel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		 
	}

	@Override
	public Book findBook(String bookid) {
		try {
			return bdi.queryBookByid(bookid); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int queryCount(String bid) {
		try {
			Book book=bdi.queryBookNumberByBname(bid);
			return book.getNumber();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<BnameNumber> findBooks(String[] ids) {
		try {
			CartDao cdi = new CartDaoImpl();
			ArrayList<BnameNumber> list = new ArrayList<BnameNumber>();
			for(int x=0;x<ids.length;x++) {
				Cart cart = cdi.queryCartById(ids[x]);
				Integer bid = cart.getBid();
				Integer cartNumber = cart.getBnumber();
				Book book = bdi.queryBookByid(bid.toString());
				Integer realNumber = book.getNumber();
				if(cartNumber>realNumber) {
					list.add(new BnameNumber(book.getBname(), realNumber));
				}
			}
			System.out.println(list);
			return list.size()>0?list:null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
