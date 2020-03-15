package com.book.service;

import java.util.List;

import com.book.entity.ShipAdress;

public interface ShipAdressService {

	boolean addAress(ShipAdress shipAdress);

	List<ShipAdress> findShipAddress(Long uid);

	ShipAdress findShipAddress(String sid);

}
