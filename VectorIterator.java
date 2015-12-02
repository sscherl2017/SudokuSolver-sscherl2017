import java.util.NoSuchElementException;

import java.util.Iterator;

/**
* An Iterator which acts upon a Vector.
* @author Sam Scherl
* @version 10/25/15
*/
public class VectorIterator<E> implements Iterator<E>
{

	/** the Vector which is passed in as a parameter */
	private Vector<E> vector;
	
	/** the current index of the Iterator within the Vector */
	private int current;
	
	/**
	* A constructor which takes in a Vector as a parameter.
	* @param v		The Vector which the Iterator will act upon
	*/
	public VectorIterator(Vector<E> v)
	{
		vector = v;
		current = 0;
	}
	
	/**
	* Returns true if there is another E within the Vector, false if there is not another E.
	* @return		True if the there is another E within the Vector, false if there is not one
	*/
	public boolean hasNext()
	{
		return (current < vector.size());
	}
	
	/**
	* Returns the next E within the Vector, and throws a NoSuchElementException if there is not another E in
	* the Vector.
	* @return		The next E within the Vector
	*/
	public E next()
	{
		if (!hasNext())
			throw new NoSuchElementException();
		current += 1;
		return vector.get(current - 1);		
	}
}