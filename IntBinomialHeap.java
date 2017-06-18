package elice;

import java.util.NoSuchElementException;

/**
 * A class representing a priority queue for storing integers using a binomial min-heap.
 *
 * Invariants:
 * 1. The internal linked list is either empty or stores one or more binomial trees in the order of
 *    increasing degrees.
 * 2. "manyItems" is the number of all integers stored in the queue.
 */
public class IntBinomialHeap {
	Node<IntBinomialTree> head;			// a linked list storing binomial trees (a forest)
	int manyItems;						// # of data items in the forest

	/**
	 * The constructor.
	 */
	public IntBinomialHeap(){
		head = null;
		manyItems = 0;
	}

	/**
	 * Empties the queue.
	 */
	public void clear(){
		head = null;
		manyItems = 0;
	}

    /**
     * Returns one of the minimum items in the queue.
     * @return a minimum item in the queue.
     * @precondition
     * 	The queue is not empty.
     */
	public int peek(){
		if (head == null)
			throw new NoSuchElementException("Empty heap");
		return findMinTree().getKey();
	}

	/**
	 * Adds a new key and value "_key" and "_value" to the queue.
	 * @param _key  - a new key of element to be added to the queue
     * @param _value  - a new value of element to be added to the queue
	 */
	public void add(int _key, String _value){
		IntBinomialHeap queue = new IntBinomialHeap();
		IntBinomialTree tree = new IntBinomialTree(_key, _value);
		queue.appendTree (tree);
		merge(queue);
	}

	/**
	 * Removes a minimum item from the queue.
	 * @return a minimum item in the queue
     * @precondition
     * 	The queue is not empty.
	 */
	public int remove(){
		if (head == null)
			throw new NoSuchElementException("Empty heap");
		IntBinomialTree minTree = removeMinTree();
		merge(minTree.split());
		return minTree.getKey();
	}

	/**
	 * Merges a given queue "addend" with this queue.
	 * @param addend - a queue to be merged with this queue.
	 * @precondition
	 * 	The argument "addend" is not null.
	 */
	 public void merge(IntBinomialHeap addend)
	 {
	 	if(addend == null) throw new IllegalArgumentException();
		
		Node<IntBinomialTree> cursor1 = head;
		Node<IntBinomialTree> cursor2 = addend.head;
		clear();
		
		if(cursor1 == null){
			head = addend.head;
			manyItems = addend.manyItems;
			return;
		}
		
		while(cursor1 != null && cursor2 != null){
			IntBinomialTree tree1 = cursor1.getData();
			IntBinomialTree tree2 = cursor2.getData();
			
			if(tree1.getDegree() == tree2.getDegree()){
				cursor1 = cursor1.getLink();
				cursor2 = cursor2.getLink();
				
				tree1.merge(tree2);
				
				appendTree(tree1);
			}
			else if(tree1.getDegree() > tree2.getDegree()){
				appendTree(tree2);
				
				cursor2 = cursor2.getLink();
			}
			else{
				appendTree(tree1);
				
				cursor1 = cursor1.getLink();
			}
		}
		//Add remaining tree
		while(cursor1 != null){
			IntBinomialTree tree1 = cursor1.getData();
			appendTree(tree1);
			cursor1 = cursor1.getLink();
		}
		//Add remaining tree
		while(cursor2 != null){
			IntBinomialTree tree2 = cursor2.getData();
			appendTree(tree2);
			cursor2 = cursor2.getLink();
		}
		
		Node<IntBinomialTree> precursor = head;
		Node<IntBinomialTree> cursor = precursor.getLink();
		
		while(cursor != null){
			IntBinomialTree tree_curr = precursor.getData();
			IntBinomialTree tree_next = cursor.getData();
			
			if(tree_curr.getDegree() == tree_next.getDegree()){
				tree_curr.merge(tree_next);
				precursor.removeNodeAfter();
				cursor = precursor.getLink();
			}
			else{
				precursor = cursor;
				cursor = cursor.getLink();
			}
		}
		
	 }

	/**
	 * Appends a given binomial tree "tree" to the forest.
	 * @param tree - a binomial tree to be added to the forest
	 * @precondition
	 * 	The degree of the given tree is larger than any of the trees in the forest.
	 *  please refer to the "supplement.pdf": figure 11.10 (a)
	 *
	 */
	void appendTree(IntBinomialTree tree){
		// To be completed by students
		// ...
		Node<IntBinomialTree> cursor = head;
		if(head == null){
			head = new Node<IntBinomialTree>(tree,null);
		}
		else{
			while(cursor.getLink() != null){
				cursor = cursor.getLink();
			}
			cursor.setLink(new Node<IntBinomialTree>(tree,null));
		}
		manyItems += tree.size();
	}

	/**
	 * Removes one of the minimum trees from the forest.
	 * (A minimum tree means a tree containing a minimum element.)
	 * @precondition
	 * 	This forest is not empty.
	 */
	IntBinomialTree removeMinTree()
	{
		// To be completed by students
		// ...
		if(manyItems == 0) throw new IllegalStateException();
		
		IntBinomialTree ans = findMinTree();
		
		if(ans == head.getData()){
			head = head.getLink();
		}
		else{
			Node<IntBinomialTree> cursor = head;
			Node<IntBinomialTree> precursor = null;
			
			while(cursor.getLink() != null){
				if(cursor.getData() == ans) break;
				precursor = cursor;
				cursor = cursor.getLink();
			}
			precursor.setLink(cursor.getLink());
		}
		
		manyItems -= ans.size();
		return ans;
	}

	/**
	 * Finds and returns one of the minimum trees from the heap, if any.
	 * (A minimum tree means a tree containing a minimum element.)
	 * @return - one of the minimum trees from the forest, if any.
	 */
	IntBinomialTree findMinTree(){
		// To be completed by students
		// ...
		if(head == null) return null;
		
		Node<IntBinomialTree> cursor = head;
		IntBinomialTree ans = head.getData();
		
		while(cursor.getLink() != null){
			if(ans.getKey() >= cursor.getLink().getData().getKey()){
				ans = cursor.getLink().getData();
			}
			cursor = cursor.getLink();
		}
		return ans;
        
	}

	/**
	 * Prints the content of the queue (for debugging).
	 * You are free to modify or remove this method.
	 */
    public void print(){
    	System.out.printf("[IntBinomialTree with %d items]\n", manyItems);
        Node<IntBinomialTree> cur = head;
        while(cur != null){
        	IntBinomialTree tree = cur.getData();
        	tree.print(0);
        	cur = cur.getLink();
        }
    }
}
