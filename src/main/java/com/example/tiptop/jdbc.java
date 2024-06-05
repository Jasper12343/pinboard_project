package com.example.tiptop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class jdbc {

	private static Connection con = null;
	// connection string to the database
	private final String connectString = "jdbc:mysql://localhost:3307/test_table?user=root&password=pinboard_PO";//hier konfigurationsdaten anpassen


	// Opens the connection to the database
	private Connection openConnection() {
		try {
			System.out.println("start");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("mid");
			con = DriverManager.getConnection(connectString);
			
			System.out.println("end");
			con.setAutoCommit(false);
			return con;
		} catch (Exception e) {
			System.out.println("No connection to " + connectString);
			e.printStackTrace();
		}
		return null;
	}

	// Get and returns the connection
	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				return new jdbc().openConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Get Connection failed");
		}
		return con;
	}

}