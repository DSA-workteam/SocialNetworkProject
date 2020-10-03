package abstractDataTypesImplemented;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

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
		hashMap =  (ArrayList<T>[])(Array.newInstance(ArrayList.class, initial_size_map));
		mapSize = initial_size_map;
		N = 0;
		for(int i = 0; i < initial_size_map;i++) {
			hashMap[i] = new ArrayList<T>(initial_size_ArrayList);
		}
	}
	
	@Override
	public void put(K key, T element) {		
		
		if(!isIn(key, element)) {
			hashMap[hashCode(key)].add(element);
			N++;
		}
		
	}
	@Override
	public boolean isIn(K key, T element) {
		return hashMap[hashCode(key)].contains(element);
	}
	
	@Override
	public boolean remove(K key, T element) {
			return hashMap[hashCode(key)].remove(element);
	}
	
	@Override
	public ArrayList<T> get(K key) {
		return hashMap[hashCode(key)];
	}

	private int hashCode(K key) {
		return Math.abs(key.hashCode() % mapSize);
	}
	@Override
	public int size() {
		
		return N;
	}

	@Override
	public ArrayList<T> getAllElements() {
		ArrayList<T> listOfAllElements = new ArrayList<T>();
		for(int i = 0; i < mapSize;i++) {
			for(T element: hashMap[i])
				listOfAllElements.add(element);
		}
		return listOfAllElements;
	}

}
