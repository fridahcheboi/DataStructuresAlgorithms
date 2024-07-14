/**

Constant time - O(1)
 - Enqueue()
 - Dequeue()
 - Front()
 - IsEmpty()
 - Size()
 - Rear()
 
 Conditions: -- has FIXED SIZE because of the array
 - Overflow() inserting in full queue 
 - Underflow() deletion from empty queue

*/

import java.util.Scanner;

// This is a simple array-based implementation of a QUEUE that stores integers
public class ArrayQueue{
	
	private int[] array1 = new int[50];
	int front = -1; // index of first element
	int rear = -1; // index of last element
	int size = 0;
	
	// adding at the REAR
	public void enqueue(int element){
		// check if queue is full
		if (rear == 49){
			System.out.println("OVERFLOW");
			return;
		}
		else{ 
			if (front == -1)
				front = 0;
			rear++;
			array1[rear] = element;
			size++;
		}
	}
	
	// deleting at the FRONT
	public void dequeue(){
		// check if queue is empty
		if (rear == -1 || front>rear){
			System.out.println("UNDERFLOW");
		}
		else{
			// element = array1[front];
			front++;
			size--;
		}
	}
	
	public boolean isEmpty(){
		return rear == -1 || front>rear;	
	}
	public int front(){
		if (isEmpty()) {
            System.out.println("QUEUE IS EMPTY");
            return -1;
        }
		return array1[front];

	}
	
	public int rear(){
		if (isEmpty()) {
            System.out.println("QUEUE IS EMPTY");
            return -1;
        }
		return array1[rear];

	}
	public void display(){
		System.out.println();
		if (isEmpty())
			System.out.println("EMPTY");
		else{
			for (int i=front;i<=rear;i++){
				System.out.print(array1[i] + " ");
			}
		}
	}
	
	public int getSize(){
		return size;
	}
	
	
	public static void main(String[] args){
		System.out.println("\nThis is a simple array-based implementation of a QUEUE for integers");
		ArrayQueue queue1 = new ArrayQueue();
		
		while(true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nSelect the operation to be performed below: \n1. Display queue\n2. Enqueue queue\n3. Dequeue queue\n4. Check if empty\n5. Get size of queue \n6. Get front element\n7. Get rear element");
		
			
			int choice = keyboard.nextInt();
			switch (choice){
				case 0:
					System.exit(0);
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
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	}
}
