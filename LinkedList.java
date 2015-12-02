import java.util.NoSuchElementException;

import java.util.Iterator;

/** 
 *  This class is a LinkedList which contains a series of singly linked ListNode. It has many
 * methods for the adding, removing, and modifying of the LinkedList.
 * @author Sam Scherl
 * @version 11/18/15
 */
public class LinkedList<E> implements Iterable<E>, Stack<E>, Queue<E>
{
		/** A pointer to the first ListNode in the LinkedList. */
		private ListNode<E> head;
		
		/** A pointer to the last ListNode in the LinkedList. */
		private ListNode<E> tail;
		
		/** The number of ListNodes in the LinkedList. */
		private int size;
		
		/**
		* The default constructor initializes a LinkedList with nothing in it
		*/
		public LinkedList()
		{
			head = null;
			tail = null;
			size = 0;
		}
		
		/**
		* A constructor which initializes a LinkedList with the given ListNode being
		* the only thing in it.
		* @param h		the ListNode which will become the first in the new LinkedList
		*/
		public LinkedList(ListNode<E> h)
		{
		
			this();
			add(h.getValue());
		}
		
		/**
		* A constructor initializes a LinkedList as a deep copy of the given LinkedList.
		* @param other		the LinkedList which will be copied for the new LinkedList
		*/
		public LinkedList(LinkedList<E> other)
		{
			this();
			for (int i = 0; i < other.size; i++)
				add(other.get(i));
			
		}
		
		/**
		* Add the given value to the end of the LinkedList. 
		* @param value		the value which will be added to the LinkedList
		*/
		public void add(E value)
		{	
			ListNode<E> newListNode = new ListNode<E>(value);
			if (size == 0)
			{
				head = newListNode;
				tail = newListNode;
			}
			else
			{
				tail.setNext(newListNode);
				tail = newListNode;
			}
			size++;
		}
		
		/**
		* Add the given value to the Stack which is represented by the LinkedList.
		* @param value		the value which will be added to the Queue
		*/
		public void push(E value)
		{
			addFirst(value);
		}
		
		/**
		* Adds the given value to the Queue which is represented by the LinkedList.
		* @param value		the value which will be added to the Queue
		*/
		public void offer(E value)
		{
			addLast(value);
		}
		
		/**
		* Removes the first occurence of the given value from the LinkedList and returns true if the value
		* was successfully removed, false if it was not.
		* @param value		the value which will be removed
		* @return		true if the value was successfully removed, false if it was not
		*/
		public boolean remove(E value)
		{
			if (!contains(value))
				return false;
			remove(indexOf(value));
			return true;
		}
		
		/**
		* Removes and returns the value at the given index.
		* @param index		the index of the value which will be removed
		* @return		the value which has been removed
		*/
		public E remove(int index)
		{
			if (index < 0 || index > size - 1)
				throw new IndexOutOfBoundsException("Index Must be greater than or equal to 0 and less than or equal to " + (size - 1));
			E holder = get(index);
			if (size == 1)
			{	
				head = null;
				tail = null;
			}	
			else if (index == 0)
			{
				head = head.getNext();
			}
			else
			{
				ListNode<E> curr = head;
				for (int i = 0; i < index - 1; i++)
					curr = curr.getNext();
				if (index == size - 1)
					tail = curr;
				curr.setNext(curr.getNext().getNext());
			}
			size--;
			return holder;
		}
		
		/**
		* Removes the first value in the LinkedList.
		* @return		the value which has been removed
		*/
		public E removeFirst()
		{
			if (size == 0)
				throw new NoSuchElementException();
			return remove(0);
		}
		
		/**
		* Removes the last value in the LinkedList.
		* @return		the value which has been removed
		*/
		public E removeLast()
		{
			if (size == 0)
				throw new NoSuchElementException();
			return remove(size - 1);
		}
		
		/**
		* Adds the given value to the front of the LinkedList.
		* @param value		the value which wil be added to the front of the LinkedList
		*/
		public void addFirst(E value)
		{
			add(0, value);
		}
		
		/**
		* Adds the given value to the end of the LinkedList.
		* @param value		the value which wil be added to the end of the LinkedList
		*/
		public void addLast(E value)
		{
			add(value);
		}
		
