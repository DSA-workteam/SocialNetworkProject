package adt;

import java.util.Collection;

/**
 * 
 * @author Borja Moralejo Tobajas
 * This abstract data type is an congregation of T type elements which share a K type key
 * @param <T> Elements that are going to share the same K type key
 * @param <K> Key type 
 */
public interface DataBlockADT<T,K extends Comparable<K>>{
	
	/**
	 * Gets the K type key used in the DataBlock
	 * @return K - Key
	 */
	public K getKey();
	
	/**
	 * Gets the collection that have the same key
	 * @return Collection<T> - All T type elements that share the same key
	 */
	public Collection<T> getCollection();
	
	/**
	 * Given element it's added into the Collection<T> but you must need to compare their keys before adding it because this doens't do that
	 * @param T - element to add
	 */
	public void add(T element);
	
	/**
	 * Given a T type element tries to remove it and returns if it's removed or not
	 * @param T - element to remove
	 * @return boolean - whether is or not removed from the Collection<T>
	 */
	public boolean remove(T element);
	
	
	/**
	 * Returns size of the Collection<T>
	 * @return int - size of Collection<T>
	 */
	public int size();
	
	
}
