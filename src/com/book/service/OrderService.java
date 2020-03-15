package com.book.service;

import java.util.List;

import com.book.entity.OrderBook;
import com.book.model.InedxBookModel;

public interface OrderService {

	boolean addOrder(String[] ids, String sid);

	InedxBookModel findOnePage(String keyword, String orderby, String start, String end, String currentPage,
			String pageSize, Long uid);

	List<OrderBook> findorderNumber(String orderNumber);

	boolean delOrder(String[] onumber);

}
