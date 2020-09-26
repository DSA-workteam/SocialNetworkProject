package adt;

import java.util.Collection;



public interface BinaryTreeADT< T, K extends Comparable<K> >  {

	public Collection<T> getElemets(K searchKey);
	
	public void addElement(T element, K addKey);
	
	public int size();
	
	public int allTreeSize();
	
	public int depth();
	
	public void removeElement(T element,K remKey);
	
}
