package elice;

public class HeapPQ<E>
{
    /*
     *  root node should always be root of the tree. 
     */ 
    private int[] priorities; // DO NOT CHANGE PRAMETER NAME
    private E[] data;
    private int[] entered;
	private int size = 100;
	private int manyItems = 0;
	
    public HeapPQ()
    {
        // A student must implement this method.
		priorities = new int[size];
		data = (E[]) new Object[size];
		entered = new int[size];
    }
	
	public void add(E element, int priority)
    {
		if(size==manyItems){
			ensureCapacity(2*size);
		} // if there's no available space, expend capacity
		
		int index = manyItems;
		data[index] = element;
		priorities[index] = priority;
		entered[index] = manyItems;
		// since, heap is a complete binary tree, every new element add at end of entry
		
		reheapification_up(index);
		manyItems++;
    }
	
	private void ensureCapacity(int new_size){
		int[] new_priorities = new int[new_size];
		E[] new_data = (E[]) new Object[new_size];
		int[] new_entered = new int[new_size];
		
		System.arraycopy(priorities,0,new_priorities,0,manyItems);
		System.arraycopy(data,0,new_data,0,manyItems);
		System.arraycopy(entered,0,new_entered,0,manyItems);
		
		priorities = new_priorities;
		data = new_data;
		entered = new_entered;
	}

	private void reheapification_up(int index)
	{
		while(priorities[index]>priorities[(index-1)/2]) // while child's priority > parent's prioirity
		{
			swap(index,(index-1)/2);
			index = (index-1)/2; //update index
		}
	}

    public E remove()
    {
        // A student must implement this method.
		if(manyItems==0) return null;
		
		int i = 0;
		int rmvidx = 0;
		
        while(priorities[i]==priorities[i+1] && i<manyItems-1)
		{
			if(entered[i+1]<entered[rmvidx])
				rmvidx=i+1;
			i=i+1;
		} //while prorities are not equal, updata rmvidx to smallest entered value
		
		//swap root and rmvidx
		swap(0,rmvidx);
		
		E answer = data[0];
		priorities[0] = priorities[manyItems-1];//맨 마지막 priority를 root로 복사.
		data[0] = data[manyItems-1];
		entered[0] = entered[manyItems-1];
		manyItems--;//맨 마지막 priority 삭제.
		
		reheapification_down(0);
		return answer;
    }
	
	private void swap(int idx1, int idx2)
	{
		int temp1 = priorities[idx1];
		priorities[idx1] = priorities[idx2];
		priorities[idx2] = temp1; // swap priority
		
		E temp2 = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp2; // swap data
		
		int temp3 = entered[idx1];
		entered[idx1] = entered[idx2];
		entered[idx2] = temp3; // swap data
	}
	
	private int leftchild(int index)
	{
		return 2*index+1;
	}
	
	private int rightchild(int index)
	{
		return 2*index+2;
	}
	
	private void reheapification_down(int index)
	{
		boolean hasLChild = false;
		
		while(priorities[index]<priorities[leftchild(index)] || priorities[index]<priorities[rightchild(index)]) // while parent's priority < children's priority
		{
			if(priorities[leftchild(index)]>priorities[rightchild(index)]) // swap with larger one
			{
				swap(index,leftchild(index));
				index = leftchild(index); //update index
			}
			else
			{
				swap(index,rightchild(index));
				index = rightchild(index); //update index
			}
			
			if(leftchild(index)>=manyItems)
				break; // there's no child
			else if(rightchild(index)>=manyItems)
			{ // there's only leftchild
				hasLChild = true;
				break;
			}
		}
		
		if(hasLChild)
		{
			if(priorities[index]<priorities[leftchild(index)])
			{
				swap(index,leftchild(index));
			}
		}
	}

    public int[] getpriorities()
    {
        // Do NOT MODIFY THIS METHOD.
        // THE TEST USES THIS METHOD.
        return priorities;
    }

    public E[] getData()
    {
        // Do NOT MODIFY THIS METHOD.
        // THE TEST USES THIS METHOD.
        return data;
    }

    public int[] getEntered()
    {
        // Do NOT MODIFY THIS METHOD.
        // THE TEST USES THIS METHOD.
        return entered;
    }
}
