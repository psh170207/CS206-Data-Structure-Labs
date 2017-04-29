package elice;

import java.lang.IllegalArgumentException;

public class RecursiveCalculator {
      String log = "";
      // You can add variables or methods.

	// DO NOT EDIT calculate METHOD
      public double calculate(String input){
            return parseExpr(input);
      }

      public double parseExpr(String expr) {
	  	if(expr.length()==0) throw new IllegalArgumentException("Empty String!!");
	  	log += "expr:" + expr + "\n"; //DO NOT MODIFY THIS LINE
		int lastp = -1; // lastest plus.
		int lastm = -1; // lastest minus.
		int cnt = 0; // # of left parenthesis.
		for(int i=0 ; i<expr.length() ; i++){
			char c = expr.charAt(i);
			if(c == '+') {
				if(cnt==0) lastp = i; // find + or -, and store lastest + or - to 'lastp'.
				//Inside parenthesis, operator will be ignored.
			}
			else if(c == '-') {
				if(cnt==0) lastm = i; // find + or -, and store lastest + or - to 'lastm'.
				//Inside parenthesis, operator will be ignored.
			}
			else if(c == '('){
				cnt++;
			}
			else if(c == ')'){
				cnt--;
			}// if right parenthesis appears, left parenthesis will be ignored.
		}
		if(lastp == -1 && lastm == -1) return parseTerm(expr); // there's no + or -.
		else{
			if(lastp>lastm){ // The lastest operator is +.
				return parseExpr(expr.substring(0,lastp-1))+parseTerm(expr.substring(lastp+2,expr.length()));
			}
			else{// The lastest operator is -.
				return parseExpr(expr.substring(0,lastm-1))-parseTerm(expr.substring(lastm+2,expr.length()));
			}
		}
      }

      public double parseTerm(String term) {
        log += "term:" + term + "\n"; //DO NOT MODIFY THIS LINE
		int lastmult = -1; // lastest plus.
		int lastdiv = -1; // lastest minus.
		int cnt = 0;
		for(int i=0 ; i<term.length() ; i++){
			char c = term.charAt(i);
			if(c == '*') {
				if(cnt == 0) lastmult = i; // find * and store lastest * to 'lastmult'.
				//Inside parenthesis, operator will be ignored.
			}
			else if(c == '/') {
				if(cnt == 0) lastdiv = i; // find / and store lastest / to 'lastdiv'.
				//Inside parenthesis, operator will be ignored.
			}
			else if(c == '(') cnt++;
			else if(c == ')') cnt--;
			// if right parenthesis appears, left parenthesis will be ignored.
		}
		if(lastmult == -1 && lastdiv == -1) return parsePower(term); // there's no * or /.
		else{
			if(lastmult>lastdiv){ // The lastest operator is *.
				return parseTerm(term.substring(0,lastmult-1))*parsePower(term.substring(lastmult+2,term.length()));
			}
			else{// The lastest operator is /.
				return parseTerm(term.substring(0,lastdiv-1))/parsePower(term.substring(lastdiv+2,term.length()));
			}
		}
      }

      public double parsePower(String power) {
        log += "power:" + power + "\n"; //DO NOT MODIFY THIS LINE
		int first = -1; // lastest ^.
		int cnt = 0;
		for(int i=0 ; i<power.length() ; i++){
			char c = power.charAt(i);
			if(c == '^') {
				if(cnt == 0){
					first = i;
					break; // find first ^.
				}//Inside parenthesis, operator will be ignored.
			}
			else if(c == '(') cnt++;
			else if(c == ')') cnt--;// if right parenthesis appears, left parenthesis will be ignored.
		}
		if(first == -1) return parseFactor(power); // there's no ^.
		else{
			double a = parseFactor(power.substring(0,first-1));
			double b = parsePower(power.substring(first+2,power.length()));
			return Math.pow(a,b);
		}
      }

      public double parseFactor(String factor) {
        log += "factor:" + factor + "\n"; //DO NOT MODIFY THIS LINE
		int cnt = 0;
		for(int i = 0 ; i<factor.length() ; i++){
			char c = factor.charAt(i);
			if(c == '(') cnt++;
		}
		if(cnt==0) return Double.parseDouble(factor); // if there's no left parenthesis, factor is number.
		else{
			return parseExpr(factor.substring(2,factor.length()-2)); // if there's left parenthesis, factor is (expr).
		}
      }
}
