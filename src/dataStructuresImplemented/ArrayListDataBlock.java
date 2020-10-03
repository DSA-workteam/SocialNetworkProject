package dataStructuresImplemented;

import java.util.ArrayList;
import java.util.Collection;

import adt.DataBlockADT;

public class ArrayListDataBlock<T,K extends Comparable<K>> implements DataBlockADT<T,K>{

	
	private K key;
	private ArrayList<T> list;
	private int N;
	
	
	
	public ArrayListDataBlock(K key) {
		N = 0;
		list = new ArrayList<T>();
		this.key = key; 
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public Collection<T> getCollection() {
		return list;
	}

	@Override
	public void add(T element) {
		if(!list.contains(element)) {
			list.add(element);
			N++;
		}
		
	}

	@Override
	public int remove(T element) {
		if(list.remove(element))
			N--;
		return N;
	}


	@Override
	public int size() {		
		return N;
	}

}