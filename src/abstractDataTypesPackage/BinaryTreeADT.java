package abstractDataTypesPackage;

import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesImplemented.GenericArrayListBinaryTree;
import exceptions.ElementNotFoundException;


/**
 * ADT of the Binary tree
 * @author Imanol Maraña Hurtado
 *
 * @param <T> Element 
 */
public interface BinaryTreeADT<T extends Comparable<T>>  {
	
	/**
 	* Given a key, returns the elements of all the nodes linked to the root. The collection of items will have an inorder form, being the first item the one of lower alphabetical value
 	* @return A collection with all the elements linked to this node.
 	* @param tree {@link GenericArrayListBinaryTree}
 	* @throws ElementNotFoundException {@link ElementNotFoundException}
 	*/
	public Collection<T> getAllElements();
	
	
	/**
	 * Returns the key element of the current node
	 * @return Key element
	 */
	public T getKeyElement();
	
	
	/**
	 * Given a T element and a K key, adds a new node with the starting T element on it in it's correct position, if there is't already in the tree.
	 * @param element Key with which find the correct position and then assigned to it
	 * @return a boolean that indicates if the node was created or already existed
	 */
	public boolean addElement(T element);
	
	
	/**
	 * Returns the node located the farest of the root that has both sons null.
	 * @return Int with the depth of the last node
	 */
	public int longestDepth();
	
	
	/**
	 * Returns the depth at which a node whose key matches the given key is located
	 * @param element Given key to compare to that of the existing nodes
	 * @return Int with the depth of the node whose key matches that of the given key
	 */
	public int depthOfElement(T element) throws ElementNotFoundException;
	
	
	/**
	 * Returns the depth of the node that has at least one null son located the closest to the root
	 * @return First node with a null son
	 */
	public int shortestDepth();
	
	public T removeElement(T remElem) throws ElementNotFoundException;
	
	
	/**  
	* Returns the string representation of the binary tree.
	*
	* @return  a string representation of the binary tree
	*/
	public String toString();
	
	
    /**
	* 	
	* Performs an inorder traversal on this binary tree by calling an 
	* overloaded, recursive inorder method that starts with the root. 
	* @param tree {@link BinaryTreeADT}
	* @return  an iterator over the elements of this binary tree
	*/
	public Iterator<T> iteratorInOrder();
	
	
	/** 
	* Performs a preorder traversal on this binary tree by calling an 
	* overloaded, recursive preorder method that starts with the root. 
	* 
	* @param tree {@link BinaryTreeADT}
	* @return  an iterator over the elements of this binary tree
	*/
	public Iterator<T> iteratorPreOrder();

	
	/**   
	* Performs a postorder traversal on this binary tree by calling an 
	* overloaded, recursive postorder method that starts with the root. 
	* @param tree {@link BinaryTreeADT}
	* @return  an iterator over the elements of this binary tree
	*/
	public Iterator<T> iteratorPostOrder();

	
	/**  
	* Performs a levelorder traversal on the binary tree, using a queue.
	* @param tree {@link BinaryTreeADT}
	* @return  an iterator over the elements of this binary tree
	*/
	public Iterator<T> iteratorLevelOrder();
	
}
