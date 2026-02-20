package com.prowings.dbconnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionUtils {

	private static String url;
	private static String uname;
	private static String pwd;

	private static Connection connection;

	static {
		Properties dbprops = new Properties();
		// No ClassLoader here. Just a direct file path relative to project root.
		try (FileInputStream fis = new FileInputStream("dbdetails.properties")) {

			dbprops.load(fis);

			url = dbprops.getProperty("dburl");
			uname = dbprops.getProperty("dbusername");
			pwd = dbprops.getProperty("dbpassword");

			System.out.println("Loaded from Root: " + dbprops);

		} catch (IOException e) {
			System.err.println("Could not find dbdetails.properties in project root!");
			e.printStackTrace();
		}
	}

	public static Connection getDbConnection() {
		try {
			if (connection == null) 
			{
				System.out.println("Creating Connected object!");
				connection = DriverManager.getConnection(url, uname, pwd);
				System.out.println("Connected Successfully!");
				return connection;
			} 
			else 
			{
				System.out.println("returning existing Connected object!");
				return connection;
			}

		} catch (SQLException e) {
			System.out.println("Error while establishing connection to DB!!");
			e.printStackTrace();
			return null;
		}

	}

	public static void closeDbConnection() {

		System.out.println("----CLOSING DB CONNECTION--------");
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("----ERROR WHILE CLOSING DB CONNECTION--------");
			e.printStackTrace();
		}

	}

}
