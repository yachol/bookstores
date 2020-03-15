package com.book.service;

import java.util.List;

import com.book.entity.Type;

public interface TypeService {

	List<Type> showBigType();

	List<Type> showLittleType(String bigtypeid);

}
