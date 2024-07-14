import java.util.Scanner;

public class ArrayStack{
	int length = 20;
	int[] array1 = new int[length];
	private int top = -1;
	int sizeOfStack = 0;
	
	public boolean isEmpty(){
		return top == -1;
	}
	
	public void push(int element){
		if (top == length-1){
			System.out.println("OVERFLOW");
		}
		else{
			top++;
			array1[top] = element;
			sizeOfStack++;
		}
	}
	
	public void pop(){
		if (top == -1){
			System.out.println("UNDERFLOW");
		}
		else{
			top--;
			sizeOfStack--;
		}
	}
	
	public void display(){
		if (isEmpty())
			System.out.println("EMPTY");
		else{
			for(int i=0; i<sizeOfStack;i++){
				System.out.print(array1[i] + " ");
			}
		}
	}
	
	public int getSize(){
		return sizeOfStack;
	}
	
	public int peek(){
		if (top == -1){
			System.out.println("EMPTY");
			return -1;
		}
		return array1[top];
	}
	
	public static void main(String[] args){
		System.out.println("\nThis is a array-based implementation of a STACK for integers");
		ArrayStack stack1 = new ArrayStack();
		
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
						System.out.println("EMPTY");
					break;
				default:
					System.out.println("Invalid choice!");
					break;
			}
		}
	}
}