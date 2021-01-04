/**
 * BinarySearchTreeList represents an ordered list implemented using a binary
 * search tree.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */
package abstractDataTypesImplemented;
import java.util.Iterator;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
public class BinarySearchTreeList<T> extends LinkedBinarySearchTree<T> 
                 implements Iterable<T>
{  
	/**
    * Creates an empty BinarySearchTreeList.
    */
   public BinarySearchTreeList() 
   { super();  }

   /**
    * Adds the given element to this list.
    * 
    * @param element  the element to be added to this list
    */
   public void add (T element)
   { addElement(element);  }
   
   /**
    * Removes and returns the first element from this list.
    *
    * @return  the first element in this list
 * @throws EmptyCollectionException  - There's no element
    */
   public T removeFirst () throws EmptyCollectionException
   { return removeMin(); }
   
   /**
    * Removes and returns the last element from this list.
    *
    * @return  the last element from this list
 * @throws EmptyCollectionException  - There's no element
    */
   public T removeLast () throws EmptyCollectionException
   {  return removeMax(); }

  /**
   * Removes and returns the specified element from this list.
   *
   * @param element  the element being sought in this list
   * @return         the element from the list that matches the target
 * @throws ElementNotFoundException  - There's no element
   */
   public T remove (T element) throws ElementNotFoundException
   {  return removeElement(element);  }

  /**
   * Returns a reference to the first element on this list.
   *
   * @return  a reference to the first element in this list
 * @throws EmptyCollectionException  - There's no element
   */
   public T first () throws EmptyCollectionException
   {  return findMin();  }

  /**
   * Returns a reference to the last element on this list.
   *
   * @return  a reference to the last element in this list
 * @throws EmptyCollectionException - There's no element
   */
   public T last () throws EmptyCollectionException
   {  return findMax(); }

  /**
   * Returns an iterator for the list.
   *
   * @return  an iterator over the elements in this list
   */
   public Iterator<T> iterator()
   {  return iteratorInOrder();  }
}

