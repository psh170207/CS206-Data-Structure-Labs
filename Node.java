
package cs206b;


public class Node<T>
{
   // Please inmplement Node class.
   // Reference DoubleNode in previous homework, and change it to generic version.
   // You don't have to impelemnt every method in DoubleNode class. Just make sure that DoublyLinkedSseq works well.
// File: DoubleNode.java from the package edu.colorado.nodes
// Complete documentation is available from the DoubleNode link in:
//   http://www.cs.colorado.edu/~main/docs

/******************************************************************************
* A DoubleNode provides a node for a linked list with 
* double data in each node.
*
* @note
*   Lists of nodes can be made of any length, limited only by the amount of
*   free memory in the heap. But beyond Integer.MAX_VALUE (2,147,483,647),
*   the answer from listLength is incorrect because of arithmetic
*   overflow. 
*
* @see
*   <A HREF="../../../../edu/colorado/nodes/DoubleNode.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/nodes/DoubleNode.java) </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
*
* @see Node
* @see BooleanNode
* @see ByteNode
* @see CharNode
* @see FloatNode
* @see IntNode
* @see LongNode
* @see ShortNode
******************************************************************************/
   // Invariant of the DoubleNode class:
   //   1. The node's double data is in the instance variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the link part is a reference to the
   //      next node of the list.
   private T data;
   private Node<T> link;
   private Node<T> prv; // previous link.


   /**
   * Initialize a node with a specified initial data and link to the next
   * node. Note that the initialLink may be the null reference, 
   * which indicates that the new node has nothing after it.
   * @param initialData
   *   the initial data of this new node
   * @param initialLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * @postcondition
   *   This node contains the specified data and link to the next node.
   **/   
   public Node(T initialData, Node<T> initialLink, Node<T> initialPrv)
   {
      data = initialData;
      link = initialLink;
	  prv = initialPrv;
   }


   /**
   * Modification method to add a new node after this node.   
   * @param item
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed after this node.
   *   The data for the new node is item. Any other nodes
   *   that used to be after this node are now after the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new 
   *   DoubleNode. 
   **/
   public void addNodeAfter(T item)   
   {
      link = new Node<T>(item, link, this.prv);
   }          
   
   
   /**
   * Accessor method to get the data from this node.   
   * @return
   *   the data from this node
   **/
   public T getData( )   
   {
      return data;
   }
   
   
   /**
   * Accessor method to get a reference to the next node after this node. 
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public Node<T> getLink( )
   {
      return link;                                               
   } 
    
   public Node<T> getPrv()
   {
      return prv;
   }
    
   /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source. The return value is the head reference for the
   *   copy. 
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/ 
   @SuppressWarnings("unchecked")
   public static <T> Node<T> listCopy(Node<T> source)
   {
      Node<T> copyHead;
      
      // Handle the special case of the empty list.
      if (source == null)
         return null;
         
      // Make the first node for the newly created list.
	  copyHead = new Node<T>(null, null, null);
      Node<T> firstElem = new Node<T>(source.getData(),null,copyHead);
	  copyHead.setLink(firstElem);
      
      
      // Make the rest of the nodes for the newly created list.
	  Node<T> copycursor = firstElem;
      while (source.link != null)
      {
         source = source.link;
         Node<T> temp = new Node<T>(source.getData(),source.getLink(),copycursor);
		 copycursor = temp;
      }
      // Return the head reference for the new list.
      return copyHead;
   }
   
   
   /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source.  The return value is an
   *   array where the [0] element is a head reference for the copy and the [1]
   *   element is a tail reference for the copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/
   @SuppressWarnings("unchecked")
   public static <T> Object[] listCopyWithTail(Node<T> source)
   {
      Node<T> copyHead;
      Node<T> copyTail;
      Object[] answer = new Object[2];
     
      // Handle the special case of the empty list.   
      if (source == null)
         return answer; // The answer has two null references .
      
      // Make the first node for the newly created list.
      copyHead = new Node<T>(source.data, null, null);
      copyTail = copyHead;
      
      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references.
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }
   
   
   /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list
   *   with a null head)
   * @return
   *   the number of nodes in the list with the given head 
   * @note
   *   A wrong answer occurs for lists longer than Int.MAX_VALUE.     
   **/   
   @SuppressWarnings("unchecked")
   public static <T> int listLength(Node<T> head)  // Tbew is non-static.
   {
      Node<T> cursor;
      int answer;
      
      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.link)
         answer++;
        
