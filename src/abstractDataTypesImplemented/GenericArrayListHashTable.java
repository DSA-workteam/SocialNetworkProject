package abstractDataTypesImplemented;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.HashTableADT;
import exceptions.ElementNotFoundException;
/**
 * /**
 * Implementation of {@link HashTableADT} made with {@link ArrayList}
 * @author Borja Moralejo Tobajas
 *
 *
 * @param <Value>  - Value that stores the hashmap.
 * @param <Key> - key that is going to use to compare or get the elements.
 */
public class GenericArrayListHashTable<Value, Key extends Comparable<Key>> implements HashTableADT<Value,Key> {
	
	
	private ArrayList<Node>[] lookTable;
	private int N, size;
	private final static int DEFAULT_SIZE = 100;
	
	/**
	 * Basic element of the hash table
	 * @author Borja Moralejo Tobajas
	 *
	 */
	private class Node{
		public Value v;
		public Key k;
		
		public Node(Value v, Key k) {
			this.v = v;
			this.k = k;
		}
		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Node other = (Node) o;
			if(v.equals(other.v) && k.equals(other.k))
				return true;
			else return false;
		}
		
		
	}
	
	
	public GenericArrayListHashTable(){
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public GenericArrayListHashTable(int size){
		this.size = size;
		// Initialize the lookTable
		lookTable =  (ArrayList<Node>[])(Array.newInstance(ArrayList.class, size));
		N = 0;
		for(int i = 0; i < size;i++) {
			lookTable[i] = new ArrayList<Node>();
		}
		
	}
	
	
	@Override
	public void put(Value v, Key k) {
		
		if(!isIn(v, k)) {
			lookTable[hashCode(k)].add(new Node(v, k));
			N++;
		/*	if(N>= size*1.5) {
				expandLookTable();
			}
		*/ 
		}
		
	}

	@Override
	public Value get(Key key) throws ElementNotFoundException {
		boolean found = false;
		
		Iterator<Node> it = lookTable[hashCode(key)].iterator();
		Node node = null;
		while(!found && it.hasNext()) {
			node = it.next();
			if(key.equals(node.k)) {
				found = true;
			}
		}
		
		if(!found)
			throw new ElementNotFoundException("Element with key "+key);
			
		return node.v;
	}

	@Override
	public boolean remove(Value v, Key k) {		
		return lookTable[hashCode(k)].remove(new Node(v,k));
	}

	@Override
	public boolean isIn(Value v, Key k) {
		return lookTable[hashCode(k)].contains(new Node(v, k));
	}

	/**
	 * Get the index of the key in the HashTable
	 * @param key 
	 * @return int index from 0 to size-1
	 */
	private int hashCode(Key key) {
		return Math.abs(key.hashCode() % size);
	}
	@Override
	public int size() {		
		return N;
	}


	@Override
	public Collection<Value> getAllElements() {
		ArrayList<Value> list = new ArrayList<Value>(N);
		
		for(int i = 0; i < size;i++) {
			for(Node node: lookTable[i]) {
				list.add(node.v);
			}
		}
		return list;
	}
	
	
	@Override
	public Iterator<Value> iterator() {
		return getAllElements().iterator();		
	}
	
	/**
	 * Gets all the nodes of the hashTable
	 * @return
	 */
	private Collection<Node> getNodes(){
		ArrayList<Node> list = new ArrayList<Node>(N);
		for(int i = 0; i < size;i++) {
			for(Node node: lookTable[i]) {
				list.add(node);
			}
		}
		return list;
	}
	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void expandLookTable() {
		Iterator<Node> it= getNodes().iterator();
		size = (int) (size*1.5);
		lookTable = null;
		lookTable =  (ArrayList<Node>[])(Array.newInstance(ArrayList.class, size));
		N = 0;
		for(int i = 0; i < size;i++) {
			lookTable[i] = (ArrayList<Node>) new ArrayList<Node>(size);
		}
		it.forEachRemaining(node -> lookTable[hashCode(node.k)].add(node));
	}

}
