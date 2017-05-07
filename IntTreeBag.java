// File: IntTreeBag.java from the package edu.colorado.collections

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"

// Check with your instructor to see whether you should put this class in
// a package. At the moment, it is declared as part of edu.colorado.collections:
package elice;

import java.util.Iterator;

/******************************************************************************
* This class is a homework assignment;
* An <CODE>IntTreeBag</CODE> is a collection of int numbers.
*
* <b>Limitations:</b> 
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <b>Outline of Java Source Code for this class:</b>
*   <A HREF="../../../../edu/colorado/collections/IntTreeBag.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/IntTreeBag.java
*   </A>
*
* <b>Note:</b>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version Feb 10, 2016
*
* @see IntArrayBag
* @see IntLinkedBag
******************************************************************************/
public class IntTreeBag implements Cloneable
{
   // Invariant of the IntTreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private IntBTNode root;   

   /**
   *
   *  DO NOT CHANGE THIS METHOD.
   *  TEST CODE USES THIS CODE FOR TEST.
   *
   **/
   public IntBTNode getRoot()
   {
      return root;
   }

   /**
   * Insert a new element into this bag.
   * @param element
   *   the new element that is being inserted
   * <b>Postcondition:</b>
   *   A new copy of the element has been added to this bag.
   * 
   **/
   public void add(int element)
   {      
      // Implemented by student.
	  if(size()==0){
	  	root = new IntBTNode(element,null,null);
	  }
	  else{
	  	boolean done = false;
	  	IntBTNode cursor = getRoot();
		while(!done){
			if(element<=cursor.getData()){
				if(cursor.getLeft()!=null){
					cursor = cursor.getLeft();
				}
				else{
					done = true;
					IntBTNode newLeft = new IntBTNode(element,null,null);
					cursor.setLeft(newLeft);
				}
			}
			else{
				if(cursor.getRight()!=null){
					cursor = cursor.getRight();
				}
				else{
					done = true;
					IntBTNode newRight = new IntBTNode(element,null,null);
					cursor.setRight(newRight);
				}
			}
		}
		
	  }
   }


   public void addMany(int[] elements)
   {
		for(int i = 0;i<elements.length;i++)
			add(elements[i]);
   }

   /**
   * Add the contents of another bag to this bag.
   * @param addend
   *   a bag whose contents will be added to this bag
   * <b>Precondition:</b>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <b>Postcondition:</b>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   *
   **/
   public void addAll(IntTreeBag addend)
   {
      // Implemented by student.
	  IntBTNode addroot=addend.getRoot();
	  
	  if(root==addend.root){
	  	addroot = IntBTNode.treeCopy(addend.root);
		addTree(addroot);
	  }
	  else
	  	addTree(addroot);
   }
   
