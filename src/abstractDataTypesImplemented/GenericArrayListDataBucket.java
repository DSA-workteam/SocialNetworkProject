package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;

import abstractDataTypesPackage.DataBucketADT;

/**
 * Implementation of DataBucketADT made with arraylist
 * @author Borja Moralejo Tobajas
 *
 * @param <Value> Value type that databucket is going to store
 * @param <Key> Key that databucket is going to use to store or not the element
 */
public class GenericArrayListDataBucket<Value,Key extends Comparable<Key>> implements DataBucketADT<Value,Key>{

	
	private Key key;
	private ArrayList<Value> list;
	private int N;
	
	
	/**
	 * Constructor
	 * @param key - key that databucket is going to keep
	 */
	public GenericArrayListDataBucket(Key key) {
		N = 0;
		list = new ArrayList<Value>();
		this.key = key; 
	}
	
	@Override
	public Key getKey() {
		return key;
	}

	@Override
	public Collection<Value> getCollection() {
		return list;
	}

	@Override
	public void add(Value element) {
		if(!list.contains(element)) {
			list.add(element);
			N++;
		}
		
	}

	@Override
	public boolean remove(Value element) {
		boolean ret = false;
		if(list.remove(element)) {
			N--;
			ret = true;
		}
		return ret;
	}


	@Override
	public int size() {		
		return N;
	}

	@Override
	public String toString() {
		String ret = "DataBucket holding "+key.toString() +"\n";
		for(Value t: list)
			ret+= t.toString() + "\n";
			
		return ret;
	}
}
