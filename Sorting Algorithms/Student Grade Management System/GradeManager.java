import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeManager{
	
	private List<Student> students = new ArrayList<>();
	
	public void addStudent(String fname, String lname, int grade){
		students.add(new Student(fname, lname, grade));
	}
	
	public void sortStudentsByGrade(){
		mergeSort(students, 0, students.size()-1);
	}
	
	public void displayStudents(){
		for(Student student: students){
			System.out.println(student);
		}
	}
	public void mergeSort(List<Student> A, int low,int high){
		if (low<high){
			int mid = (high+low)/2;
			mergeSort(A, low, mid);
			mergeSort(A, mid+1,high);
			merge(A, low, mid, high);
		}
	}
	public void merge(List<Student> A, int low, int mid, int high){
		List<Student> B = new ArrayList<>(high-low+1);
		int h = low;
		int j = mid+1;
		int i = 0;
		
		while (h<=mid && j<=high){
			if (A.get(h).grade<=A.get(j).grade){
				B.add(A.get(h));
				h++;
			}
			else{
				B.add(A.get(j));
				j++;
			}
			i++;
		}
		
		if (h>mid){
			for (int k=j;k<=high;k++){
				B.add(A.get(k));
				i++;
			}
		}
		else{
			for (int k=h;k<=mid;k++){
				B.add(A.get(k));
				i++;
			}
		}
		
		for (int k=0;k<B.size();k++){
			A.set(low+k,B.get(k));
		}
	}
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		GradeManager manager = new GradeManager();
		
		while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Sort Students by Grade");
            System.out.println("3. Display Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = keyboard.nextInt();
            keyboard.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter student first name: ");
                String fname = keyboard.nextLine();
				System.out.print("Enter student last name: ");
                String lname = keyboard.nextLine();
                System.out.print("Enter student grade: ");
                int grade = keyboard.nextInt();
                keyboard.nextLine(); // consume newline
                manager.addStudent(fname,lname, grade);
            } else if (option == 2) {
                manager.sortStudentsByGrade();
                System.out.println("Students sorted by grade.");
            } else if (option == 3) {
                manager.displayStudents();
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }

        // keyboard.close();
	}
}