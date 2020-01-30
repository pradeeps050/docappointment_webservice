package org.ps.docappointment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DBURL = "jdbc:mysql://localhost:3306/docappointment?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.jdbc.Driver
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			System.out.println("Database connection Established....");
			return connection;
		} catch (SQLException e) {
			System.out.println("Error >>  SQL Exception");
			e.printStackTrace();
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Error >>  ClassNotFoundException");
			e.printStackTrace();
			return connection;
			
		}
	}

}
