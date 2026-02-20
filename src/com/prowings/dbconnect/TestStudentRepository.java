package com.prowings.dbconnect;

public class TestStudentRepository {
	
	
	public static void main(String[] args) {

		System.out.println("main method started!!");
		StudentRepository repository = new StudentRepository();
		
		//1. save new student to DB
		Student student = new Student(80, "aaa", "bbb");
		if(repository.saveStudent(student))
			System.out.println("RECORD INSERTED SUCCESSFULLY!!!");
		else
			System.out.println("RECORD NOT INSERTED SUCCESSFULLY!!!");
			
		
		//2. Get student by rollNumber
		Student s = repository.getStudent(10);
		System.out.println(">>> Fetched Std : "+s);

		//3. Get all students 
//		List<Student> stdLst = repository.getAllStudents();
//		System.out.println(">>> Fetched Stds : "+stdLst);
		

		//4. Update existing student by roll number
		
//		Student updatedStd = new Student(40, "Samir", "Dubai");
//		if(repository.updateStudent(updatedStd, 40))
//			System.out.println("Update success!!");
//		else
//			System.out.println("Update not success!!");
			
		//5. Delete student by roll number
//		repository.deleteStudent(40);
		
		
		System.out.println("main method ended!!");
		
	}

}
