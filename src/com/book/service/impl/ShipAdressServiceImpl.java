package com.book.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.book.core.util.DBUtil;
import com.book.dao.ShipAdressDao;
import com.book.dao.impl.ShipAdressDaoImpl;
import com.book.entity.ShipAdress;
import com.book.service.ShipAdressService;

public class ShipAdressServiceImpl implements ShipAdressService {

	@Override
	public boolean addAress(ShipAdress shipAdress) {
		ShipAdressDao sadi = new ShipAdressDaoImpl();
		Connection conn = DBUtil.getConnection();
		byte isdefault = shipAdress.getIsdefault();
		Long uid = shipAdress.getUid();
		ShipAdress sa = null;
		try {
			sa = sadi.queryDefaultAddressByUid(uid);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		if (isdefault != 1 || (isdefault == 1 && sa == null)) {
			try {
				int rows = sadi.insertAdress(shipAdress, conn);
				return rows > 0 ? true : false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		try {
			// 已经有默认地址了且isdefault=1
			if (sa != null) {
				// 事务开启
				conn.setAutoCommit(false);
				// 修改默认为0
				int old = sadi.updateShipAdress(sa.getId(), conn);
				// 新的插入为1
				if (old > 0) {
					int rows = sadi.insertAdress(shipAdress, conn);
					if (rows > 0)
						conn.commit();
					return rows > 0 ? true : false;
				}
				return false;
			} 
		} catch (SQLException e3) {
			e3.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<ShipAdress> findShipAddress(Long uid) {
		ShipAdressDao sadi = new ShipAdressDaoImpl();
		try {
			return sadi.queryShipAdressByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ShipAdress findShipAddress(String sid) {
		ShipAdressDao sadi = new ShipAdressDaoImpl();
		sid=sid.substring(2);
		try {
			return sadi.queryShipAdressByUid(sid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
