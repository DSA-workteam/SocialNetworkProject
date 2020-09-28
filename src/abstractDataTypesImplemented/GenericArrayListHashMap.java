package abstractDataTypesImplemented;

import java.util.ArrayList;

import adt.HashMapADT;

public class GenericArrayListHashMap<T,K> implements HashMapADT<T, K> {

	private ArrayList<T>[] hashMap;
	private final static int DEFAULT_SIZE = 128, DEFAULT_ARRAYLIST_SIZE = 16;
	private int mapSize = 0;
	private int N;
	public GenericArrayListHashMap() {
		
		this(DEFAULT_SIZE);
	
	}
	
	public GenericArrayListHashMap(int initial_size_map) {
		this(initial_size_map,DEFAULT_ARRAYLIST_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public GenericArrayListHashMap(int initial_size_map, int initial_size_ArrayList) {
		hashMap =  (ArrayList<T>[])(new Object[initial_size_map]);
		mapSize = initial_size_map;
		N = 0;
		for(int i = 0; i < initial_size_map;i++) {
			hashMap[i] = new ArrayList<T>(initial_size_ArrayList);
		}
	}
	
	@Override
	public void put(K key, T element) {		
		
		if(!isIn(key, element)) {
			hashMap[key.hashCode()% mapSize].add(element);
			N++;
		}
		
	}
	@Override
	public boolean isIn(K key, T element) {
		return hashMap[key.hashCode()% mapSize].contains(element);
	}
	
	@Override
	public boolean remove(K key, T element) {
			return hashMap[key.hashCode()% mapSize].remove(element);
	}
	
	@Override
	public ArrayList<T> get(K key) {
		return hashMap[key.hashCode() % mapSize];
	}

	@Override
	public int size() {
		
		return N;
	}

}
