package elice;


public class IntBalancedSet implements Cloneable
{
   // Invariant of the IntBalancedSet class:
   //   1. The elements of the Set are stored in a B-tree, satisfying the six
   //      B-tree rules.
   //   2. The number of elements in the tree's root is in the instance
   //      variable dataCount, and the number of subtrees of the root is stored
   //      stored in the instance variable childCount.
   //   3. The root's elements are stored in data[0] through data[dataCount-1].
   //   4. If the root has subtrees, then subtree[0] through 
   //      subtree[childCount-1] are references to these subtrees.
   private final int MINIMUM = 1; // DO NOT CHANGE
   private final int MAXIMUM = 2*MINIMUM; // DO NOT CHANGE
   int dataCount;
   int[ ] data = new int[MAXIMUM + 1];
   int childCount;
   IntBalancedSet[ ] subset = new IntBalancedSet[MAXIMUM + 2];


   /**
   * Initialize an empty set.
   * <b>Postcondition:</b>
   *   This set is empty.
   **/
   public IntBalancedSet( )
   {
      dataCount = 0;
      childCount = 0;
   }


   /**
   * Add a new element to this set.
   * @param  element
   *   the new element that is being added
   * <b>Postcondition:</b>
   *   If the element was already in this set, then there is no change.
   *   Otherwise, the element has been added to this set.
   **/
   public void add(int element)
   {
      // Implemented by student.
	  looseAdd(element);
	  if(dataCount>MAXIMUM){
	  	//make child for new root
	  	IntBalancedSet child = new IntBalancedSet();
		System.arraycopy(data,0,child.data,0,dataCount);
		System.arraycopy(subset,0,child.subset,0,childCount);
		child.dataCount = dataCount;
		child.childCount = childCount;
		//new root's info
		dataCount = 0;
		childCount = 1;
		subset[0] = child;
		fixExcess(0);
	  }
   }
   

   /**
   * Generate a copy of this set.
   * @return
   *   The return value is a copy of this set. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>IntBalancedSet</CODE> before it 
   *   can be used.
   **/ 
   public Object clone( )
   {  // Clone a IntBalancedSet object.
      // Student will replace this return statement with their own code:
      IntBalancedSet answer = new IntBalancedSet();
	  try{
	  	answer = (IntBalancedSet) super.clone();
	  }
	  catch(CloneNotSupportedException e){	
	  	throw new RuntimeException("This class does not implement Cloneable.");
	  }
	  answer.data = data.clone();
	  answer.dataCount = dataCount;
	  answer.childCount = childCount;
	  answer.subset = subset.clone();
	  return answer;
   }


   /**
   * Accessor method to determine whether a particular element is in this set.
   * @param target
   *   an element that may or may not be in this set
   * @return
   *   <CODE>true</CODE> if this set contains <CODE>target</CODE>;
   *   otherwise <CODE>false</CODE>
   **/
   public boolean contains(int target)
   {
      // Student will replace this return statement with their own code:
      int i = firstGE(target);
	  if(data[i]==target) return true;
	  else if(childCount == 0) return false;
	  else return subset[i].contains(target);
   }


   /**
   * Remove a specified element from this set.
   * @param target
   *   the element to remove from this set
   * @return
   *   if <CODE>target</CODE> was found in this set, then it has been removed
   *   and the method returns <CODE>true</CODE>. Otherwise this set remains
   *   unchanged and the method returns <CODE>false</CODE>.
   **/
   public boolean remove(int target)
   {
      // Student will replace this return statement with their own code:
      boolean answer = looseRemove(target);
	  if(dataCount==0 && childCount==1){
	  	data = subset[0].data;
		subset = subset[0].subset;
		dataCount = subset[0].dataCount;
		childCount = subset[0].childCount;
	  }
	  return answer;
   }


   public void print(int indent)
   // Print a representation of this set's B-tree, useful during debugging.
   {
      final int EXTRA_INDENTATION = 4;
      int i;
      int space;
  
      // Print the indentation and the data from this node
      for (space = 0; space < indent; space++)
         System.out.print(" ");
      for (i = 0; i < dataCount; i++)
         System.out.print(data[i] + " ");
      System.out.println( );
         
      // Print the subtrees
      for (i = 0; i < childCount; i++)
         subset[i].print(indent + EXTRA_INDENTATION);
   }
       
       
   // PRIVATE HELPER METHODS 
   // The helper methods are below with precondition/postcondition contracts.
   // Students should implement these methods to help with the other methods.

