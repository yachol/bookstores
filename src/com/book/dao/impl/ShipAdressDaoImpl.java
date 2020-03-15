package com.book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.book.core.util.DBUtil;
import com.book.dao.ShipAdressDao;
import com.book.entity.ShipAdress;

public class ShipAdressDaoImpl implements ShipAdressDao {

	@Override
	public int insertAdress(ShipAdress shipAdress,Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		String sql = "insert into t_shipadress (uid,adress,post_code,name,mobile,isdefault) value(?,?,?,?,?,?)";
		return qr.update(conn, sql, shipAdress.getUid(),shipAdress.getAdress(),shipAdress.getPostCode(),shipAdress.getName(),shipAdress.getMobile(),shipAdress.getIsdefault());
	}

	
	@Override
	public List<ShipAdress> queryShipAdressByUid(Long uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,uid,adress,post_code postCode,name,mobile,isdefault from t_shipadress where uid=? ORDER BY isdefault DESC";
		return qr.query(sql,new BeanListHandler<ShipAdress>(ShipAdress.class),uid);
	}

	@Override
	public ShipAdress queryDefaultAddressByUid(Long uid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,uid,adress,post_code postCode,name,mobile,isdefault from t_shipadress where uid=? and isdefault=1";
		return qr.query(sql,new BeanHandler<ShipAdress>(ShipAdress.class),uid);
	}

	@Override
	public int updateShipAdress(Long id, Connection conn) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunnerNo();
		String sql = "update t_shipadress set isdefault=0 where id=?";
		return qr.update(conn, sql, id);
	}


	@Override
	public ShipAdress queryShipAdressByUid(String sid) throws SQLException {
		QueryRunner qr = DBUtil.getQueryRunner();
		String sql ="select id,uid,adress,post_code postCode,name,mobile,isdefault from t_shipadress where id=?";
		return qr.query(sql,new BeanHandler<ShipAdress>(ShipAdress.class),sid);
	}

}