		/**
		* Returns true if the LinkedList contains the given value, fale if it does not.
		* @param value		the value which will be searched for
		* @return		true if the LinkedList contains the given value, false if it does not
		*/
		public boolean contains(E value)
		{
			return (indexOf(value) != -1);
		}
		
		/**
		* Returns the number of values in the LinkedList.
		* @return		the number of values in the LinkedList
		*/
		public int size()
		{
			return size;
		}
		
		/**
		* Clears all values from the LinkedList.
		*/
		public void clear()
		{
			head = null;
			tail = null;
			size = 0;
		}
		
		/**
		* Returns the value at the given index.
		* @param index		the index where the value will be found
		* @return		the value which is at the given index
		*/
		public E get(int index)
		{
			if (index < 0 || index > size - 1)
				throw new IndexOutOfBoundsException("Index Must be greater than or equal to 0 and less than or equal to " + (size - 1));
			ListNode<E> curr = head;
			for (int i = 0; i < index; i++)
			{
				curr = curr.getNext();
			}
			return curr.getValue();
		}
		
		/**
		* Sets the given index to the given value and returns the value which has been replaced.
		* @param index		the index where the given value should be set
		* @param value		the value which will be set at the given index
		* @return		the value which has been replaced
		*/
		public E set(int index, E value)
		{
			if (index < 0 || index > size - 1)
				throw new IndexOutOfBoundsException("Index Must be greater than or equal to 0 and less than or equal to " + (size - 1));
			E holder = get(index);
			remove(index);
			add(index, value);
			return holder;
		}
		
		/**
		* Adds the given value to the given index.
		* @param index		the index where the value should be added
		* @param value		the value which will be added at the given index
		*/
		public void add(int index, E value)
		{
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("Index Must be greater than or equal to 0 and less than or equal to " + size);
			if (size == 0 || index == size)
				add(value);
			else if (index == 0)
			{
				ListNode<E> newListNode = new ListNode<E>(value);
				newListNode.setNext(head);
				head = newListNode;
				size++;
			}
			else
			{
				ListNode<E> newListNode = new ListNode<E>(value);
				ListNode<E> curr = head;
				for (int i = 0; i < index - 1; i++)
					curr = curr.getNext();
				newListNode.setNext(curr.getNext());
				curr.setNext(newListNode);
				size++;
			}
		}
		
		/**
		* Returns the index of the given value.
		* @param value		the value which will be searched for
		* @return		the index of the given value
		*/
		public int indexOf(E value)
		{
			ListNode<E> curr = head;
			for (int index = 0; index < size; index++)
			{
				if (value == null)
				{
					if (curr.getValue() == value)
						return index;
				}
				else if (value.equals(curr.getValue()))
					return index;
				curr = curr.getNext();
			}
			return -1;
		}
		
		/**
		* Returns and removes the first value in the Queue represented by the LinkedList.
		* @return		the value which has been removed from the Queue
		*/
		public E poll()
		{
			return removeFirst();
		}
		
		/**
		* Returns and removes the first value in the Stack represented by the LinkedList.
		* @return		the value which has been removed from the Stack
		*/
		public E pop()
		{
			return removeFirst();
		}
		
		/**
		* Returns the first value in the Stack or Queue represented by the LinkedList.
		* @return		the first value in the Stack or Queue
		*/
		public E peek()
		{
			return get(0);
		}
		
		/**
		* Returns true if the LinkedList is empty, false if it is not.
		* @return		true if the LinkedList is empty, false if it is not
		*/
		public boolean isEmpty()
		{
			return (size == 0);
		}
		
		/**
		* Returns a String representation of the LinkedList.
		* @return		the String representation of the LinkedList
		*/
		public String toString()
		{
			String output = "";
			int index = 0;
			for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
			{
				output += index + ": " + curr;
				if (curr.getNext() != null)
					output += "\n";
				index++;
			}
			return output;
		}
		
		/**
		* Returns an Iterator of the LinkedList.
		* @return		an Iterator of the LinkedList
		*/
		public Iterator<E> iterator()
		{
			return new LinkedListIterator<E>(head);
		}
	}