   private int deleteData(int removeIndex)
   // Precondition: 0 <= removeIndex < dataCount.
   // Postcondition: The element at data[removeIndex] has been removed and
   // subsequent elements shifted over to close the gap. Also, dataCount has
   // been decremented by one, and the return value is a copy of the
   // removed element.
   {
      // Student will replace this return statement with their own code:
	  int deleted = data[removeIndex];
      for(int i=removeIndex;i<dataCount;i++){
	  	data[i]=data[i+1];
	  }
	  dataCount--;
	  return deleted;
   }
   
   
   private IntBalancedSet deleteSubset(int removeIndex)
   // Precondition: 0 <= removeIndex < childCount.
   // Postcondition: The element at subset[removeIndex] has been removed and
   // subsequent elements shifted over to close the gap. Also, childCount has
   // been decremented by one, and the return value is a copy of the
   // removed element.
   {
      // Student will replace this return statement with their own code:
      IntBalancedSet deleted = subset[removeIndex];
	  for(int i=removeIndex;i<childCount;i++){
	  	subset[i]=subset[i+1];
	  }
	  childCount--;
	  return deleted;
   }
   
   private int firstGE(int target)
   // Postcondition: The return value, x, is the first location in the root
   // such that data[x] >= target. If there is no such location, then the
   // return value is dataCount.
   {
      // Student will replace this return statement with their own code:
	  int index = dataCount;
      for(int i = 0; i<dataCount; i++)
	  	if(data[i]>=target){
			index = i;
			break;
		}
	  return index;
   }
   
   
   private void fixExcess(int i)
   // Precondition: 
   //   (i < childCount) and the entire B-tree is valid EXCEPT that
   //   subset[i] has MAXIMUM + 1 entries. Also, the root is allowed to have
   //   zero entries and one child.
   // Postcondition: 
   //   The tree has been rearranged so that the entire B-tree is valid EXCEPT
   //   that the number of entries in the root of this set might be one more than
   //   the allowed maximum.
   {
      // Implemented by student.
	  int middle = subset[i].data[MINIMUM];
	  insertData(i,middle); // insert element to root
	  IntBalancedSet set1 = new IntBalancedSet(); // splited set1
	  IntBalancedSet set2 = new IntBalancedSet(); // splited set2
	  System.arraycopy(subset[i].data,0,set1.data,0,MINIMUM); // subset[i].data[0~MINIMUM-1]
	  //remove subset[i].data[MINIMUM]
	  System.arraycopy(subset[i].data,MINIMUM+1,set2.data,0,MINIMUM); //subset[i].data[MINIMUM+1~2*MINIMUM-1]
	  set1.dataCount=MINIMUM;
	  set2.dataCount=MINIMUM;
	  
	  if(subset[i].childCount!=0){
	  	int div = subset[i].childCount/2;
		for(int j=0;j<div;j++){
			set1.subset[j] = subset[i].subset[j];
			set2.subset[j] = subset[i].subset[j+div];
		}
		set1.childCount = MINIMUM+1; //subset[i]'s childCount is must be MAXIMUM+2(=(MAXIMUM+1)+1) = 2*MINIMUM+2
		set2.childCount = MINIMUM+1;
	  }
	  for(int k=childCount;k>i;k--){
	  	subset[k]=subset[k-1]; //make room for set1 and set2
	  }
	  subset[i]=set1;
	  subset[i+1]=set2;
	  childCount++;
   }

   private void fixShortage(int i)
   // Precondition: 
   //   (i < childCount) and the entire B-tree is valid EXCEPT that
   //   subset[i] has only MINIMUM - 1 entries.
   // Postcondition: 
   //   The tree has been rearranged so that the entire B-tree is valid EXCEPT
   //   that the number of entries in the root of this set might be one less than
   //   the allowed minimum.
   {
      // Implemented by student.
	  if(i>0 && subset[i-1].dataCount>MINIMUM) transferLeft(i);
	  else if(i<dataCount-1 && subset[i+1].dataCount>MINIMUM) transferRight(i);
	  else if(i>0 && subset[i-1].dataCount==MINIMUM) mergeWithNextSubset(i-1);
	  else if(i<dataCount-1 && subset[i+1].dataCount==MINIMUM) mergeWithNextSubset(i);
   }


