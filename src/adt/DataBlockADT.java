package adt;

import java.util.Collection;

public interface DataBlockADT<T,K extends Comparable<K>>{
	
	public K getKey();
	
	public Collection<T> getCollection();
	
	public void add(T element);
	
	public int remove(T element);
	
	public int size();
	
	
}
