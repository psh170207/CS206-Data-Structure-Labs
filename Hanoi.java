package elice;

public class Hanoi {

	public IntegerLinkedStack[] towers = new IntegerLinkedStack[3];
	public int height = 0;
	public String log = "";

	public Hanoi(int height){
        towers[0] = new IntegerLinkedStack(0);
        towers[1] = new IntegerLinkedStack(1);
        towers[2] = new IntegerLinkedStack(2);
		if(height>0) for(int i = height ; i>0 ; i--) towers[0].push(i);
		//else throw new IllegalArgumentException("height must be non-negative integer");
	}
 
	public void moveTower(){
		moveTower(towers[0].size(),0,2,1);
	}

	public void moveTower(int disk, int source, int dest, int spare){
		log += disk + "," + source + "," + dest + "," + spare + "\n"; // DO NOT MODIFY THIS FIRST LINE
		if(disk==1) towers[dest].push(towers[source].pop());
		else{
			moveTower(disk-1,source,spare,dest);
			towers[dest].push(towers[source].pop());
			moveTower(disk-1,spare,dest,source);
		}
	}

	// DO NOT EDIT toString METHOD
	public String toString(){
		return "0: " + towers[0] + "\n1: " + towers[1] + "\n2: " + towers[2] + "\n";
	}
}
