package com.book.dao;

import java.sql.SQLException;

import com.book.entity.User;

public interface UserDao {
	/**
	 * 通过过用户名查询用户是否存在
	 * 
	 * @param uname
	 * @return
	 * @throws SQLException
	 */
	User queryUserByUname(String uname) throws SQLException;

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int insertUser(User user) throws SQLException;

	/**
	 * 查找用户
	 * 
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	User queryUser(User user) throws SQLException;

}
