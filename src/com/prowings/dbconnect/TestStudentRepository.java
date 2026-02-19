package com.prowings.dbconnect;

public class TestStudentRepository {
	
	
	public static void main(String[] args) {

		System.out.println("main method started!!");
		Student student = new Student(30, "Sachin", "Mumbai");
		StudentRepository repository = new StudentRepository();
		
		if(repository.saveStudent(student))
			System.out.println("RECORD INSERTED SUCCESSFULLY!!!");
		else
			System.out.println("RECORD NOT INSERTED SUCCESSFULLY!!!");
			
		System.out.println("main method ended!!");
		
	}

}
