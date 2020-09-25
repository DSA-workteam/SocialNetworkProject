package adt;

public interface HashMapADT<T,K> {

	/**
	 * Adds the given element into the hash map
	 * @param K key - Key for storing element in hash map 
	 * @param T element - Element which is going to be added into the hash map
	 */
	public void put(K key, T element);
	
	/**
	 * Gets element with given key
	 * @param K key - Searches element with key
	 * @return Returns T element binded with K key
	 */
	public T get(K key);
	
	/**
	 * Gets the size of the collection
	 * @return int - Size of the collection
	 */
	public int size();
	
	
	public String toString();
	
}
