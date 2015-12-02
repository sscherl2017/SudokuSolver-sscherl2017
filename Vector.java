import java.util.Iterator;

/** 
 *  This class creates a Vector which will allow storage of an Object of type E
 * and allows many operations to be done upon the Vector such as adding, removing and modifying.
 * @author Sam Scherl
 * @version 10/25/15
 */
public class Vector<E> implements Iterable<E>
{
	/** An array of type Object which will store all values added to the Vector. */
	private Object[] data;
	
	/** An int which represents the number of Objects stored in the Vector. */
	private int size;
	
	/**
	* The default constructor which sets the length of the array and the capacity to 10 and the size 
	* of the array to 0.
	*/
	public Vector()
	{
		this(10);
	}
	
	/**
	* A constructor which sets the length of the array and the capacity to the given capacity and sets
	* the size to 0.
	* @param initCapacity	The initial capacity of the array
	*/
	public Vector(int initCapacity)
	{
		if (initCapacity <= 0)
			throw new IllegalArgumentException("You must input an initial capacity that is greater than 0");
		data = new Object[initCapacity];
		size = 0;
	}
	
	/**
	* A constructor which initializes the new Vector as a copy of the given Vector, sets the capacity and size to the size
	* of the given Vector.
	* @param other		The Vector which is to be copied
	*/ 
	public Vector(Vector<E> other)
	{
		if (other == null)
			throw new IllegalArgumentException("The paramater cannot be of type null");
		data = new Object[other.data.length];
		size = 0;
		for (int i = 0; i < other.size; i++)
			add(other.get(i));
	}
	
	/**
	* Adds the given Object of type E to the end of the Vector.
	* @param toAdd		The Object which will be added to the Vector
	*/				
	public void add(E toAdd)
	{
		add(size, toAdd);
	}
	
	/**
	* Adds the given Object of type E to the given index in the Vector and shifts all the following
	* Objects over one spot to the right.
	* @param index		The index where the Object will be added to the Vector
	* @param toAdd		The Object which will be added to the Vector at the given index
	*/
	public void add(int index, E toAdd)
	{
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("The index must be greater than or equal to 0 and less than " + (size + 1));
		if (size == data.length)
			increaseCapacity();
		for (int i = size; i > index; i--)
			data[i] = data[i - 1];
		data[index] = toAdd;
		size++;
	}
	
	/**
	* Doubles the length and capacity of the Array.
	*/
	private void increaseCapacity()
	{
		Object[] helper = data;
		data = new Object[data.length * 2];
		for (int i = 0; i < helper.length; i++)
			data[i] = helper[i];
	}
	
	/**
	* Returns the Object at the given index.
	* @param index		The index where the Object will be found
	* @return		The Object found at the given index
	*/
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("The index must be greater than or equal to 0 and less than " + size);
		return (E) data[index];
	}
	
	/**
	* Removes the Object at the given index, shifts all the following Objects to the left by one
	* and returns the Object which has been removed.
	* @param index		The index at which the Object will be removed
	* @return		The object which has been removed from the Vector
	*/
	public E remove(int index)
	{
		E output = get(index);
		data[index] = null;
		for (int i = index; i < size - 1; i++)
			data[i] = data[i + 1];
		data[size - 1] = null;
		size--;
		return output;
	}
		
	/**
	* Removes the first instance of a specified Object. Returns true if the object was successfully removed, and
	* false if not successfully removed.
	* @param obj		The Object which is to be removed
	* @return		True if the Object was successfully removed, false if not successfully removed
	*/
	public boolean remove(E obj)
	{
		if (indexOf(obj) == -1)
			return false;
		remove(indexOf(obj));
		return true;
	}
		
	/**
	* Sets the specified index to the given Object and returns the Objet which has been replaced.
	* @param index		The index at which the given Object should be placed
	* @param obj		The Object which should be placed at the given index
	* @return		The Object which has been replaced in the Vector
	*/
	public E set(int index, E obj)
	{
		E output = get(index);
		data[index] = obj;
		return output;
	}
	
	/**
	* Returns the number of Objects in the Vector.
	* @return		The number of Objects in the Vector
	*/
	public int size()
	{
		return size;
	}
	
	/**
	* Removes all Objects from the Vector.
	*/
	public void clear()
	{
		data = new Object[data.length];
		size = 0;
	}
	
	/**
	* Returns true if the Vector is empty, false if it is not empty.
	* @return		True if the list is empty, false if it is not
	*/
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		return false;
	}
	
	/**
	* Returns true if the Object is in the Vector, false if it is not in the Vector.
	* @param obj		The Object to be searched for
	* @return		True if the Object is in the Vector, false if it is not in the Vector
	*/	
	public boolean contains(E obj)
	{
		if (indexOf(obj) == -1)
			return false;
		return true;	
	}
	
	/**
	* Returns the index of the given Object in the Vector.
	* @param obj		The Object which will be searched for
	* @return		The index of the Object in the Vector
	*/
	public int indexOf(E obj)
	{
		for (int i = 0; i < size; i++)
		{	
			if (get(i) == null)
			{
				if (obj == get(i))
					return i;
			}
			else
			{
				if (get(i).equals(obj))
					return i;
			}
		}
		return -1;
	}
	
	/**
	* Returns a String representation of the Vector, with a dashed line between where the size of the Vector ends 
	* and the rest of the Array.
	* @return		A String representation of the Vector
	*/
	public String toString()
	{
		String helper = "";
		for (int i = 0; i < size; i++)
			helper += i + ": " + data[i] + "\n";
		helper += "------------" + "\n";
		for (int i = size; i < data.length; i++)
			helper += i + ": " + data[i] + "\n";
		return helper;
	}
	
	/**
	* Returns an Iterator of the Vector.
	* @return		An Iterator of the Vector
	*/
	public Iterator<E> iterator()
	{
		return new VectorIterator<E>(this);
	}
}