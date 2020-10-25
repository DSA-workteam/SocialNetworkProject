package abstractDataTypesPackage;

import java.util.Collection;

/**
 * This abstract data type is an congregation of Value type elements which share a Key type key. Its mainly use is inside hash maps.
 * @author Borja Moralejo Tobajas
 * 
 * @param <Value> Elements that are going to share the same Key type key
 * @param <Key> Key type 
 */
public interface DataBucketADT<Value,Key extends Comparable<Key>>{
	
	/**
	 * Gets the K type key used in the Data bucket
	 * @return K - Key
	 */
	public Key getKey();
	
	/**
	 * Gets the collection that have the same key
	 * @return Collection&lt;T&gt; - All T type elements that share the same key
	 */
	public Collection<Value> getCollection();
	
	/**
	 * Given element it's added into the Collection&lt;T&gt; but you must need to compare their keys before adding it because this doens't do that
	 * @param element - T to add
	 */
	public void add(Value element);
	
	/**
	 * Given a T type element tries to remove it and returns if it's removed or not
	 * @param element - T element to remove
	 * @return boolean - whether is or not removed from the Collection&lt;T&gt;
	 */
	public boolean remove(Value element);
	
	
	/**
	 * Returns size of the Collection&lt;T&gt;
	 * @return int - size of Collection&lt;T&gt;
	 */
	public int size();
	
	@Override
	public String toString() ;
}
