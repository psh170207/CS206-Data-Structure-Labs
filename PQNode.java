
package elice;

public class Node<E>
{
   // Please inmplement Node class.
   // Reference DoubleNode in previous homework, and change it to generic version.
   // You don't have to impelemnt every method in DoubleNode class. Just make sure that DoublyLinkedSseq works well.
   // Invariant of the DoubleNode class:
   //   1. The node's double data is in the instance variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the link part is a reference to the
   //      next node of the list.
   private E data;
   private int priority;
   private Node<E> link;
   private Node<E> prvlink;

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
   public Node(int initialPriority, E initialData, Node<E> initialLink, Node<E> initialPrvlink)
   {
   	  priority = initialPriority;
      data = initialData;
      link = initialLink;
	  prvlink = initialPrvlink;
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
   /**
   * Accessor method to get the data from this node.   
   * @return
   *   the data from this node
   **/
   public E getData()   
   {
      return data;
   }
   
   public int getPriority()
   {
   		return priority;
   }
   
   /**
   * Accessor method to get a reference to the next node after this node. 
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public Node<E> getLink( )
   {
      return link;                                               
   } 

   public Node<E> getPrvlink()
   {
      return prvlink;
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
   /**
   * Modification method to set the data in this node.   
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   **/
   public void setData(E newData)   
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
   public void setLink(Node<E> newLink)
   {                    
      link = newLink;
   }
   
   public void setPrvlink(Node<E> newPrvlink)
   {
      prvlink = newPrvlink;
   }
}
