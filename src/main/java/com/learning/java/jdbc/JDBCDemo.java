package com.learning.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author sangyue1
 * @Date 2019/8/13 14:38
 */
public class JDBCDemo {
	public static void main(String[] args) throws SQLException {
		String URL = "jdbc:mysql://localhost/nemo_new?useConfigs=maxPerformance&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
		String USER = "root";
		String PASSWORD = "root";
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println(conn);
	}
}