   private void insertData(int insertIndex, int entry)
   // Precondition: 0 <= insertIndex <= dataCount <= MAXIMUM.
   // Postcondition: The entry has been inserted at data[insertIndex] with
   // subsequent elements shifted right to make room. Also, dataCount has
   // been incremented by one.
   {
      // Implemented by student.
	  for(int i=dataCount;i>insertIndex;i--){
	  	data[i]=data[i-1];
	  }
	  data[insertIndex] = entry;
	  dataCount++;
   }
   
   
   private void insertSubset(int insertIndex, IntBalancedSet set)
   // Precondition: 0 <= insertIndex <= childCount <= MAXIMUM+1.
   // Postcondition: The set has been inserted at subset[insertIndex] with
   // subsequent elements shifted right to make room. Also, childCount has
   // been incremented by one.
   {
      // Implemented by student.
	  for(int i=childCount;i>insertIndex;i--){
	  	subset[i]=subset[i-1];
	  }
	  subset[insertIndex] = set;
	  childCount++;
   }
   
   
   private boolean isLeaf( )
   // Return value is true if and only if the B-tree has only a root.
   {
      return (childCount == 0);
   }
   
   
   private void looseAdd(int entry)
   // Precondition:
   //   The entire B-tree is valid.
   // Postcondition:
   //   If entry was already in the set, then the set is unchanged. Otherwise,
   //   entry has been added to the set, and the entire B-tree is still valid
   //   EXCEPT that the number of entries in the root of this set might be one
   //   more than the allowed maximum.
   {
      // Implemented by student.
	  int i = firstGE(entry); // find first index s.t. data[i]>=entry
	  if(data[i]==entry) return; // if there are element that same as entry, do noting and return
	  else if(childCount==0){ // if there are no child, data[i]<-entry and others shifted right to make room
	  	for(int j=dataCount;j>i;j--){
			data[j] = data[j-1];
		}
		data[i] = entry;
		dataCount++;
	  }
	  else{
	  	subset[i].looseAdd(entry); 
		if(subset[i].dataCount > MAXIMUM) fixExcess(i); // if the root of subset[i] new has an excess element, then fix that problem before returning
	  }
   }


   private boolean looseRemove(int target) //OK
   // Precondition:
   //   The entire B-tree is valid.
   // Postcondition:
   //   If target was in the set, then it has been removed from the set and the
   //   method returns true; otherwise the set is unchanged and the method 
   //   returns false. The entire B-tree is still valid EXCEPT that the
   //   number of entries in the root of this set might be one less than the
   //   allowed minimum.
   {
      // Student will replace this return statement with their own code:
      int i = firstGE(target);
	  if(childCount==0){ // root has no children
	  	if(i==dataCount) return false; //target not found
		else{ // target found
			deleteData(i);
			dataCount--;
			return true;
		}
	  }
	  else{ //root has children
	  	if(i==dataCount){ // target not found
	  		boolean answer = subset[i].looseRemove(target);
			if(answer && subset[i].dataCount < MINIMUM) fixShortage(i);
			return answer;
		}
		else{ // target found
			data[i] = subset[i].removeBiggest();
			if(subset[i].dataCount < MINIMUM) fixShortage(i);
			return true;
		}
	  }
   }


   private void mergeWithNextSubset(int i)
   // Precondition: 
   //   (i+1 < childCount) and the entire B-tree is valid EXCEPT that the total
   //   number of entries in subset[i] and subset[i+1] is 2*MINIMUM - 1.
   // Postcondition: 
   //   subset[i] and subset[i+1] have been merged into one subset (now at
   //   subset[i]), and data[i] has been passed down to be the median entry of the
   //   new subset[i]. As a result, the entire B-tree is valid EXCEPT that the
   //   number of entries in the root of this set might be one less than the
   //   allowed minimum.
   {
      // Transfer data[i] down to the end of subset[i].data. This actually removes the element from the root, so shift data[i+1],data[i+2], and so on, leftward to fill in the gap. Also remember to subtract 1 from dataCount and add 1 to subset[i].dataCount.
	subset[i].insertData(subset[i].dataCount,data[i]);
	deleteData(i);
	
	// Transfer all the elements and children from subset[i+1] to the end of subset[i]. Remember to update the values of subset[i].dataCount and subset[i].childCount.
	for(int j=0;j<subset[i+1].childCount;j++){
		subset[i].insertSubset(subset[i].childCount+j,subset[i+1].subset[j]);
	}
	for(int j=0;j<subset[i+1].dataCount;j++){
		subset[i].insertData(subset[i].dataCount+j,subset[i+1].data[j]);
	}

	//Disconnect the node subset[i+1] from the B-tree by shifting subset[i+2], subset[i+3], and so on, leftward. Also reduce childCount by 1.
	deleteSubset(i+1);
   }
   
