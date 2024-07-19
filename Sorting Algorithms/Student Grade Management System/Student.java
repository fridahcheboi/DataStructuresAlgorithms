public class Student{
	String firstName;
	String lastName;
	int grade;
	
	public Student(String fname, String lname, int grade){
		firstName = fname;
		lastName = lname;
		this.grade = grade;
		
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName + ": " + grade;
	}
}