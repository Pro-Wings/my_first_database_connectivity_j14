package com.prowings.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionDemo {

	public static void main(String[] args) {

		System.out.println("Code to write Student data into DB Table started!!");

		String url = "jdbc:mysql://localhost:3306/my_first_database_connectivity_j14";
		String username = "root";
		String password = "prowingsroot";

		String createStudentQuery = "insert into Student (rollNumber, name, address) values (20, 'Sham', 'Pune');";

		// 5 steps to connect to DB
		Connection con = null;

		try {

			// step1: Register the Driver Class - NOTE: this is optional as latest JDBC
			// automatically finds and load/registers drivers from classpath
			
			Class.forName("com.mysql.jdbc.Driver");

			// step2: Create connection
			con = DriverManager.getConnection(url, username, password);

			// step3: Create Statement
			if (con != null) {
				System.out.println("Connected Successfully!");

				Statement stmt = con.createStatement();

				// step4: Execute Query
				stmt.execute(createStudentQuery);

				System.out.println("Student data written into DB Table successfully!!");

			}

		} catch (ClassNotFoundException e) {
			System.out.println("Drive class not found!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error while establishing connection to DB!!");
			e.printStackTrace();
		} finally {
			// step5: Close the connection
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error while closing DB connection!!");
				e.printStackTrace();
			}

		}

		System.out.println("Code to write Student data into DB Table completed!!");

	}

}
