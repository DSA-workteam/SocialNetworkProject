package abstractDataTypesPackage;

import java.util.Collection;

import exceptions.ElementNotFoundException;

/**
 * This is the Hash Map abstract data type interface. The hash map gets an element with a key and stores it hashing the key, it transforms the key into an index.
 * @author Borja Moralejo Tobajas
 * 
 * @param <Value> Elements to store
 * @param <Key> Key
 */
public interface HashMapADT<Value, Key extends Comparable<Key>> extends Iterable<Value>{

	/**
	 * Adds the given element into the hash map.
	 * @param v - Element which is going to be added into the hash map.
	 * @param k - Key for storing element in hash map .
	 */
	public void put(Value v, Key k);
	
	/**
	 * Given a bucket puts the bucket in the hashmap
	 * @param bucket - {@link DataBucketADT}
	 */
	public void put(DataBucketADT<Value, Key> bucket);
	
	/**
	 * Gets element with given key.
	 * @param key - Searches element with key.
	 * @return Returns Value element binded with Key key.
	 * @throws ElementNotFoundException {@link ElementNotFoundException}
	 */
	public Collection<Value> get(Key key) throws ElementNotFoundException;
	
	
	/**
	 * Gets the bucket that has the given key
	 * @param key - Key
	 * @return {@link DataBucketADT}
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}
	 */
	public DataBucketADT<Value,Key> getBucket(Key key) throws ElementNotFoundException;
	
	/**
	 * Tries to remove the given element from the CollectionT and returns whether or not has removed it.
	 * @param k - Key type. Key to search with.
	 * @param v - Value type. Element that is going to be removed.
	 * @return boolean - Whether or not has been removed.
	 */
	public boolean remove(Value v, Key k);
	
	
	
	
	/**
	 * Whether or not the given element is in the HashMap.
	 * @param k - Key type. 
	 * @param v - Value type.
	 * @return boolean
	 */
	public boolean isIn(Value v, Key k);
	
	/**
	 * Gets the size of the collection
	 * @return int - Size of the collection
	 */
	public int size();
	
	/**
	 * Creates a databucket with the given value and key
	 * @param val - Value that the databucket is going to store
	 * @param key - Key element that the databucket is going to keep for keying
	 * @return {@link DataBucketADT} returns created databucked
	 */
	public DataBucketADT<Value, Key> createBucket(Value val, Key key);
	
	/**
	 * Puts all elements from the HashMap into a Collection&lt;Value&gt; and returns it.
	 * @return Collection&lt;Value&gt; - All elements.
	 */
	public Collection<Value> getAllElements();
	
	/**
	 * Puts all buckets from the HashMap into a Collection&lt;Value&gt; and returns it.
	 * @return Collection&lt;DataBucketADT&gt; - All buckets in the.
	 */
	public Collection<DataBucketADT<Value,Key>> getAllBuckets();
	
	@Override
	public String toString();
	
}
