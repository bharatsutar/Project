package com.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static final String URL="jdbc:mysql://localhost:3306/bookstore";
	static final String USER="root";
	static final String PASS="root";
	
	public  static Connection getConnection() throws SQLException
	{
		Connection connection=null;
		connection=DriverManager.getConnection(URL,USER,PASS);
		return connection;
	}


}
