import java.util.Scanner;

public class GenericStack<T>{
	
	private class Node{
		T data;
		Node next;
		
		public Node(T data){
			this.data = data;
		}
	}
	
	private Node head;
	private Node tail;
	private int size = 0;
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public void push(T element){
		Node newNode = new Node(element);
		if (isEmpty()){
			head = newNode;
			tail = newNode;
		}
		else{
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	public void pop(){
		if (isEmpty())
			System.out.println("EMPTY");
		else if (size == 1){
			head = null;
			tail = null;
			size = 0;
		}
		else{
			Node temp = head;
			while (temp.next.next != null){
				temp = temp.next;
			}
			temp.next = null;
			tail = temp;
			size--;
		}
	}
	
	public void display(){
		if (isEmpty()){
			System.out.println("EMPTY");
		}
		else{
			Node temp = head;
			while (temp != null){
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}
	
	public T peek(){
		if (isEmpty()){
			System.out.println("EMPTY");
			return null;
		}
		else
			return tail.data;
	}
	public int getSize(){
		return size;
	}
	
	
	public static void main(String[] args){
		GenericStack<String> genStack1 = new GenericStack<String>();
		System.out.println("\nThis is a generic linkedlist-based implementation of a STACK");
		
		while(true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nSelect the operation to be performed below: \n0. Exit\n1. Display stack\n2. Push to stack\n3. Pop stack\n4. Check if empty\n5. Get size of queue \n6. Peek stack");
		
			
			int choice = keyboard.nextInt();
			switch (choice){
				case 0:
					System.exit(0);
					break;
				case 1:
					genStack1.display();
					break;
				case 2:
					keyboard.nextLine();
					System.out.print("Enter element to be added: ");
					String element = keyboard.nextLine();
					genStack1.push(element);
					genStack1.display();
					break;
				case 3:
					genStack1.pop();
					genStack1.display();
					break;
				case 4:
					System.out.println(genStack1.isEmpty());
					break;
				case 5:
					System.out.println(genStack1.getSize());
					break;
				case 6: 
					System.out.println("Top element is: " + genStack1.peek());
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	}
}