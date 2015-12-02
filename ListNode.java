/**
* This class is a ListNode, which forms the basis of a LinkedList. It has ways to get and set
* class fields in the ListNode.
* @author Sam Scher
* @version 11/19/15
*/
public class ListNode<E>
{
	/** The object contained within the ListNode. */
	private E value;
	
	/** A pointer to the next ListNode in the LinkedList. */
	private ListNode<E> next;
	
	/**
	* A Constructor which sets the value equal to the given Object. 
	* @param v		the Object which value will be set to
	*/
	public ListNode(E v)
	{
		this(v, null);
	}
	
	/** 
	* A Constructor which sets the value equal to the given Object, and pointer 
	* to the next ListNode to the given pointer
	* @param v		the Object which value will be set to
	* @param n		the pointer to the next ListNode which next will be set to
	*/
	public ListNode(E v, ListNode<E> n)
	{
		value = v;
		next = n;
	}
	
	/**
	* Returns the value of the ListNode.
	* @return		the value of the ListNode
	*/
	public E getValue()
	{
		return value;
	}
	
	/**
	* Returns the pointer to the next ListNode.
	* @return		the pointer to the next ListNode
	*/
	public ListNode<E> getNext()
	{
		return next;
	}
	
	/**
	* Sets the value to the gien Object.
	* @param v		the Object which the value will be set to
	*/
	public void setValue(E v)
	{
		value = v;
	}
	
	/**
	* Sets the pointer to the given ListNode
	* @param n		the pointer to the next ListNode which next will be set to
	*/
	public void setNext(ListNode<E> n)
	{
		next = n;
	}
	
	/**
	* Returns a String representation of the ListNode.
	* @return		a String representation of the ListNode
	*/
	public String toString()
	{
		String output = "";
		return value + output;
	}
}
