package cs206b;

public class DoubleLinkedSeq {
	
	private int manyNodes;
	private DoubleNode head;
	private DoubleNode tail;
	private DoubleNode cursor;
	private DoubleNode precursor;
	
	public DoubleLinkedSeq() {
	}
	
	public void addAfter(double element) {
		try{
			
		}catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAfter(): Not enough memory for creating new node.");	
		}
}

	public void addBefore(double element) {
		try{

		} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addBefore(): Not enough memory for creating new node.");	
		}
	}
	
	public void addAll(DoubleLinkedSeq addend) {
		if(false) {
			throw new NullPointerException("addAll(): input parameter is null");
		} else {
			try{

			} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAll(): Not enough memory for creating new list.");
			}
		}
	}
	
	public void advance() {
		if(false) {

		} else {
			throw new IllegalStateException("advance(): There are no current cursor.");
		}
	}
	
	public Object clone() {
		try{

		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("clone(): Not enough memory for creating new sequence.");
		}
		return null;
	}
	
	public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
		if(false) {
			throw new IllegalArgumentException("concaternation(s1, s2): at least one arguments is null");
		}
		
		try {
		
		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("concaternation(s1, s2): Not enough memory.");
		}
		return null;
	}
	
	public double getCurrent() {
		if(false) {

		} else {
			throw new IllegalStateException("getCurrent(): There is no current element.");
		}
		return -1;
	}
	
	public boolean isCurrent() {
		return false;
	}
	
	public void removeCurrent() {
		if(false) {

		} else {
			throw new IllegalStateException("removeCurrent(): There is no current element.");
		}
	}
	
	public int size() {
		return -1;
	}
	
	public void start() {
		
	}

	public void removeNegative() {
		
	}

	public void removeMostDuplicate() {
		
	}
	
	
	//Do not modify below methods.
	private boolean isEqual(double a, double b) {
		return Math.abs(a-b) <= 0.000001 ;
	}

	//returns String of the sequence of DoubleLinkedSeq.
	//needs size(), start(), getCurrent(), and advance().
	public String toString() {
		if(this.size() == 0) {
			return "no elements";
		}
		String ret = "";
		
		DoubleNode tempCursor = getCursor();
		this.start();
		
		ret += "many: " + this.size() + "\n";
		ret += "Sequence:";
		for(int i = 0; i < this.size(); i++) {
			ret += " "+ this.getCurrent();
			this.advance();
		}
		ret += "\n";
		
		if(tempCursor == null) {
			cursor = tempCursor;
		} else {
			this.start();
			while(this.getCursor() != tempCursor)
				this.advance();
		}
        return ret;
	}

	//Do not modify below methods, that is just need for grading code. 
	//Also you don't need that methods for your homework
	 
	public DoubleNode getHead() {
		return head;
	}
	
	public DoubleNode getCursor() {
		return cursor;
	}
	
	public DoubleNode getPrecursor() {
		return precursor;
	}
	
	public DoubleNode getTail() {
		return tail;
	}
	//////////////////////////////////////////////
}
