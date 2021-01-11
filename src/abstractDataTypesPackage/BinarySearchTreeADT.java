/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package abstractDataTypesPackage;

import exceptions.ElementNotFoundException;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> 
{
/** 
    * Adds the specified element to the proper location in this tree. 
    *
    * @param element  the element to be added to this tree
    */

   public void addElement (T element);

   /** 
    * Removes and returns the specified element from this tree. 
    *
    * @param targetElement  the element to be removed from this tree
    * @return               the element removed from this tree
 * @throws ElementNotFoundException  - There's no element
    */ 

   public T removeElement (T targetElement) throws ElementNotFoundException;
 

 
   

}