      return answer;
   }
   

   /**
   * Copy part of a list, providing a head and tail reference for the new copy. 
   * @param start
   *   reference to start node of a linked list
   * @param end
   *   reference to end node of a linked list
   * @precondition
   *   start and end are non-null references to nodes
   *   on the same linked list,
   *   with the start node at or before the end node. 
   * @return
   *   The method has made a copy of the part of a linked list, from the
   *   specified start node to the specified end node. The return value is an
   *   array where the [0] component is a head reference for the copy and the
   *   [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates that start and end are not references
   *   to nodes on the same list.
   * @exception NullPointerException
   *   Indicates that start is null.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.    
   **/   
   @SuppressWarnings("unchecked")
   public static <T> Object[] listPart(Node<T> start, Node<T> end)
   {
      Node<T> copyHead;
      Node<T> copyTail;
      Node<T> cursor;
      Object[] answer = new Object[2];
      
      // Make the first node for the newly created list. Notice that this will
      // cause a NullPointerException if start is null.
      copyHead = new Node<T>(start.data, null, null);
      copyTail = copyHead;
      cursor = start;
      
      // Make the rest of the nodes for the newly created list.
      while (cursor != end)
      {
         cursor = cursor.link;
         if (cursor == null)
            throw new IllegalArgumentException
            ("end node was not found on the list");
         copyTail.addNodeAfter(cursor.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }        
   
   
   /**
   * Find a node at a specified position in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @precondition
   *   position &gt; 0.
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list. (The head node is position 1, the next node is position 2, and
   *   so on.) If there is no such position (because the list is too short),
   *   then the null reference is returned.
   * @exception IllegalArgumentException
   *   Indicates that position is not positive.    
   **/   
   @SuppressWarnings("unchecked")
   public static <T> Node<T> listPosition(Node<T> head, int position)
   {
      Node<T> cursor;
      int i;
      
      if (position <= 0)
           throw new IllegalArgumentException("position is not positive");
      
      cursor = head;
      for (i = 1; (i < position) && (cursor != null); i++)
         cursor = cursor.link;

      return cursor;
   }


   /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a piece of data to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If there is no such node, the null reference is 
   *   returned.     
   **/   
   @SuppressWarnings("unchecked")
   public static <T> Node<T> listSearch(Node<T> head, T target)
   {
      Node<T> cursor;
      
      for (cursor = head; cursor != null; cursor = cursor.link)
         if (target == cursor.data)
            return cursor;
        
      return null;
   }

   
   /**
   * Modification method to remove the node after this node.   
   * @precondition
   *   This node must not be the tail node of the list.
   * @postcondition
   *   The node after this node has been removed from the linked list.
   *   If there were further nodes after that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
   public void removeNodeAfter( )   
   {
      link = link.link;
   }          
   
   
   /**
   * Modification method to set the data in this node.   
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   **/
   public void setData(T newData)   
   {
      data = newData;
   }                                                               
   
   
   /**
   * Modification method to set the link to the next node after this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   * @postcondition
   *   The link to the node after this node has been set to newLink.
   *   Any other node (that used to be in this link) is no longer connected to
   *   this node.
   **/
   public void setLink(Node<T> newLink)
   {                    
      link = newLink;
   }
   
   public void setPrv(Node<T> newPrv)
   {
      prv = newPrv;
   }
}
