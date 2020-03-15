package com.book.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.book.core.util.DBUtil;
import com.book.dao.UserDao;
import com.book.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User queryUserByUname(String uname) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "select id,uname,pwd,email,mobile,company from t_user where uname=?";
		return qr.query(sql, new BeanHandler<User>(User.class),uname);
	}

	@Override
	public int insertUser(User user) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "insert into t_user (uname,pwd,email,mobile,company) value(?,?,?,?,?)";
		return qr.update(sql,user.getUname(),user.getPwd(),user.getEmail(),user.getMobile(),user.getCompany());
	}

	@Override
	public User queryUser(User user) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "select id,uname,pwd,email,mobile,company from t_user where uname=? and pwd=?";
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUname(),user.getPwd());
	}
}
