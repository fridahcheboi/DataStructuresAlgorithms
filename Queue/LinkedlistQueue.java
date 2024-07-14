import java.util.Scanner;

public class LinkedlistQueue{
	
	private static class Node{
		int data;
		Node next;
		
		public Node(int data){
			this.data = data;
		}
	}
	
	private Node head; // remove at head
	private Node tail; // add at tail
	private int size;
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void enqueue(int element){
		Node newNode = new Node(element);
		if (tail != null){
			 tail.next = newNode;
		}
		tail = newNode;
		size++;
		if (head == null){
			head = newNode;
		}
		
		
	}
	public void dequeue(){
		if (!isEmpty()){
			head = head.next;
			if (head == null){
				tail = null;
			}
			size++;
		}
		else 
			System.out.println("EMPTY");
		
	}
	
	// this is also the peek method
    public int front(){
		if (isEmpty()){
			return -1;
		}
		return head.data;
	}

	public int getSize(){
		return size;
	}
	public int rear(){
		if (isEmpty()){
			return -1;
		}
		return tail.data;
	}
	
	public void display(){
		Node temp = head;
		if (isEmpty()){
			System.out.println("EMPTY");
		}
		else {
			while (temp!= null){
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}
 
	
	
	public static void main(String[] args){

		System.out.println("\nThis is a linkedlist-based implementation of a QUEUE for integers");
		LinkedlistQueue queue1 = new LinkedlistQueue();
		
		while(true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nSelect the operation to be performed below: \n0. Exit\n1. Display queue\n2. Enqueue queue\n3. Dequeue queue\n4. Check if empty\n5. Get size of queue \n6. Get front element\n7. Get rear element");
		
			
			int choice = keyboard.nextInt();
			switch (choice){
				case 0:
					System.exit(0);
					break;
				case 1:
					queue1.display();
					break;
				case 2:
					System.out.print("Enter element to be added: ");
					int element = keyboard.nextInt();
					queue1.enqueue(element);
					queue1.display();
					break;
				case 3:
					queue1.dequeue();
					queue1.display();
					break;
				case 4:
					System.out.println(queue1.isEmpty());
					break;
				case 5:
					System.out.println(queue1.getSize());
					break;
				case 6: 
					int frontElement = queue1.front();
					if (frontElement != -1){
						System.out.println("Front element is: " + frontElement);
					}
					break;
				case 7:
					int rearElement = queue1.rear();
					if (rearElement != -1){
						System.out.println("Rear element is: " + rearElement);
					}
					else
						System.out.println("EMPTY");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	}
}