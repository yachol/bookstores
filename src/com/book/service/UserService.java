package com.book.service;

import com.book.entity.User;

public interface UserService {
	/**
	 * 注册时检查用户名是否存在
	 * 
	 * @param uname
	 * @return
	 */
	boolean checkuname(String uname);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	boolean registUser(User user);

	/**
	 * 登录
	 * 
	 * @param user
	 * @return 数据库查出的user数据
	 */
	User login(User user);

}
