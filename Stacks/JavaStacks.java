/**
Stacks are used to 
 - balance symbols:{{{{[({{}})}}}}
 - express evaluation: infix to postfix - precedence
infix is inefficient to a computer
A+B/C*D-E/(F+G)
FG+
BC/
BC/D*
EFG+/
ABC/D*+
ABC/D*+EFG+/-

3+5*(5/5)-2^2
22^
55/
555/*
3555/*+22^- correct! the expression will be scanned from left to right. as soon as we encounter an operator we will apply it to the last to operands

7+5*3/5^1+(3+2)
 - method call
 - web browser navigation

*/





// STACK CLASS OF THE JAVA COLLECTIONS FRAMEWORK... Array-based
//  
import java.util.Stack;

public class JavaStacks{
	public static void main(String[] args){
	Stack<Integer> stack = new Stack<>(); 
	// THE SHUNTING YARD ALGORITHM developed by Edsger Dijkstra
	// Step1: empty stack output-list
	// Step2: read expression from left to right
	// Step3: Each token (operand, operator, or parenthesis) in the infix expression:

	/** If the token is an operand (e.g., a number or variable), add it to the output list.
	If the token is an operator (e.g., +, -, , /):
	While there is an operator at the top of the stack with greater than or equal precedence to the current token, pop operators from the stack to the output list.
	Push the current operator onto the stack.
	If the token is a left parenthesis (, push it onto the stack.
	If the token is a right parenthesis ):
	Pop from the stack to the output list until a left parenthesis ( is at the top of the stack.
	Pop the left parenthesis from the stack (but do not add it to the output list).
	After reading all tokens:

	Pop any remaining operators in the stack to the output list.
	*/
	//Step4: Pop any remaining operators to the output list
	String infixExpression = "A+B*C";
    String postfixExpression = infixToPostfix(infixExpression);
    System.out.println("Infix: " + infixExpression);
    System.out.println("Postfix: " + postfixExpression);
	
	}
	private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // Function to convert infix expression to postfix expression
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            // If the token is an operand, add it to the output
            if (Character.isLetterOrDigit(token)) {
                result.append(token);
            }
            // If the token is '(', push it to the stack
            else if (token == '(') {
                stack.push(token);
            }
            // If the token is ')', pop and output from the stack until '(' is found
            else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            // An operator is encountered
            else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    result.append(stack.pop());
                }
                stack.push(token);
            }
        }

        // Pop all the operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
	
}