package adt;

import java.util.Collection;
/**
 * 
 * @author Borja Moralejo Tobajas
 * This is the Hash Map abstract data type interface. The hash map gets an element with a key and stores it hashing the key, it transforms the key into an index.
 * @param <T> Elements to store
 * @param <K> Key
 */
public interface HashMapADT<T, K> {

	/**
	 * Adds the given element into the hash map.
	 * @param K key - Key for storing element in hash map .
	 * @param T element - Element which is going to be added into the hash map.
	 */
	public void put(K key, T element);
	
	/**
	 * Gets element with given key.
	 * @param K key - Searches element with key.
	 * @return Returns T element binded with K key.
	 */
	public Collection<T> get(K key);
	
	/**
	 * Tries to remove the given element from the Collection<T> and returns whether or not has removed it.
	 * @param key - K type. Key to search with.
	 * @param element - T type. Element that is going to be removed.
	 * @return boolean - Whether or not has been removed.
	 */
	public boolean remove(K key, T element);
	
	/**
	 * Puts all elements from the HashMap into a Collection<T> and returns it.
	 * @return Collection<T> - All elements.
	 */
	public Collection<T> getAllElements();
	
	
	/**
	 * Whether or not the given element is in the HashMap.
	 * @param key - K type. 
	 * @param element - T type.
	 * @return boolean
	 */
	public boolean isIn(K key, T element);
	
	/**
	 * Gets the size of the collection
	 * @return int - Size of the collection
	 */
	public int size();
	
	
	public String toString();
	
}
