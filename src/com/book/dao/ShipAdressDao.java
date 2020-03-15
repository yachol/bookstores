package com.book.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.book.entity.ShipAdress;

public interface ShipAdressDao {

	int insertAdress(ShipAdress shipAdress, Connection conn) throws SQLException;

	
	List<ShipAdress> queryShipAdressByUid(Long uid) throws SQLException;

	ShipAdress queryDefaultAddressByUid(Long uid) throws SQLException;

	int updateShipAdress(Long id, Connection conn) throws SQLException;


	ShipAdress queryShipAdressByUid(String sid) throws SQLException;

}
