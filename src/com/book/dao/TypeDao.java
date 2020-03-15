package com.book.dao;

import java.sql.SQLException;
import java.util.List;

import com.book.entity.Type;

public interface TypeDao {

	List<Type> findBigType() throws SQLException;

	List<Type> findLittleTyp(Long typeid) throws SQLException;

}
