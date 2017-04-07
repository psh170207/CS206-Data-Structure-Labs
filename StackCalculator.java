
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
			//infixExpression=modifyInfix(infixExpression);
			
			if(infixExpression.length()==0) return infixExpression;
			else{
				for (int i=0;i<infixExpression.length();i++){
					char c = infixExpression.charAt(i);

					if(i>1) prvc = infixExpression.charAt(i-2);

					if(isOperator(c)){
						if(c=='-' && i==0) postfix+=c;//first position.
						else if(c=='-' && (isOperator(prvc) || isLeftParenthesis(prvc))){ // after operator or after leftparenthesis.
							postfix+=c;//negative number.
						}
						else{
							while(!(temp.isEmpty()||getPriority(temp.peek())<getPriority(c)||temp.peek()=='('||temp.peek()=='{'||temp.peek()=='[')){
							postfix+=temp.pop();
							postfix+=" ";//HERE.
							}
							temp.push(c);
						}
					}
					else if(isLeftParenthesis(c)){
						temp.push(c);
					}
					else if(isRightParenthesis(c)){
						switch(c){
							case ')':
								while(temp.peek()!='('){
									postfix+=temp.peek();
									postfix+=' ';
									temp.pop();
								}
								temp.pop();
								break;
							case '}':
								while(temp.peek()!='{'){
									postfix+=temp.peek();
									postfix+=' ';
									temp.pop();
								}
								temp.pop();
								break;
							case ']':
								while(temp.peek()!='['){
									postfix+=temp.peek();
									postfix+=' ';
									temp.pop();
								}
								temp.pop();
								break;
						}
					}
					else{ // c should be operand.
						postfix+=c;
						System.out.println(postfix+"e");
					}
				}
				
				while(!temp.isEmpty()){//다 돌고나면 마지막에 스택에 남은 연산자가 pop이 안됨.
					if(isOperator(temp.peek())){
						postfix+=' ';
						System.out.println(postfix+"f");
						postfix+=temp.pop();
						System.out.println(postfix+"g");
					} // if the stack top is operator, pop and add to postfix.
					//if(!temp.isEmpty()) temp.pop(); // if the stack top is not an operator, just pop.
				}
				
				postfix=postfix.replaceAll("\\s+", " ");
				return postfix.trim();
			}
		}
		else throw new UnbalancedParenthesisException("This expression is unbalanced!!");
	}
	
	public double evaluate(String postfixExpression) throws DividedByZeroException{
		//do evaluation and return the result
		LinkedStack<String> temp = new LinkedStack<String>();
		String[] arr = postfixExpression.split(" ");
		
		for(int i=0;i<arr.length;i++){
			System.out.print("arr[i] : "+arr[i]+"///");
			if(!temp.isEmpty()) System.out.print("stack top : "+temp.peek()+"///");
			if(isOperator_str(arr[i])){
				double right = Double.parseDouble(temp.pop());
				double left = Double.parseDouble(temp.pop());
				if(arr[i].equals("+")){
						double result = right+left;
						System.out.println("addtion : "+result);
						temp.push(""+result);
				}
				else if(arr[i].equals("-")){
						double result = left-right;
						System.out.println("sub : "+result);
						temp.push(""+result);
				}
				else if(arr[i].equals("*")){
						double result = left*right;
						System.out.println("mult : "+result);
						temp.push(""+result);
				}
				else if(arr[i].equals("/")){
						double result = left/right;
						System.out.println("div : "+result);
						if(right==0) throw new DividedByZeroException("There's zero division!!");
						else temp.push(""+result);
				}
			}
			else{
				temp.push(arr[i]);
			}
		}
		double ret = Double.parseDouble(temp.pop());
		return ret;
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
		char[] operator = {'+','-','*','/'};
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
	
	public boolean isOperator_str(String s){
		boolean isOp = false;
		String[] operator = {"+","-","*","/"};
		for(String op : operator){
			if(s.equals(op)) isOp = true;
		}
		return isOp;
	}
	public String modifyInfix(String s){
		String[] arr = s.split(" ");
		String temp ="";
		for(int i=0;i<arr.length;i++){
			if(isOperator_str(arr[i])){
				temp+=(arr[i]+" ");
			}
			else{
				if(arr[i].charAt(0)=='-'){
					int len = arr[i].length();
					temp+="( 0 - "+arr[i].substring(1,len)+" ) ";
				}
				else temp+=arr[i]+" ";
			}
		}
		int len_temp = temp.length();
		return temp.substring(0,len_temp-1);
	}
	
}
