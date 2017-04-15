package elice;
import java.util.NoSuchElementException;
public class Deque<E> extends ArrayQueue<E>{

	/* Do not make the data, manyItem, front and rear for the Deque class.
	 * Use the variable of ArrayQueue using 'super' keyword
	*/
	  E tmp; // This variable is used to compile the only initial skeleton code.
	  public Deque()
	  {
		  super();
	  }
	  
	  
	  public Deque(int initialCapacity)
	  {
		  super(initialCapacity);
	  }
	  
	  
	  public void addFirst(E element)
	  {
		if(manyItems==data.length){
			if(manyItems*2+1>Integer.MAX_VALUE) throw new IllegalStateException("There's arithmetic overflow!");
			else ensureCapacityWithBlank();
		}
		if(manyItems==0){
			front = 0;
			rear = 0;
			data[front] = element;
		}
		else data[front-1] = element;
		manyItems++;
	  }
	  
	  
	  public void addLast(E element)
	  {
		if(manyItems==data.length) ensureCapacity();
		if(manyItems==0){
			front=0;
			rear=0;
		}
		else rear = nextIndex(rear);
		data[rear] = element;
		manyItems;
	  }
	  
	  
	  public E removeFirst()
	  {
		  return tmp;
	  }
	  
	  
	  public E removeLast()
	  {
		  return tmp;
	  }
	  
	  //helper methods.
	  private int nextIndex(int i){
	  	if(i==data.length-1) return 0;
		else return i+1;
	  }
	  
	  private void ensureCapacityWithBlank(int minimumCapacity){
	  	Object[] biggerArray;
		int n1,n2;
		
		if(data.length >= minimumCapacity){
			if(front<=rear){
				if(front!=0) return;
				else{ // front == 0.
					biggerArray = new Object[data.length+1];
					System.arraycopy(data,0,biggerArray,1,manyItems); // biggerArray[0] = blank.
					front = 1;
					rear = manyItems;
					data = (E[]) biggerArray;
				}
			}
			else{//front > rear.
				biggerArray = new Object[data.length+1];
				n1 = data.length - front;
				n2 = rear+1;
				System.arraycopy(data,front,biggerArray,1,n1); // biggerArray[0] = blank.
				System.arraycopy(data,0,biggerArray,n1+1,n2);
				front = 1;
				rear = manyItems;
				data = (E[]) biggerArray;
			}
		}
		else if(manyItems==0){
			data = (E[]) new Object[minimumCapacity];
		}
		else if(front <= rear){
			if(front!=0){
				biggerArray = new Object[minimumCapacity];
				System.arraycopy(data,front,biggerArray,front,manyItems);
				data = (E[]) biggerArray;
			}
			else{//front == 0
				biggerArray = new Object[minimumCapacity];
				System.arraycopy(data,0,biggerArray,1,manyItems);
				front = 1;
				rear = manyItems;
				data = (E[]) biggerArray;
			}
		}
		else{ // rear < front.data[front] ~ data[data.length-1] + data[0] ~ data[rear];
			biggerArray = new Object[minimumCapacity];
			n1 = data.length - front;
			n2 = rear +1;
			System.arraycopy(data,front,biggerArray,1,n1); // make blank in biggerArray[0] so that addFirst().
			System.arraycopy(data,0,biggerArray,n1+1,n2);
			front = 0;
			rear = manyItems-1;
			data = (E[]) biggerArray;
		}
	  }


}
