package elice;

import java.util.Stack;

public class IterativeQuickSort {
	//Do not change the name
	public Stack<Integer> qsStack;
	
	public IterativeQuickSort(){
		//Implement by Student
		//Initialize the Stack
		qsStack = new Stack<Integer>();
	}
 
    private static int partition (int[] data, int first, int n)
    {
    	//Implement by Student
    	//Precondition: n>1, and data has at least n elements starting at data[first].
    	//Postcondition: The method has selected some "pivot value" that occurs in data[first]...data[first+n-1].
    	//The elements of data have then been rearranged and the method returns a pivot index so that
    	// --data[pivot index] is equal to the pivot;
    	// --each element before data[pivot index] is <= the pivot;
    	// --each element after data[pivot index] is > the pivot.
    	
    	// Page 674 of textbook will be a help to you.
    	/* Initial values */
		if(n<=1) throw new IllegalArgumentException();
		for(int i = first; i<first+n; i++){
			if(data[i]==0) throw new IllegalArgumentException();
		}
		
		int pivot = data[first];
		int tooBigIndex = first + 1;
		int tooSmallIndex = first + n - 1;
		
		while(tooBigIndex<=tooSmallIndex){
		
			while(tooBigIndex<=tooSmallIndex && data[tooBigIndex]<=pivot){
				tooBigIndex++;
			}
			
			while(tooBigIndex<=tooSmallIndex && data[tooSmallIndex]>pivot){
				tooSmallIndex--;
			}
			
			if(tooBigIndex<tooSmallIndex){
				int temp = data[tooBigIndex];
				data[tooBigIndex] = data[tooSmallIndex];
				data[tooSmallIndex] = temp;
				
				tooBigIndex++;
				tooSmallIndex--;
			}
		}
		data[first] = data[tooSmallIndex];
		data[tooSmallIndex] = pivot;
		return tooSmallIndex;
    }

	public void QuickSort(int[] data, int first, int n)
    {
		//Implement by Student
		//DO NOT implement QuickSort recursively.
		//Please follow the specification of Programming Project 4 in page 699.
		if(n<=1) throw new IllegalArgumentException();
		for(int i = first; i<first+n; i++){
			if(data[i]==0) throw new IllegalArgumentException();
		}
		
		qsStack.push(first);
		qsStack.push(n);
		
		int sizen;
		int i;
		int pivotIndex;
		
		while(!qsStack.isEmpty()){
			sizen = qsStack.pop();
			i = qsStack.pop();
			pivotIndex = partition(data,i,sizen);
			
			if(pivotIndex-i>1){
				qsStack.push(i);
				qsStack.push(pivotIndex-i);
			}
			
			if(i-1+sizen-pivotIndex>1){
				qsStack.push(pivotIndex+1);
				qsStack.push(sizen-pivotIndex+i-1);
			}
		}
    }
	
	//Print method for your help
	public void print( int[] data, int n )
    {
        int i;
        for ( i = 0; i < n; ++i )
            System.out.print(data[i]+" ");
    }

}
