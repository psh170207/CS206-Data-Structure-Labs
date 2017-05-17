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
	
    public void add(E nodedata, int priority)
    {
        // A student must implement this method.
		int index = manyItems;
		data[index] = nodedata;
		priorities[index] = priority;
		entered[index] = manyItems;
		
		while(priorities[index]>priorities[(index-1)/2]){
			int temp1 = priorities[index];
			priorities[index] = priorities[(index-1)/2];
			priorities[(index-1)/2] = temp1; // swap priority
			
			E temp2 = data[index];
			data[index] = data[(index-1)/2];
			data[(index-1)/2] = temp2; // swap data
			
			int temp3 = entered[index];
			entered[index] = entered[(index-1)/2];
			entered[(index-1)/2] = temp3; // swap entered
			
			index = (index-1)/2; //update index
		}
		manyItems++;
    }

    public E remove()
    {
        // A student must implement this method.
		if(manyItems==0) return null;
		
		int i = 0;
		E answer = data[i];
		int rmvidx = 0;
		
        while(priorities[i]==priorities[i+1] && i<manyItems){
			if(entered[i+1]<entered[rmvidx]) rmvidx=i+1;
			i=i+1;
		} //while prorities are not equal, updata rmvidx to smallest entered value
		
		//swap root and rmvidx
		swap(0,rmvidx);
		
		answer = data[0];
		
		data[0] = data[manyItems-1];//맨 마지막 element를 root로 복사.
		data[manyItems-1] = null;
		manyItems--;//맨 마지막 element 삭제.
		
		int index = 0;
		boolean hasLChild = false;
		
		while(priorities[index]<priorities[leftchild(index)] || priorities[index]<priorities[rightchild(index)]){
			if(priorities[leftchild(index)]>priorities[rightchild(index)]){// swap with larger one
				swap(index,leftchild(index));
				index = leftchild(index); //update index
			}
			else if(priorities[leftchild(index)]<priorities[rightchild(index)]){
				swap(index,rightchild(index));
				index = rightchild(index); //update index
			}
			
			if(leftchild(index)>=manyItems) break; // there's no child
			else if(rightchild(index)>=manyItems){ // there's only leftchild
				hasLChild = true;
				break;
			}
		}
		
		if(hasLChild){
			if(priorities[index]<priorities[leftchild(index)]){
				swap(index,leftchild(index));
			}
		}
		
		return answer;
    }
	
	private void swap(int idx1, int idx2){
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
	
	private int leftchild(int index){
		return 2*index+1;
	}
	
	private int rightchild(int index){
		return 2*index+2;
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
