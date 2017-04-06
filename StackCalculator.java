
package cs206b;

import java.util.Scanner;

public class StackCalculator {
	
	public StackCalculator(){
		
	}
	
	public String changeToPostfix(String infixExpression) throws UnbalancedParenthesisException{
		//Change input infix expression to postfix expression
		if(isBalanced(infixExpression)){
			LinkedStack<Character> temp = new LinkedStack<Character>();
			String postfix= new String("");
			char prvc='0';
			for (int i=0;i<infixExpression.length();i++){
				char c = infixExpression.charAt(i);
				if(i>1) prvc = infixExpression.charAt(i-2);
				//System.out.println("i is "+i);
				//System.out.println(c);
				//System.out.println(prvc);
				//System.out.println("------------------------------");
				if(isOperator(c)){
					if((c=='-')&&(isOperator(prvc))){
						postfix+=c;//negative number.
					}
					else{
						if(!temp.isEmpty()){
							if(isOperator(temp.peek())){ // stack top is operator.
								if(getPriority(c)>getPriority(temp.peek())) temp.push(c); // c has higher priority than stack top.
								else{ // c has lower or equal priority than stack top.
									postfix+=temp.pop();
									if(!temp.isEmpty()){
										if(isOperator(temp.peek())){
											if(getPriority(c)>getPriority(temp.peek())) temp.push(c);
											else{
												postfix+=' '; //consecutive pop, add blank space.
												postfix+=temp.pop();
												temp.push(c);
											}
										}// stack top is operator, pop again. Because there's no 2 operators which has same priority.
										else temp.push(c);
									}
									else temp.push(c);
								}
							}
							else temp.push(c);
						}
						else temp.push(c); // stack top is not operator.
					}
				}
				else if(isLeftParenthesis(c)){
					temp.push(c);
				}
				else if(isRightParenthesis(c)){
					if(isOperator(temp.peek())){
						postfix+=temp.pop();// pop the operator.
						temp.pop();// pop the leftparenthesis.
					}
				}
				else{ // c should be operand.
					postfix+=c;
				}
			}
			
			while(!temp.isEmpty()){//다 돌고나면 마지막에 스택에 남은 연산자가 pop이 안됨.
				if(isOperator(temp.peek())){
					postfix+=' ';
					postfix+=temp.pop();
				} // if the stack top is operator, pop and add to postfix.
				if(!temp.isEmpty()) temp.pop(); // if the stack top is not an operator, just pop.
			}
			
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

	
	public int getPriority(char operator){
		if(operator == '/' || operator == '*') return 2;
		else return 1;
	}
	
	public boolean isOperator(char c){
		boolean isOp = false;
		char[] operator = {'+','–','*','/'};
		for (char op : operator){
			if(c==op) isOp=true;
		}
		return isOp;
	}
	
	public boolean isLeftParenthesis(char c){
		boolean isLP = false;
		char[] leftparenthesis = {'(','{','['};
		for (char lp : leftparenthesis){
			if(c==lp) isLP = true;
		}
		return isLP;
	}
	public boolean isRightParenthesis(char c){
		boolean isRP = false;
		char[] rightparenthesis = {')','}',']'};
		for (char rp : rightparenthesis){
			if(c==rp) isRP = true;
		}
		return isRP;
	}
	public boolean isZeroDivision(String postfix){
		boolean zerodiv = false;
		for(int i=0;i<postfix.length();i++){
			char c = postfix.charAt(i);
			char prvc = 'n';
			char preprvc = 'n';
			if(i>2){
				prvc = postfix.charAt(i-2);
				preprvc = postfix.charAt(i-3);
			}
			
			if(c=='/'){
				if(prvc=='0' && preprvc==' ') zerodiv = true;
			}
		}
		return zerodiv;
	}
}
