package com.prowings.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StudentRepository {
	
	
	String url = "jdbc:mysql://localhost:3306/my_first_database_connectivity_j14";
	String username = "root";
	String password = "prowingsroot";
	
	public Student getStudent(int rollNumber)
	{
		//save incoming student object to DB
		return null;
		
	}

	public List<Student> getAllStudents()
	{
		//save incoming student object to DB
		return null;
		
	}


	public boolean saveStudent(Student std)
	{
		System.out.println("saveStudent() started - for Student Object - "+std);
		boolean res = false;
		String ctreateStdQuery = "insert into Student (rollNumber, name, address) values ("+std.getRollNumber()+", '" +std.getName()+"', '"+std.getAddress()+"');";
		System.out.println(">>>>> CREATE QUERY : "+ctreateStdQuery);
		//save incoming student object to DB
		// 5 steps to connect to DB
		Connection con = null;
		try {

			// step2: Create connection
			con = DriverManager.getConnection(url, username, password);

			if (con != null) {
				System.out.println("Connected Successfully!");
				// step3: Create Statement
				Statement stmt = con.createStatement();
				// step4: Execute Query
				stmt.execute(ctreateStdQuery);
				System.out.println("Student data written into DB Table successfully!!");
				res = true;
			}

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
		return res;
		
	}

	public boolean updateStudent(Student std, int rollNumber)
	{
		//save incoming student object with updated details to DB
		return false;
		
		
	}

	public boolean deleteStudent(int rollNumber)
	{
		//delete student object from DB with specified rollnumber
		return false;
		
		
	}

}
