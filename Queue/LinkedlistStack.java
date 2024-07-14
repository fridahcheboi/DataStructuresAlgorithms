import java.util.Scanner;

public class LinkedlistStack{
	
	private static class Node{
		int data;
		Node next;
		
		public Node(int data){
			this.data = data;
		}
	}
	
	private Node head;
	private Node tail; //top is the tail node, end which we pop and push items 
	private int size;
	
	public boolean isEmpty(){
		return (head == null);
	}
	public void push(int element){
		Node newNode = new Node(element);
		if (size == 0){
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
			System.out.println("UNDERFLOW");
		else if (size == 1){
			head = null;
			tail = null;
			size=0;
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
	
	public int peek(){
		if (isEmpty())
			return -1;
		return tail.data;
	}
	
	public void display(){
		Node temp = head;
		if (isEmpty())
			System.out.println("EMPTY");
		else{
			while(temp != null){
				System.out.print(temp.data + " ");
				temp = temp.next;
			} 
			System.out.println();
		}	
	}
	
	public int getSize(){
		return size;
	}
	
	public static void main(String[] args){
		System.out.println("\nThis is a linkedlist-based implementation of a STACK for integers");
		LinkedlistStack stack1 = new LinkedlistStack();
		
		while(true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nSelect the operation to be performed below: \n0. Exit\n1. Display stack\n2. Push to stack\n3. Pop stack\n4. Check if empty\n5. Get size of queue \n6. Peek stack");
		
			
			int choice = keyboard.nextInt();
			switch (choice){
				case 0:
					System.exit(0);
					break;
				case 1:
					stack1.display();
					break;
				case 2:
					System.out.print("Enter element to be added: ");
					int element = keyboard.nextInt();
					stack1.push(element);
					stack1.display();
					break;
				case 3:
					stack1.pop();
					stack1.display();
					break;
				case 4:
					System.out.println(stack1.isEmpty());
					break;
				case 5:
					System.out.println(stack1.getSize());
					break;
				case 6: 
					int topElement = stack1.peek();
					if (topElement != -1){
						System.out.println("Top element is: " + topElement);
					}
					else
						system.out.println("EMPTY");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	}
}