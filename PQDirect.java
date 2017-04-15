package elice;
import java.util.NoSuchElementException;
public class PQDirect<E>
{
	public Node head, tail, cursor;  // Do not erase this
	public int manyNodes;  // Do not erase this
	public int highest; // Do not erase this
	private Node DummyHead, DummyTail;
	E tmp;
	public PQDirect(int highest_priority)
	{
		if(highest_priority<0) throw new IllegalArgumentException("Priority must be larger or equal than 0!");
		DummyHead = new Node(-1,null,null,null);//Dummyhead.
		DummyTail = new Node(-1,null,null,null);//Dummytail.
		DummyHead.setLink(DummyTail);
		DummyTail.setPrvlink(DummyHead);
		head = DummyHead;
		tail = DummyTail;
		manyNodes=0;
		highest = highest_priority;
		cursor=head;
	}

	public void add(E item, int priority)
	{
		if(priority<0 || priority>highest) throw new IllegalArgumentException("Priority must be larger or equal than 0!");
		try{
			Node tmp = new Node(priority, item, tail, tail.getPrvlink());
			tail.getPrvlink().setLink(tmp);
			tail.setPrvlink(tmp);
			cursor = tmp;
			manyNodes++;
		}
		catch(OutOfMemoryError e){
			throw new OutOfMemoryError("Insufficient memory for adding a new item to this PQ.");
		}
	}

	public E remove()
	{
		if(manyNodes==0) throw new NoSuchElementException("There's no element in PQ!");
		Node answer = null;
		try{
			int hp = FindPriority();//hp is the highest priority in this PQ
			Node tmp;
			for(tmp=head;tmp!=null;tmp=tmp.getLink()){
				if(tmp.getPriority()==hp){
					answer = tmp;
					tmp.getPrvlink().setLink(tmp.getLink());
					tmp.getLink().setPrvlink(tmp.getPrvlink());
					manyNodes--;
					break;
				}
			}
		}
		catch(OutOfMemoryError e){
			throw new OutOfMemoryError("Insufficient memory for adding a new item to this PQ.");
		}
		return (E) answer.getData();
	}
	
	//helper method.
	private int FindPriority()
	{
		Node tmp;
		int hp = 0;//highest priority in this PQ.
		for(tmp = head;tmp!=null;tmp=tmp.getLink()){
			if(tmp.getPriority()>hp) hp = tmp.getPriority();
		}
		return hp;
	}
}
