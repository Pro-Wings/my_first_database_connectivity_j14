package com.prowings.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	String url = "jdbc:mysql://localhost:3306/my_first_database_connectivity_j14";
	String username = "root";
	String password = "prowingsroot";

	public Student getStudent(int rollNumber) {
		System.out.println("fetching student record with rollNumber : " + rollNumber + "  from DB!!!");
		String getStudentByRollNumberQuery = "SELECT * FROM student where rollNumber=" + rollNumber;
		Student fetchedStd = new Student();
		// 5 steps to connect to DB
		Connection con = null;
		try {
			// step2: Create connection
			con = DbConnectionUtils.getDbConnection();
			if (con != null) {
				// step3: Create Statement
				Statement stmt = con.createStatement();
				// step4: Execute Query
				ResultSet rs = stmt.executeQuery(getStudentByRollNumberQuery);

				if (!rs.next())
					System.out.println("Student Record not found with given rollNumber : " + rollNumber);

				while (rs.next()) {
					int rn = rs.getInt("rollNumber");
					String nm = rs.getString("name");
					String add = rs.getString("address");

					fetchedStd.setRollNumber(rn);
					fetchedStd.setName(nm);
					fetchedStd.setAddress(add);
				}

				System.out.println("Student data fetched from DB Table successfully : " + fetchedStd);
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

		return fetchedStd;

	}

	public List<Student> getAllStudents() {

		System.out.println("fetching all student records from DB!!!");
		String getStudentByRollNumberQuery = "SELECT * FROM student";
		List<Student> fetchedStdList = new ArrayList<>();
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
				ResultSet rs = stmt.executeQuery(getStudentByRollNumberQuery);

				if (!rs.next())
					System.out.println("No Student Record not found!!!");

				while (rs.next()) {
					Student fetchedStd = new Student();
					int rn = rs.getInt("rollNumber");
					String nm = rs.getString("name");
					String add = rs.getString("address");

					fetchedStd.setRollNumber(rn);
					fetchedStd.setName(nm);
					fetchedStd.setAddress(add);

					fetchedStdList.add(fetchedStd);
				}

				System.out.println("Students data fetched from DB Table successfully : ");
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

		return fetchedStdList;

	}

	public boolean saveStudent(Student std) {
		System.out.println("saveStudent() started - for Student Object - " + std);
		boolean res = false;
//		String ctreateStdQuery = "insert into Student (rollNumber, name, address) values (" + std.getRollNumber()
//				+ ", '" + std.getName() + "', '" + std.getAddress() + "');";
		
		String preparedStmtQuery = "insert into Student (rollNumber, name, address) values (?,?,?)";
		
		
//		System.out.println(">>>>> CREATE QUERY : " + ctreateStdQuery);
		// save incoming student object to DB
		// 5 steps to connect to DB
		Connection con = DbConnectionUtils.getDbConnection();
		try {
			if (con != null) {
				// step3: Create Statement
//				Statement stmt = con.createStatement();
				
				PreparedStatement ps = con.prepareStatement(preparedStmtQuery);
				ps.setInt(1, std.getRollNumber());
				ps.setString(2, std.getName());
				ps.setString(3, std.getAddress());
				
				// step4: Execute Query
//				stmt.execute(ctreateStdQuery);
				
				ps.execute();
				ps.close();
//				stmt.close();
				System.out.println("Student data written into DB Table successfully!!");
				res = true;
			}

		} catch (SQLException e) {
			System.out.println("Error while establishing connection to DB!!");
			e.printStackTrace();
		} finally {
//			DbConnectionUtils.closeDbConnection();
		}
		System.out.println("Code to write Student data into DB Table completed!!");
		return res;

	}

	public boolean updateStudent(Student updatedStd, int rollNumber) {
		// save incoming student object with updated details to DB

		System.out.println("updateStudent() started - updating Student record with Roll number : " + rollNumber);
		boolean res = false;
		String updateStdQuery = "UPDATE student SET name='" + updatedStd.getName() + "', address='"
				+ updatedStd.getAddress() + "' WHERE rollNumber = " + rollNumber;
		System.out.println(">>>>> Update QUERY : " + updateStdQuery);

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
//				res = stmt.execute(updateStdQuery);
				int rowsAffected = stmt.executeUpdate(updateStdQuery);
				System.out.println("Student data updated into DB Table successfully!!");
				res = rowsAffected == 1;
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

	public boolean deleteStudent(int rollNumber) {
		// delete student object from DB with specified rollnumber
		System.out.println("Deleting student record with rollNumber : " + rollNumber + "  from DB!!!");
		String deleteStudentByRollNumberQuery = "DELETE FROM student where rollNumber=" + rollNumber;
		boolean res = false;
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
				int rowsAffected = stmt.executeUpdate(deleteStudentByRollNumberQuery);
				if (rowsAffected ==1)
					System.out.println("Student data deleted from DB Table successfully.");
				else
					System.out.println("Student data not deleted from DB Table");

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

		return res;

	}

}
