package adt;

import java.util.Collection;

/**
 * This abstract data type is an congregation of T type elements which share a K type key. Its mainly use is inside hash maps.
 * @author Borja Moralejo Tobajas
 * 
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
	 * @return Collection&lt;T&gt; - All T type elements that share the same key
	 */
	public Collection<T> getCollection();
	
	/**
	 * Given element it's added into the Collection&lt;T&gt; but you must need to compare their keys before adding it because this doens't do that
	 * @param element - T to add
	 */
	public void add(T element);
	
	/**
	 * Given a T type element tries to remove it and returns if it's removed or not
	 * @param element - T element to remove
	 * @return boolean - whether is or not removed from the Collection&lt;T&gt;
	 */
	public boolean remove(T element);
	
	
	/**
	 * Returns size of the Collection&lt;T&gt;
	 * @return int - size of Collection&lt;T&gt;
	 */
	public int size();
	
	
}
