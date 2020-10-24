package abstractDataTypesPackage;

import java.util.Collection;

import exceptions.ElementNotFoundException;

/**
 * This is the Hash Table abstract data type interface. The hash table gets an element with a key and stores it hashing the key, it transforms the key into an index.
 * @author Borja Moralejo Tobajas
 * 
 * @param <T> Elements to store
 * @param <K> Key
 */
public interface HashTableADT<Value, Key extends Comparable<Key>> extends Iterable<Value>{

	/**
	 * Adds the given element into the hash table.
	 * @param key - Key for storing element in hash table .
	 * @param element - Element which is going to be added into the hash table.
	 */
	public void put(Value v, Key k);
	
	
	
	/**
	 * Gets element with given key.
	 * @param key - Searches element with key.
	 * @return Returns T element binded with K key.
	 * @throws ElementNotFoundException 
	 */
	public Value get(Key key) throws ElementNotFoundException;
	
	
		
	/**
	 * Tries to remove the given element from the CollectionT and returns whether or not has removed it.
	 * @param key - K type. Key to search with.
	 * @param element - T type. Element that is going to be removed.
	 * @return boolean - Whether or not has been removed.
	 */
	public boolean remove(Value v, Key k);
	
	
	
	
	/**
	 * Whether or not the given element is in the HashTable.
	 * @param key - K type. 
	 * @param element - T type.
	 * @return boolean
	 */
	public boolean isIn(Value v, Key k);
	
	/**
	 * Gets the size of the collection
	 * @return int - Size of the collection
	 */
	public int size();
	
	/**
	 * Puts all elements from the HashTable into a Collection&lt;T&gt; and returns it.
	 * @return Collection&lt;T&gt; - All elements.
	 */
	public Collection<Value> getAllElements();
	

	@Override
	public String toString();
	
}
