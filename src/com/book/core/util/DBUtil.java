package com.book.core.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 集成了C3P0+DBUtil
 */
public class DBUtil {
	private static DataSource dataSource = new ComboPooledDataSource(); //数据源是共享的
	
	/**
	 * 1.提供一个获取数据库连接对象的方法（事务）
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 2. 创建的是一个用于非事务操作的DBUtils工具
	 * @return
	 */
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource); 
		// 使用完成后悔自动调用conn.close() 【重写：本质是归还到连接池】
	}
	/**
	 * 3. 创建的是一个用于事务操作的DBUtils工具
	 * @return
	 */
	public static QueryRunner getQueryRunnerNo() {
		return new QueryRunner(); 
		// 使用完成后悔自动调用conn.close() 【重写：本质是归还到连接池】
	}
}
