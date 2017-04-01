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
		dummyHead = new Node(null,dummyTail,null);
		dummyTail = new Node(null,null,dummyHead);
		head = dummyHead;
		tail = dummyTail;
		cursor = null;
	}
	
	public void addAfter(T element)
	{
		try{
			if(isCurrent()){
				Node temp = new Node(element,cursor.getLink(),cursor);
				cursor.getLink().setBlink(temp);
				cursor.setLink(temp);
				advanceForward();
			}
			else{
				if(manyNodes==0){
					Node temp = new Node(element,dummyTail,dummyHead);
					cursor = temp;
				}
				else{
					Node temp = new Node(element,dummyTail,dummyTail.getBlink());
					dummyTail.setBlink(temp);
					dummyTail.getBlink().setLink(temp);
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
				Node temp = new Node(element, cursor, cursor.getBlink());
				cursor.getBlink().setLink(temp);
				cursor.setBlink(temp);
				advanceBackward();
			}
			else{
				if(manyNodes==0){
					Node temp = new Node(element,dummyTail,dummyHead);
					cursor = temp;
				}
				else{
					Node temp = new Node(element, dummyHead.getLink(), dummyHead);
					dummyHead.getLink().setBlink(temp);
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

	}

	public void advanceForward()
	{
		if(isCurrent()){
			cursor=cursor.getLink();
		}
		else throw new IllegalStateException("There's no current element!");
	}

	public void advanceBackward()
	{
		if(isCurrent()){
			cursor=cursor.getBlink();
		}
		else throw new IllegalStateException("There's no current element!");
	}


	public void advanceNstepForward(int n)
	{

	}
	public void advanceNstepBackward(int n)
	{

	}

	public static <T> DoublyLinkedSeq<T> concatenation(DoublyLinkedSeq s1, DoublyLinkedSeq s2)
	{
		return null;
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

	}

	public int size()
	{
		return manyNodes;
	}

	public void start()
	{
		if(dummyHead.getLink()!=null) cursor = dummyHead.getLink();
	}
}
