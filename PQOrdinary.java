package elice;
import java.util.NoSuchElementException;
public class PQOrdinary<E>
{
	private ArrayQueue<E>[] queues; // DO NOT ERASE THIS!
	
	public ArrayQueue<E>[] getQueues()
	{
		/*
			DO NOT CHANGE THIS METHOD!
		*/
		return queues;
	}

	public PQOrdinary(int highest)
	{
		if(highest<0) throw new IllegalArgumentException("The highest priority must be larger or equal than 0!");
		Object[] temp = new Object[highest+1];
		for(int i=0;i<=highest;i++){
			temp[i] = (E) temp[i];
		}
		queues = (ArrayQueue<E>[]) temp;
	}

	public void add(E item, int priority)
	{
		if(priority<0 || priority>=queues.length) throw new IllegalArgumentException("The highest priority must be larger or equal than 0!");
		queues[priority].add(item);
	}

	public E remove()
	{
		int highest = queues.length-1;
		while(queues[highest].isEmpty()&&highest>=0) highest--; // find highest priority with non empty ordinary queue.
		if(highest<0) throw new NoSuchElementException("There's no element in this PQ!");
		return queues[highest].remove();
	}
}
