package elice;

import java.util.LinkedList;

public class RadixSort {
	//Do not change the name
	LinkedList<Integer> list0, list1;
	final int MAX_ITERATIONS=31;
	
	public RadixSort(){
		//Implement by Student
		//Initialize two lists.
		list0 = new LinkedList<Integer>();
		list1 = new LinkedList<Integer>();
	}
	public void radixSort(int[] data){

		//Implement by Student
		//Please follow the specification of Programming Project 9 in page 701
		int divisor = 1;
		for(int i = 0; i < MAX_ITERATIONS; ++i){
			for(int j =0 ; j<data.length; j++){
				if((data[j]/divisor)%2 == 0){
					list0.add(data[j]);
				}
				else{
					list1.add(data[j]);
				}
			}
			
			divisor *= 2;
			
			int k = 0;
			while(!list0.isEmpty()){
				data[k] = list0.remove();
				k++;
			}
			while(!list1.isEmpty()){
				data[k] = list1.remove();
				k++;
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