   /* Helper method for addAll */
   private void addTree(IntBTNode addroot)
   {
		if(addroot != null){
			add(addroot.getData());
			addTree(addroot.getLeft());
			addTree(addroot.getRight());
		}
   }
   
   
   /**
   * Generate a copy of this bag.
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>IntTreeBag</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone( )
   {  // Clone an IntTreeBag object.
      // Student will replace this return statement with their own code:
      IntTreeBag answer;
	  
	  try{
	  	answer = (IntTreeBag) super.clone();
	  }
	  catch(CloneNotSupportedException e){
	  	throw new RuntimeException("This class does not implement Cloneable.");
	  }
	  answer.root = root;
	  return answer;
   }
   

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param target
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(int target)
   {
      // Student will replace this return statement with their own code:
      IntBTNode cursor = getRoot();
	  int count = 0;
	  
	  if(cursor!=null){
	  	while(cursor != null){
			if(target==cursor.getData()){
				count++;
				cursor = cursor.getLeft();
			}
			else if(target<cursor.getData()){
				cursor = cursor.getLeft();
			}
			else{
				cursor = cursor.getRight();
			}
		}
	  }
	  return count;
   }
   
             
   /**
   * Remove one copy of a specified element from this bag.
   * If there are dulplicates remove lower one first.
   * @param target
   *   the element to remove from the bag
   * <b>Postcondition:</b>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(int target)
   {
      // Student will replace this return statement with their own code:
	  IntBTNode cursor = getRoot();
	  IntBTNode parentOfCursor = cursor;
	  boolean ret = true;
	  
	  if(cursor == null) return false;
	  
	  if(cursor.getData()==target){
	  	if(cursor.getLeft()==null){
			root = root.getRight();
			return true;
		}
		else if(cursor.getRight()==null){
			root = root.getLeft();
			return true;
		}
	  }
	  
	  while(cursor!=null){
		if(cursor.getData()==target){
			if(cursor == parentOfCursor.getLeft()){
				if(cursor.getLeft()==null)
					parentOfCursor.setLeft(cursor.getRight());
				else if(cursor.getRight()==null)
					parentOfCursor.setLeft(cursor.getLeft());
				else{
					cursor.setData(cursor.getLeft().getRightmostData());
					cursor.setLeft(cursor.getLeft().removeRightmost());
				}
			}
			else{
				if(cursor.getRight()==null)
					parentOfCursor.setRight(cursor.getLeft());
				else if(cursor.getLeft()==null)
					parentOfCursor.setRight(cursor.getRight());
				else{
					cursor.setData(cursor.getLeft().getRightmostData());
					cursor.setLeft(cursor.getLeft().removeRightmost());
				}
			}
		}
		else if(cursor.getData()<target){
			parentOfCursor = cursor;
			cursor=cursor.getRight();
			if(cursor==null) ret = false;
		}
		else{
			parentOfCursor = cursor;
			cursor = cursor.getLeft();
			if(cursor==null) ret = false;
		}
	  }
	  return ret;
   }
     
   /**
   **Determine the number of elements in this bag.
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return IntBTNode.treeSize(root);
   }
   
   /**
   * Find target's level in the tree bag. Root's level is 0. If there are duplicates, return level of lower one(closer to root).
   * @param target
   *   The target want to find in the bag.
   * 
   * <b>Precondition:</b>
   * @return
   *   The level of target. If there is no target in the bag, return -1
   *
   **/
   public int search(int target)
   {
      return -1;
   }


   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param b1
   *   the first of two bags
   * @param b2
   *   the second of two bags
   * <b>Precondition:</b>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   *
   **/   
   public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2)
   {
      // Student will replace this return statement with their own code:
      return null;
   }


   /**
   * Split the bag into two bags. 
   * @param b1
   *   the bag
   * @param t
   *   the target
   * <b>Precondition:</b>
   *   Neither b1 nor b2 is null.
   * @return
   *   Two bags- All elements in the first bag are smaller than "target" and all elements in the second bag are equal or smaller than "target"
   * @exception IllegalArgumentException
   *   Indicates that the bag is null.
   * 
   **/   
   public static IntTreeBag[] split(IntTreeBag bag, int target)
   {
      return null;
   }

   /**
    * A histogram is a graphical representation of the distribution of numerical data. 
    * getHistogram method return the array of the number of cases in each bin
   * @param bin
   *   range of value, bin is should be larger than zero.
   * @return
   *   Array of the number of cases in each bin.
   *   Length of the array must be smallest. Please see a given document for this homework.
   *   The array must be sorted in ascending order of represented value of bin. 
   * @exception IllegalArgumentException
   *   Indicates that "bin" is equal or smaller than zero
   * 
   **/   

   public int[] getHistogram(int bin){
	   int[] array = null;
	   return array;
   }

	/*
	 * LevelOrderIterator is an external iterator for IntTreeBag which can traverse IntTreeBag in level order (Breadth First Traversal).
	 * You should implement hasNext() and next() methods in this class 
	 */
	private	class LevelOrderIterator implements Iterator<Integer> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override 
		public void remove(){
			
		}
		
	}
	
//DO NOT MODIFY BELOW METHOD
	public Iterator<Integer> getLevelOrderIterator(){
		return new LevelOrderIterator();
	}
   
}
