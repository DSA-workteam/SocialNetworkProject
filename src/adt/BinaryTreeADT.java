package adt;

import java.util.Collection;

public interface BinaryTreeADT<T,K> {

	public Collection<T> getElemets(K searchKey);
	
	public void addElement(T element);
	
	public int size();
	
	public int depth();
	
	public void removeElement(T element);
	
}
