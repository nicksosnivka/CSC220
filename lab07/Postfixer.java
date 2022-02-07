package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {


	/**
	*  Determines if the first operator has same or greater
    *  precedence than the second
	*
	* @param op1 the first operator
	* @param op2 the second operator
	* @return the boolean result
	*/
	private static boolean hasPrecedence(String op1, String op2) {
		/*
		int operator1 = (opToPrcd(op1));
		int operator2 = (opToPrcd(op2));
		
		if (operator1 >= operator2) {
			return true;
		} else {
			return false;
		}  */
		
		return opToPrcd(op1) >= opToPrcd(op2);
		
		
	}


	/**
	* Converts an operator to its precedence priority
	*
	* We expect you to be able to handle +, -, *, /, ^, and (
	* (why don't you need ")" as well? see algorithm in part 4)
	*
	* The order of these is as follows:
	*    ^, * and /, + and -, (
	*
	* @param op a string representing an operator, e.g. "+" or "-"
	* @return an integer value reflecting its precedence
	*/
	private static int opToPrcd(String op) {
		switch(op) {
			case "^":
				return 3;
			case "*":
			case "/":
				return 2;
			case "+":
			case "-":
				return 1;
			case "(":
				return 0;
		}
		
		return -1; 
		
		/*
		
		if(op.equals("^")) {
			return 3;
		} else if(op.equals("*") || op.equals("/")) {
			return 2;
		} else if(op.equals("+") || op.equals("-")) {
			return 1;
		} else {
			return 0;
		} */
	}

	/**
	* determines if the input token is an operator
	*
	* @param token the string token to check
	* @return a boolean reflecting the result
	*/
	private static boolean isOperator(String token) {
		switch(token) {
			case "^":
			case "*":
			case "/":
			case "+":
			case "-":
			case "(":
				return true;
		}
		
		return false;
	}

	/**
    * Evaluates an expression
    *
    * NOTE Beware the order of pop and evaluation when receiving operand1
    * and operand2 as input.
    *
    * @param operand1 the first operand
    * @param operator the operator to apply
    * @param operand2 the second operand
    * @return a double expressing the result
    * @throws IllegalArgumentException if operator passed is not one of
    *  "+", "-", "*", "/", or "^"
    *
	*/
	private static double evaluate(double operand1, String operator, double operand2){
		switch(opToPrcd(operator)) {
			case 3:
				return Math.pow(operand1, operand2);
			case 2:
				if (operator.equals("*")) {
					return operand1*operand2;
				} else {
					return operand1/operand2;
				}
			case 1:
				if (operator.equals("+")) {
					return operand1+operand2;
				} else {
					return operand1-operand2;
				}
		}
		
		throw new IllegalArgumentException("None of the operators were +, -, *, /, or ^");
	}


	/**
	* takes in a string line and operates the whole line in infix notation
	* @param line of String
	* @return returns the value of all executed operations involved in the line
	*/
	public static double infixEvaluator(String line){
		StringSplitter data = new StringSplitter(line);
		
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();
		
		while(data.hasNext()) {
			String token = data.next();
			
			if(!isOperator(token) && !token.equals(")")) {
				operands.push(Double.parseDouble(token));
			} else if (opToPrcd(token) == 0) {
				operators.push(token);
			} else if (token.equals(")")) {
				while(!operators.peek().equals("(")) {
					String popOp = operators.pop();
					double pop1 = operands.pop();
					double pop2 = operands.pop();
					operands.push(evaluate(pop2, popOp, pop1));
				}
				operators.pop();
			} else if (isOperator(token)) {
				String currentOperator = token;
				while(!operators.empty() && hasPrecedence(operators.peek(),currentOperator)) {
					String popOp = operators.pop();
					double pop1 = operands.pop();
					double pop2 = operands.pop();
					operands.push(evaluate(pop2,popOp,pop1));
				}
				operators.push(currentOperator);
			}
		}
		
		while(!operators.empty()) {
			String popOp = operators.pop();
			double pop1 = operands.pop();
			double pop2 = operands.pop();
			operands.push(evaluate(pop2,popOp,pop1));
		}
		
		return operands.pop();

	}

	/**
	* accepts a fully parenthesized infix expression and
	* returns a string representing an equivalent postfix expression
	* @param line fully parenthesized infix expression
	* @return string of a postfix expression equivalent to the input
	*/
	public static String toPostfix(String line){
		
		String toPostFix = "";
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();		
				
		while(data.hasNext()) {
			String token = data.next();
			if(!isOperator(token) && !token.equals(")")) {
				toPostFix += token;
			} else if(isOperator(token)) {
				String currentOperator = token;
				while(!token.equals("(") && hasPrecedence(operators.peek(), currentOperator)) {
					toPostFix += operators.pop();
				}
				operators.push(currentOperator);
			} else if(token.equals("(")) {
				operators.push(token);
			} else if(token.equals(")")) {
				while(!operators.peek().equals("(")) {
					String popOp = operators.pop();
					toPostFix += popOp;
				}
				operators.pop();
			}
		}
		
		return toPostFix; // placeholder
	}


	public static void main(String[] args){
		String line = "--------------------------------------------------";
		
		System.out.println(hasPrecedence("^","*"));
		System.out.println(hasPrecedence("+","-"));
		System.out.println(hasPrecedence("+","*"));
		System.out.println(hasPrecedence("+","-") + "\n" + line);
		
		//---------------------------------------------------------------
		
		System.out.println(opToPrcd("^"));
		System.out.println(opToPrcd("*"));
		System.out.println(opToPrcd("/"));
		System.out.println(opToPrcd("+"));
		System.out.println(opToPrcd("-"));
		System.out.println(opToPrcd("(") + "\n" + line);
		
		//---------------------------------------------------------------
		
		System.out.println(isOperator("g"));
		System.out.println(isOperator("-")+ "\n" + line);
		
		//---------------------------------------------------------------
		
		System.out.println(evaluate(5.0,"/",2.0));
		System.out.println(evaluate(3.0,"^",2.0));
		System.out.println(evaluate(3.0,"*",2.0));
		System.out.println(evaluate(6.0,"+",4.0));
		System.out.println(evaluate(10.0,"-",7.0) + "\n" + line);
		
		//---------------------------------------------------------------
		
        if (infixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");

        if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");

        if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");

        System.out.println("Lab Testing Done!!!" + "\n" + line);
		
		//---------------------------------------------------------------);
        
        
        /* uncomment the below lines for assignmemt */
		 if (!toPostfix(new String("(4+5)")).equals("45+"))
		     System.err.println("test1 failed --> should have been 45+");

		 if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		     System.err.println("test2 failed --> should have been 45+6*");

		 if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
		     System.err.println("test3 failed --> should have been 456*7/+8-");

		 if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		     System.err.println("test4 failed --> should have been 4567-*+8/");

		 if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
		     System.err.println("test5 failed --> should have been 987*654^/3*-2*+");


         System.out.println("Assignment Testing Done!!!");


	}

}
