package com.book.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.book.dao.TypeDao;
import com.book.dao.impl.TypeDaoImpl;
import com.book.entity.Type;
import com.book.service.TypeService;

public class TypeServiceImpl implements TypeService {
private static TypeDao tdi = new TypeDaoImpl();
	@Override
	public List<Type> showBigType() {
		try {
			return tdi.findBigType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Type> showLittleType(String bigtypeid) {
		try {
			Long typeid = Long.parseLong(bigtypeid.substring(1));
			return tdi.findLittleTyp(typeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
