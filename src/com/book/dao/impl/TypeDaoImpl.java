package com.book.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.book.core.util.DBUtil;
import com.book.dao.TypeDao;
import com.book.entity.Type;

public class TypeDaoImpl implements TypeDao {

	@Override
	public List<Type> findBigType() throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "select id,type,pre_id preId from t_type where id=pre_id";
		return qr.query(sql,new BeanListHandler<Type>(Type.class));
	}

	@Override
	public List<Type> findLittleTyp(Long typeid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql = "select id,type,pre_id preId from t_type where id!=pre_id and pre_id=?";
		return qr.query(sql,new BeanListHandler<Type>(Type.class),typeid);
	}

}