   private int removeBiggest( ) //OK
   // Precondition: 
   //   (dataCount > 0) and the entire B-tree is valid.
   // Postcondition:
   //   The largest item in the set has been removed, and the value of this
   //   item is the return value. The B-tree is still valid EXCEPT
   //   that the number of entries in the root of this set might be one less than
   //   the allowed minimum.
   {
      // Student will replace this return statement with their own code:
	  if(childCount==0){
		  int answer = deleteData(dataCount-1);
		  return answer;
	  }
	  else{
		  int answer = subset[childCount-1].removeBiggest();
		  if(subset[childCount-1].dataCount<MINIMUM) fixShortage(childCount-1);
		  return answer;
	  }
   }

    
   public void transferLeft(int i)
   // Precondition: 
   //   (0 < i < childCount) and (subset[i]->dataCount > MINIMUM)
   //   and the entire B-tree is valid EXCEPT that
   //   subset[i-1] has only MINIMUM - 1 entries.
   // Postcondition:
   //   One entry has been shifted from the front of subset[i] up to
   //   data[i-1], and the original data[i-1] has been shifted down to the last
   //   entry of subset[i-1]. Also, if subset[i] is not a leaf, then its first
   //   subset has been transfered over to be the last subset of subset[i-1].
   //   As a result, the entire B-tree is now valid.
   {
 	  // Transfer data[i-1] down to the front of subset[i].data. Remember to shift over the existing elements to make room and add one to subset[i].dataCount
	  subset[i].insertData(0,data[i-1]);
	  
	  // Transfer the final element of subset[i-1].data up to replace data[i-1] and subtract one from subset[i-1].dataCount
	  data[i-1] = subset[i-1].data[subset[i-1].dataCount-1];
	  subset[i-1].deleteData(subset[i-1].dataCount-1);
	  // If subset[i-1] has children, transfer the final child of subset[i-1] over to the front of subset[i]. This involves shifting over the existing array subset[i].subset to make room for the new child at subset[i].subset[0]. Also add 1o to subset[i].childCount and subtract 1 from subset[i-1].childCount
	  if(subset[i-1].childCount!=0){
		subset[i].insertSubset(0,subset[i-1].subset[subset[i-1].childCount-1]);
		subset[i-1].deleteSubset(subset[i-1].childCount-1);
	  }
   }
   

   public void transferRight(int i)
   // Precondition: 
   //   (i+1 < childCount) and (subset[i]->dataCount > MINIMUM)
   //   and the entire B-tree is valid EXCEPT that
   //   subset[i] has only MINIMUM - 1 entries.
   // Postcondition: One entry has been shifted from the end of subset[i] up to
   //   data[i], and the original data[i] has been shifted down to the first entry
   //   of subset[i+1]. Also, if subset[i] is not a leaf, then its last subset has
   //   been transfered over to be the first subset of subset[i+1].
   //   As a result, the entire B-tree is now valid.
   {
	  // Transfer data[i] down to the last of subset[i].data. Remember to shift over the existing elements to make room and add one to subset[i].dataCount
	  subset[i].insertData(subset[i].dataCount,data[i]);
	  
	  // Transfer the first element of subset[i+1].data up to replace data[i] and subtract one from subset[i+1].dataCount
	  data[i] = subset[i+1].data[0];
	  subset[i+1].deleteData(0);
	  
	  // If subset[i+1] has children, transfer the first child of subset[i+1] over to the final of subset[i]. There's no shifting over the existing array subset[i].subset. Also add 1o to subset[i].childCount and subtract 1 from subset[i+1].childCount
	  if(subset[i+1].childCount!=0){
		subset[i].insertSubset(subset[i].childCount,subset[i+1].subset[0]);
		subset[i+1].deleteSubset(0);
	  }
   }
}
