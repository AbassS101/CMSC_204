
public class Notation {

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
	    MyQueue operatorQueue = new MyQueue();
	    MyStack stack = new MyStack();
	    String postfix= "";
	    for (int i=0; i< infix.length();i++) {
	        if (infix.charAt(i) == ' ')
	            continue;
	        else if (Character.isDigit(infix.charAt(i))) {
	            // If the character is a digit, enqueue it directly
	            try {
	                operatorQueue.enqueue(infix.charAt(i));
	            } catch (QueueOverflowException e) {
	                // Handle queue overflow exception
	                System.out.print(e.getMessage());
	            }
	        }
	        else if (infix.charAt(i) == '(') {
	            // If it's a left parenthesis, push it onto the stack
	            try {
	                stack.push(infix.charAt(i));
	            } catch (StackOverflowException e) {
	                System.out.print(e.getMessage());
	            }
	        }
	        else if (infix.charAt(i) == ')') {
	            // If it's a right parenthesis, pop operators from the stack
	            // and enqueue them until a left parenthesis is encountered
	            try {
	                while (!stack.isEmpty() && (char) stack.top() != '(') {
	                    operatorQueue.enqueue(stack.pop());
	                }
	                if (stack.isEmpty())
	                    throw new InvalidNotationFormatException();
	                stack.pop(); // Discard the left parenthesis
	            } catch (QueueOverflowException | StackUnderflowException e) {
	                System.out.print(e.getMessage());
	            }
	        } else { // If it's an operator
	            // Determine precedence and handle accordingly
	            try {
	                while (!stack.isEmpty() && (
	                    (infix.charAt(i) == '+' || infix.charAt(i) == '-') && (char) stack.top() != '(' ||
	                    ((infix.charAt(i) == '*' || infix.charAt(i) == '/' || infix.charAt(i) == '%') && (char) stack.top() == '*' || (char) stack.top() == '/' || (char) stack.top() == '%'))) {
	                    operatorQueue.enqueue(stack.pop());
	                }
	                stack.push(infix.charAt(i)); // Push current operator onto the stack
	            } catch (StackUnderflowException | QueueOverflowException | StackOverflowException e) {
	                System.out.print(e.getMessage());
	            }
	        }
	    }
	    // Enqueue any remaining operators from the stack
	    while (!stack.isEmpty()) {
	        try {
	            char x = (char) stack.pop();
	            if (x == '(')
	                throw new InvalidNotationFormatException();
	            else
	                operatorQueue.enqueue(x);
	        } catch (StackUnderflowException | InvalidNotationFormatException | QueueOverflowException e) {
                System.out.print(e.getMessage());
	        }
	    }
	    // Construct the postfix expression by dequeuing from the queue
	    while (!operatorQueue.isEmpty()) {
	        try {
	            postfix += operatorQueue.dequeue();
	        } catch (QueueUnderflowException e) {
                System.out.print(e.getMessage());
	        }
	    }
	    return postfix;
	}


	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack operatorStack = new MyStack();
		double result = 0;
		for (int i=0; i < postfixExpr.length(); i++) {
			char x = postfixExpr.charAt(i);
			
			if (x == ' ') //Ignores Spaces
				continue;
			if (Character.isDigit(x)) {//Checks if the character is a digit
				try {
					operatorStack.push((double) Character.getNumericValue(x));
				} catch (StackOverflowException e) {
	                System.out.print(e.getMessage());
				}
			}
			else { //Check if the character is an operator
				if (operatorStack.size() < 2)
					throw new InvalidNotationFormatException();
				try {
					double operand2 = (double) operatorStack.pop();
					double operand1 = (double) operatorStack.pop();
					switch (x) {
	                case '+':
	                    result = operand1 + operand2;
	                    break;
	                case '-':
	                    result = operand1 - operand2;
	                    break;
	                case '*':
	                    result = operand1 * operand2;
	                    break;
	                case '/':
	                    if (operand2 == 0) {
	    					throw new InvalidNotationFormatException();
	                    }
	                    result = operand1 / operand2;
	                    break;
	                default:
	                	System.out.println("Invalid operator: " + x);
	            }
				} catch (StackUnderflowException e) {
	                System.out.print(e.getMessage());
				}
				try {
					operatorStack.push(result);
				} catch (StackOverflowException e) {
	                System.out.print(e.getMessage());
				}
			}
		}
		if (operatorStack.size() != 1)
			throw new InvalidNotationFormatException();
		try {
			return (double) operatorStack.pop();
		} catch (StackUnderflowException e) {
            System.out.print(e.getMessage());
		}
		return 0;
	}
	
	public static String convertPostfixToInfix(String postFix) throws InvalidNotationFormatException {
		MyStack stack = new MyStack();
		String str = "";
		String infix = "";
		for(int i=0; i < postFix.length(); i++) {
			if (postFix.charAt(i) == ' ')
				continue;
			else if (Character.isDigit(postFix.charAt(i))) {
				try {
					stack.push(postFix.charAt(i));
				} catch (StackOverflowException e) {
	                System.out.print(e.getMessage());
				}
			}
			else if (postFix.charAt(i) == '+' || postFix.charAt(i) == '-' || postFix.charAt(i) == '/' || postFix.charAt(i) == '%' || postFix.charAt(i) == '*') {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				try {
					String second = stack.pop().toString();
					String first = stack.pop().toString();
					str = "(" + first + postFix.charAt(i) + second + ")";
					stack.push(str);
				} catch (StackUnderflowException | StackOverflowException e) {
	                System.out.print(e.getMessage());
				}
			}
			
		}

		if (stack.size() == 1) {
			try {
				infix += stack.pop().toString();
			} catch (StackUnderflowException e) {
                System.out.print(e.getMessage());
			}
		}
		else
			throw new InvalidNotationFormatException();
		return infix;
	}

}
