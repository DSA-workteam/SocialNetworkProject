package abstractDataTypesPackage;

import java.util.Collection;

import exceptions.ElementNotFoundException;


/**
 * ADT of the Binary tree
 * @author Imanol Maraña Hurtado
 *
 * @param <T> Element
 * @param <K> Key. K type must extend ComparableK
 */
public interface BinaryTreeADT< T, K extends Comparable<K> >  {
	
	
	/**
	 * Given a key, returns the information located in a node that matches with the given key, if there is.
	 * @param searchKey A key to search for the correct node
	 * @return A collection with all the information saved in the node with the given key
	 */
	public Collection<T> getElemets(K searchKey);
	
	
	/**
	 * Given a T element and a K key, adds a new node with the starting T element on it in it's correct position, if there is't already in the tree.
	 * @param element Starting element of the node of the tree
	 * @param addKey Key with which find the correct position and then assigned to it
	 * @return a boolean that indicates if the node was created or already existed
	 */
	public boolean addElement(T element, K addKey);
	
	
	/**
	 * Returns the amount of nodes that the binary tree has
	 * @return Int with the number of existing nodes
	 */
	public int numberOfElements();
	
	
	/**
	 * Returns the node located the farest of the root that has both sons null.
	 * @return Int with the depth of the last node
	 */
	public int longestDepth();
	
	/**
	 * Returns the depth at which a node whose key matches the given key is located
	 * @param key Given key to compare to that of the existing nodes
	 * @return Int with the depth of the node whose key matches that of the given key
	 */
	public int depth(K key);
	
	
	/**
	 * Returns the depth of the node that has at least one null son located the closest to the root
	 * @return First node with a null son
	 */
	public int shortestDepth();
	
	public void removeElement(K remKey) throws ElementNotFoundException;
	
}
