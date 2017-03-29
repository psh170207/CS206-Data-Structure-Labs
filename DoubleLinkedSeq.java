package cs206b;

public class DoubleLinkedSeq implements Cloneable{
	
	private int manyNodes;
	private DoubleNode head;
	private DoubleNode tail;
	private DoubleNode cursor;
	private DoubleNode precursor;
	
	public DoubleLinkedSeq() {
		manyNodes=0;
		head = null;
		tail = head;
		cursor = head;
		precursor = null;
	}
	
	public void addAfter(double element) {
		try{
			if(isCurrent()){
				cursor.addNodeAfter(element);
				advance();
				setPrecursor();
				setTail();
			}
			else{
				if(manyNodes==0){
					head = new DoubleNode(element,null);
					tail = head;
					cursor = head;
				}
				else{
					setTail();
					tail.addNodeAfter(element);
					precursor = tail;
					tail = tail.getLink();
					cursor = tail;
				}
			}
			manyNodes++;
		}catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAfter(): Not enough memory for creating new node.");	
		}
	}

	public void addBefore(double element) {
		try{
			if(isCurrent()){
				cursor.setLink(new DoubleNode(cursor.getData(), cursor.getLink()));
				cursor.setData(element);
			}
			else{
				if(manyNodes==0){
					head = new DoubleNode(element,null);
					tail = head;
					cursor = head;
				}
				else{
					head = new DoubleNode(element,head);
					cursor = head.getLink();
					precursor = head;
				}
			}
			manyNodes++;
		} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addBefore(): Not enough memory for creating new node.");	
		}
	}
	
	public void addAll(DoubleLinkedSeq addend) {
		if(addend == null) {
			throw new NullPointerException("addAll(): input parameter is null");
		} else {
			try{
				DoubleNode argcursor = addend.getHead();
				setTail();
				while(argcursor!=null){
					tail.addNodeAfter(argcursor.getData());
					tail = tail.getLink();
					argcursor=argcursor.getLink();
					manyNodes++;
				}
			} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAll(): Not enough memory for creating new list.");
			}
		}
	}
	
	public void advance() {
		if(isCurrent()) {
			precursor = cursor;
			cursor = cursor.getLink();
		} else {
			throw new IllegalStateException("advance(): There are no current cursor.");
		}
	}
	
	public Object clone() {
		try{
			DoubleLinkedSeq ret;
			try{
				ret = (DoubleLinkedSeq) super.clone();
			}
			catch(CloneNotSupportedException e){
			 throw new RuntimeException("This class does not implements Cloneable.");
			}
			ret.head = DoubleNode.listCopy(head);
			return ret;
		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("clone(): Not enough memory for creating new sequence.");
		}
	}
	
	public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
		if(s1==null || s2==null) {
			throw new IllegalArgumentException("concaternation(s1, s2): at least one arguments is null");
		}
		try {
			DoubleLinkedSeq ret = new DoubleLinkedSeq();
			DoubleNode cur1 = s1.getHead();
			DoubleNode cur2 = s2.getHead();
			while(cur1!=null){
				ret.addAfter(cur1.getData());
				cur1=cur1.getLink();
			}
			while(cur2!=null){
				ret.addAfter(cur2.getData());
				cur2=cur2.getLink();
			}
			ret.setCursor(null);
			return ret;
		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("concaternation(s1, s2): Not enough memory.");
		}
	}
	
	public double getCurrent() {
		if(isCurrent())	return cursor.getData();
		else throw new IllegalStateException("getCurrent(): There is no current element.");
	}
	
	public boolean isCurrent() {
		return cursor!=null;
	}
	
	public void removeCurrent() {
		if(isCurrent()) {
			if(manyNodes==1){
				head=null;
				tail=null;
				cursor=null;
				precursor=null;
			}
			else if(cursor == head){
				head = head.getLink();
				cursor = head;
				precursor = null;
			}
			else if(cursor == tail){
				setPrecursor();
				precursor.setLink(null);
				tail.setData(precursor.getData());
				tail.setLink(null);
				cursor = null;
				setPrecursor();
			}
			else{
				cursor = cursor.getLink();
				setTail();
				setPrecursor();
			}
			manyNodes--;
		} else {
			throw new IllegalStateException("removeCurrent(): There is no current element.");
		}
	}
	
	public int size() {
		return manyNodes;
	}
	
	public void start() {
		if(head!=null) cursor = head;
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
	
	//This is the methods which declared additionally.
	public void setHead(DoubleNode newhead){
		head = newhead;
	}
	
	public void setTail(DoubleNode newtail){
		tail = newtail;
	}
	
	public void setManyNodes(int num){
		manyNodes = num;
	}
	
	public void setCursor(DoubleNode cur){
		cursor = cur;
	}
	
	public void setPrecursor(){
		if(cursor!=head){
			DoubleNode temp = head;
			while(temp.getLink()!=cursor) temp = temp.getLink();
			precursor = temp;
		}
		else precursor = null;
	}
	
	public void setTail(){
		DoubleNode temp = head;
		while(temp.getLink()!=null) temp = temp.getLink();
		tail = temp;
	}
}
