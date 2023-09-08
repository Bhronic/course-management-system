package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// Register the Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");

		// create connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "admin@123");
		return con;
	}

}
