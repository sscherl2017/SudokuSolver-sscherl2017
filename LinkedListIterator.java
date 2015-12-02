import java.util.NoSuchElementException;

import java.util.Iterator;

/** 
* This class is an Iterator for a LinkedList.
* @author Sam Scherl
* @version 11/19/15
*/
public class LinkedListIterator<E> implements Iterator<E>
{
	/** The current ListNode within the LinkedList. */
	private ListNode<E> curr;
	
	/**
	* A Constructor which sets linkedList to the given LinkedList and initializes current as 0.
	* @param l		the LinkedList which will be Iterated.
	*/
	public LinkedListIterator(ListNode<E> l)
	{
		curr = l;
	}
	
	/**
	* Returns true if there are more Objects in the LinkedList, false if there are not.
	* @return		 true if there are more Objects in the LinkedList, false if there are not
	*/
	public boolean hasNext()
	{
		return (curr != null);
	}
	
	/**
	* Returns the next value within the LinkedList.
	* @return		the next value within the LinkedList
	*/
	public E next()
	{
		if (!hasNext())
			throw new NoSuchElementException();
		E holder = curr.getValue();
		curr = curr.getNext();
		return holder;
	}
}

