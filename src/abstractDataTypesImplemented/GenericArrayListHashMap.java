package abstractDataTypesImplemented;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.DataBucketADT;
import abstractDataTypesPackage.HashMapADT;
import exceptions.ElementNotFoundException;
import jdk.jfr.Experimental;
/**
 * Implementation of HashMapADT made with ArrayList. This uses an ArrayList inside of the hash map to fix collisions. Experimental version.
 * @author Borja Moralejo Tobajas
 *
 * @param <Value> type elements.
 * @param <Key> keys that are going to get hashed and then used as index.
 */
// TODO experimental or not final version because I don't know if this is the best way to implement a hashMap, look class documentation or "experimental" in the file.
@Experimental
public class GenericArrayListHashMap<Value,Key extends Comparable<Key>> implements HashMapADT<Value, Key>{

	private ArrayList<DataBucketADT<Value, Key>>[] hashMap;
	private final static int DEFAULT_SIZE = 128, DEFAULT_ARRAYLIST_SIZE = 4;
	private int mapSize = 0;
	private int N;
	
	/**
	 * Default constructor.
	 */
	public GenericArrayListHashMap() { this(DEFAULT_SIZE); }
	
	/**
	 * Constructor with only the desired map size.
	 * @param initial_size_map - int. Desired hash map size.
	 */
	public GenericArrayListHashMap(int initial_size_map) {
		this(initial_size_map,DEFAULT_ARRAYLIST_SIZE);
	}
	
	/**
	 * Main constructor of the class
	 * @param initial_size_map - int. Desired initial size of the hash map.
	 * @param initial_size_ArrayList - int. Desired initial size of array list that is inside the hash map.
	 */
	@SuppressWarnings("unchecked")
	public GenericArrayListHashMap(int initial_size_map, int initial_size_ArrayList) {
		hashMap =  (ArrayList<DataBucketADT<Value, Key>>[])(Array.newInstance(ArrayList.class, initial_size_map));
		mapSize = initial_size_map;
		N = 0;
		for(int i = 0; i < initial_size_map;i++) {
			hashMap[i] = new ArrayList<DataBucketADT<Value, Key>>(initial_size_ArrayList);
		}
	}
	
	@Override
	public void put(Value element, Key key) {		
		DataBucketADT<Value, Key> bucket;
		try {
			 bucket = getBucket(key);
			if(!getBucket(key).getCollection().contains(element)) {
				bucket.add(element);
				
			}
		}catch(ElementNotFoundException e) {
			bucket = new ArrayListDataBucket<Value, Key>(key);
			bucket.add(element);
			hashMap[hashCode(key)].add(bucket);
		}finally {
			N++;
			/*if(N>= mapSize*1.5) {
				expandHashMap();
			}
			*/
		}
		
		
	}
	
	@Override
	public void put(DataBucketADT<Value, Key> bucket) {
		if(!hashMap[hashCode(bucket.getKey())].contains(bucket))
			hashMap[hashCode(bucket.getKey())].add(bucket);
	}

	
	@Override
	public boolean isIn(Value element, Key key) {
		try {
			return getBucket(key).getCollection().contains(element);
		} catch (ElementNotFoundException e) {
			return false;
		}
	}
	
	@Override
	public boolean remove(Value element, Key key) {
		try {				 
			DataBucketADT<Value, Key> bucket = getBucket(key);
			boolean ret = bucket.remove(element);
			if(bucket.size() == 0)
				hashMap[hashCode(bucket.getKey())].remove(bucket);
			return ret;
		} catch (ElementNotFoundException e) {
			return false;
		}
		
	}
	
	@Override
	public Collection<Value> get(Key key) throws ElementNotFoundException {
		
		
		return getBucket(key).getCollection();
	}
	
	@Override
	public DataBucketADT<Value, Key> getBucket(Key key) throws ElementNotFoundException{
		DataBucketADT<Value, Key> bucket = null;
		boolean found = false;
		Iterator<DataBucketADT<Value, Key>> it = hashMap[hashCode(key)].iterator();
		while(!found && it.hasNext()) {
			bucket = it.next();
			if(bucket.getKey().equals(key))
				found = true;
		}
		
		if(!found)
			throw new ElementNotFoundException("element of key "+key);
		
		return bucket;
	}
	
	/**
	 * Get the index of the key in the HashMap
	 * @param key 
	 * @return int index from 0 to mapsize-1
	 */
	private int hashCode(Key key) {
		return Math.abs(key.hashCode() % mapSize);
	}
	
	@Override
	public int size() {
		
		return N;
	}

	
	@SuppressWarnings("unchecked")
	private void expandHashMap() {
		Iterator<DataBucketADT<Value, Key>> it= getAllBuckets().iterator();
		mapSize = (int) (mapSize*1.5);
		
		hashMap =  (ArrayList<DataBucketADT<Value, Key>>[])(Array.newInstance(ArrayList.class, mapSize));
		N = 0;
		for(int i = 0; i < mapSize;i++) {
			hashMap[i] = (ArrayList<DataBucketADT<Value, Key>>) new ArrayList<Value>(mapSize);
		}
		it.forEachRemaining(bucket -> hashMap[hashCode(bucket.getKey())].add(bucket));

	}
	
	
	@Override
	public Collection<Value> getAllElements() {
		ArrayList<Value> listOfAllElements = new ArrayList<Value>(N);
		getAllBuckets().iterator().forEachRemaining(bucket -> listOfAllElements.addAll(bucket.getCollection()));
		return listOfAllElements;
	}

	
	@Override
	public Iterator<Value> iterator() {		
		return getAllElements().iterator();
	}

	@Override
	public Collection<DataBucketADT<Value, Key>> getAllBuckets() {
		ArrayList<DataBucketADT<Value, Key>> listOfAllElements = new ArrayList<DataBucketADT<Value, Key>>();
		for(int i = 0; i < mapSize;i++) {
			for(DataBucketADT<Value, Key> element: hashMap[i])
				listOfAllElements.add(element);
		}
		return listOfAllElements;
	}

	@Override
	public DataBucketADT<Value, Key> createBucket(Value val, Key key){
		 DataBucketADT<Value, Key> ret = new ArrayListDataBucket<Value, Key>(key);
		 ret.add(val);
		 return ret ;
				
	}
	

}
