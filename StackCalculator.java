
package cs206b;

import java.util.Scanner;

public class StackCalculator {
	
	public StackCalculator(){
		
	}
	
	public String changeToPostfix(String infixExpression) throws UnbalancedParenthesisException{
		//Change input infix expression to postfix expression
		if(isBalanced(infixExpression)){
			LinkedStack<String> temp = new LinkedStack<String>();
			String postfix= new String("");
			char[] operator = {'+','-','*','/'};
			char[] parenthesis = {'(',')','{','}','[',']'};
			for (int i=0;i<infixExpression.length();i++){
				char c = infixExpression.charAt(i);

			}
			System.out.println((postfix+"31234").charAt(1));
			return postfix;
		}
		else throw new UnbalancedParenthesisException("This expression is unbalanced!!");
	}
	
	public double evaluate(String postfixExpression) throws DividedByZeroException{
		//do evaluation and return the result
		return 0.0;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isBalanced(String expression){
		final char LN = '(';
		final char LC = '{';
		final char LS = '[';
		final char RN = ')';
		final char RC = '}';
		final char RS = ']';
		
		LinkedStack<Character> temp = new LinkedStack<Character>();
		boolean vaild = true;
		
		for (int i=0; vaild && (i<expression.length());i++){
			switch (expression.charAt(i)){
				case LN:
				case LC:
				case LS:
					temp.push(expression.charAt(i));
					break;
				case RN:
					if(temp.isEmpty() || (temp.pop() != LN)) vaild = false;
					break;
				case RC:
					if(temp.isEmpty() || (temp.pop() != LC)) vaild = false;
					break;
				case RS:
					if(temp.isEmpty() || (temp.pop() != LS)) vaild = false;
					break;
			}
		}
	return temp.isEmpty() && vaild;
	}
}
