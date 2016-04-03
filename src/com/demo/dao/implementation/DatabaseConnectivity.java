package com.demo.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnectivity {

	Connection connection;
	Statement statement;
	
	String url = "jdbc:mysql://localhost:3306/";
	String username = "root";
	String password ="";
	
	public Statement doDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url+"spring", username, password);
			statement = connection.createStatement();
			return statement;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
