package abstractDataTypesImplemented;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import adt.HashMapADT;
import jdk.jfr.Experimental;
/**
 * Implementation of HashMapADT made with ArrayList. This uses an ArrayList inside of the hash map to fix collisions. Experimental version.
 * @author Borja Moralejo Tobajas
 *
 * @param <T> type elements.
 * @param <K> keys that are going to get hashed and then used as index.
 */
// TODO experimental or not final version because I don't know if this is the best way to implement a hashMap, look class documentation or "experimental" in the file.
@Experimental
public class GenericArrayListHashMap<T,K> implements HashMapADT<T, K>{

	private ArrayList<T>[] hashMap;
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

	/**
	 * Puts all elements from the HashMap into a Collection<T> and returns it.
	 * @return Collection<T> - All elements.
	 */
	private Collection<T> getAllElements() {
		ArrayList<T> listOfAllElements = new ArrayList<T>();
		for(int i = 0; i < mapSize;i++) {
			for(T element: hashMap[i])
				listOfAllElements.add(element);
		}
		return listOfAllElements;
	}

	
	@Override
	public Iterator<T> iterator() {		
		return getAllElements().iterator();
	}
	
	

}
