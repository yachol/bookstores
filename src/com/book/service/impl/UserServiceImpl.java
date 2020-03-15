package com.book.service.impl;

import java.sql.SQLException;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.entity.User;
import com.book.service.UserService;

public class UserServiceImpl implements UserService {
	private static UserDao udi = new UserDaoImpl();

	@Override
	public boolean checkuname(String uname) {
		if (uname == null)
			return false;

		try {
			User user = udi.queryUserByUname(uname);
			return user == null ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registUser(User user) {
		try {
			return udi.insertUser(user) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User login(User user) {
		if (user == null)
			return null;
		try {
			return udi.queryUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
