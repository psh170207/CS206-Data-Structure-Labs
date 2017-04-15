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
			ensureCapacityWithBlank(manyItems*2+1);
			data[front-1] = element;
		}
		if(manyItems==0){
			front = 0;
			rear = 0;
			data[front] = element;
		}
		else{ //manyItems < data.length
			if(front<=rear){
				if(front!=0){
					front--;
					data[front] = element;
				}
				else{// front == 0.
					E[] tmp = (E[]) new Object[data.length];
					System.arraycopy(data,front,tmp,1,manyItems);
					front = 0;
					rear = manyItems;
					data = tmp;
					data[front] = element;
				}
			}
			else{ // front > rear.
				front--;
				data[front] = element;
			}
		}
		manyItems++;
	  }
	  
	  
	  public void addLast(E element)
	  {
		if(manyItems==data.length) ensureCapacity(manyItems*2+1);
		if(manyItems==0){
			front=0;
			rear=0;
		}
		else rear = nextIndex(rear);
		data[rear] = element;
		manyItems++;
	  }
	  
	  
	  public E removeFirst()
	  {
	  	E answer;
		if(manyItems == 0) throw new NoSuchElementException("There's no element!");
		answer = data[front];
		front = nextIndex(front);
		manyItems--;
		return answer;
	  }
	  
	  
	  public E removeLast()
	  {
		E answer;
		if(manyItems==0) throw new NoSuchElementException("There's no element!");
		answer = data[rear];
		rear = prvIndex(rear);
		manyItems--;
		return answer;
	  }
	  
	  //helper methods.
	  private int prvIndex(int i){
	  	if(i==0) return data.length-1;
		else return i-1;
	  }
	  
	  private void ensureCapacityWithBlank(int minimumCapacity){
	  	Object[] biggerArray;
		int n1,n2;
		
		if(data.length >= minimumCapacity) return;
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
			front = 1;
			rear = manyItems;
			data = (E[]) biggerArray;
		}
	  }
}
