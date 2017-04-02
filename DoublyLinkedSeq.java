package cs206b;

public class DoublyLinkedSeq<T> implements Cloneable{
	/*
	 * You should be use a dummy node as a first node of this sequence.
	 * please note that dummy node is not "existing node", so must not be count as a size of sequence
	 * Also, at the constructor, head and tail point to dummy.
	 */
	private Node dummyHead, dummyTail, head, tail, cursor;
	private T t;
	private int manyNodes;
	
	public DoublyLinkedSeq() {
		manyNodes=0;
		dummyHead = new Node(null,null,null);
		dummyTail = new Node(null,null,dummyHead);
		dummyHead.setLink(dummyTail);
		head = dummyHead;
		tail = dummyTail;
		cursor = null;
	}
	
	public void addAfter(T element)
	{
		try{
			if(isCurrent()){
				Node temp = new Node(element,cursor.getLink(),cursor);
				cursor.getLink().setPrv(temp);
				cursor.setLink(temp);
				advanceForward();
			}
			else{
				if(manyNodes==0){
					Node temp = new Node(element,dummyTail,dummyHead);
					dummyHead.setLink(temp);
					dummyTail.setPrv(temp);
					cursor = temp;
				}
				else{
					Node temp = new Node(element,dummyTail,dummyTail.getPrv());
					dummyTail.getPrv().setLink(temp);
					dummyTail.setPrv(temp);
					cursor = temp;
				}
			}
			manyNodes++;
		}catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAfter(): Not enough memory for creating new node.");	
		}
	}
	
	public void addBefore(T element)
	{
		try{
			if(isCurrent()){
				Node temp = new Node(element, cursor, cursor.getPrv());
				cursor.getPrv().setLink(temp);
				cursor.setPrv(temp);
				advanceBackward();
			}
			else{
				if(manyNodes==0){
					Node temp = new Node(element,dummyTail,dummyHead);
					dummyHead.setLink(temp);
					dummyTail.setPrv(temp);
					cursor = temp;
				}
				else{
					Node temp = new Node(element, dummyHead.getLink(), dummyHead);
					dummyHead.getLink().setPrv(temp);
					dummyHead.setLink(temp);
					cursor = temp;
				}
			}
			manyNodes++;
		} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addBefore(): Not enough memory for creating new node.");	
		}
	}

	public void addAll(DoublyLinkedSeq<T> addend)
	{
		if(addend == null) {
			throw new NullPointerException("addAll(): input parameter is null");
		} else {
			try{
				Node argcursor = addend.getHead();
				Node argtail = addend.getTail();
				while(argcursor!=argtail){
					Node temp = new Node(argcursor.getData(),dummyTail,dummyTail.getPrv());
					dummyTail.getPrv().setLink(temp);
					dummyTail.setPrv(temp);
					argcursor=argcursor.getLink();
					manyNodes++;
				}
			} catch(OutOfMemoryError e) {
				throw new OutOfMemoryError("addAll(): Not enough memory for creating new list.");
			}
		}
	}

	public void advanceForward()
	{
		if(isCurrent()){
			if(cursor.getLink()!=null) cursor=cursor.getLink();
			else cursor = null;
		}
		else throw new IllegalStateException("There's no current element!");
	}

	public void advanceBackward()
	{
		if(isCurrent()){
			if(cursor.getPrv()!=null) cursor=cursor.getPrv();
			else cursor = null;
		}
		else throw new IllegalStateException("There's no current element!");
	}


	public void advanceNstepForward(int n)
	{
		if(isCurrent()){
			boolean valid = true;
			Node temp = cursor;
			for(int i=0;i<n;i++){
				if(temp.getLink()!=null) temp = temp.getLink();
				else valid = false;
			}
			
			if(valid) for(int i=0;i<n;i++) advanceForward();
			else throw new IllegalStateException("There is no element at target position!");
			}
		else throw new IllegalStateException("There is no current element!");
	}
	
	public void advanceNstepBackward(int n)
	{
		if(isCurrent()){
			boolean valid = true;
			Node temp = cursor;
			for(int i=0;i<n;i++){
				if(temp.getPrv()!=null) temp = temp.getPrv();
				else valid = false;
			}
			
			if(valid) for(int i=0;i<n;i++) advanceBackward();
			else throw new IllegalStateException("There is no element at target position!");
			}
		else throw new IllegalStateException("There is no current element!");
	}

	public static <T> DoublyLinkedSeq<T> concatenation(DoublyLinkedSeq s1, DoublyLinkedSeq s2)
	{
		if(s1==null || s2==null) {
			throw new IllegalArgumentException("concaternation(s1, s2): at least one arguments is null");
		}
		try {
			DoublyLinkedSeq<T> ret = new DoublyLinkedSeq<T>();
			
			Node cur1 = s1.getHead();
			cur1 = cur1.getLink();
			Node tail1 = s1.getTail();
			
			Node cur2 = s2.getHead();
			cur2 = cur2.getLink();
			Node tail2 = s2.getTail();
			
			Node cur = ret.getHead();
			
			while(cur1!=tail1){
				Node temp = new Node(cur1.getData(),cur.getLink(),cur);
				cur.getLink().setPrv(temp);
				cur.setLink(temp);
				cur1=cur1.getLink();
				cur = cur.getLink();
			}
			while(cur2!=tail2){
				Node temp = new Node(cur2.getData(),cur.getLink(),cur);
				cur.getLink().setPrv(temp);
				cur.setLink(temp);
				cur2=cur2.getLink();
				cur = cur.getLink();
			}
			ret.setCursor(null);
			ret.setManyNodes(s1.size()+s2.size());
			return ret;
			
		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("concaternation(s1, s2): Not enough memory.");
		}
	}

	public DoublyLinkedSeq<T> clone()
	{
		try{
			DoublyLinkedSeq<T> ret;
			try{
				ret = (DoublyLinkedSeq<T>) super.clone();
			}
			catch(CloneNotSupportedException e){
			 throw new RuntimeException("This class does not implements Cloneable.");
			}
			ret.head = Node.listCopy(head);
			return ret;
		} catch(OutOfMemoryError e) {
			throw new OutOfMemoryError("clone(): Not enough memory for creating new sequence.");
		}
	}
	public T getCurrent()
	{
		if(isCurrent()) return (T) cursor.getData();
		else throw new IllegalStateException("There's no current element!");
	}

	public boolean isCurrent()
	{
		return cursor!=null;
	}

	public void removeCurrent()
	{
		if(isCurrent()) {
			if(manyNodes==1){
				dummyHead.setLink(dummyTail);
				dummyTail.setPrv(dummyHead);
				cursor = null;
			}
			else if(cursor == dummyHead.getLink()){
				dummyHead.setLink(cursor.getLink());
				cursor.getLink().setPrv(dummyHead);
				cursor = dummyHead.getLink();
			}
			else if(cursor == dummyTail.getPrv()){
				dummyTail.setPrv(cursor.getPrv());
				cursor.getPrv().setLink(dummyTail);
				cursor = dummyTail.getPrv();
			}
			else{
				cursor.getLink().setPrv(cursor.getPrv());
				cursor.getPrv().setLink(cursor.getLink());
				cursor = cursor.getLink();
			}
			manyNodes--;
		} else {
			throw new IllegalStateException("removeCurrent(): There is no current element.");
		}
	}

	public int size()
	{
		return manyNodes;
	}

	public void start()
	{
		if(dummyHead.getLink()!=null) cursor = dummyHead.getLink();
	}
	
	//////////////// self-declared part //////////////////////////////
	public Node<T> getHead(){
		return dummyHead;
	}
	public void setCursor(Node<T> node){
		cursor = node;
	}
	public Node<T> getTail(){
		return dummyTail;
	}
	public void setManyNodes(int num){
		manyNodes = num;
	}
}
