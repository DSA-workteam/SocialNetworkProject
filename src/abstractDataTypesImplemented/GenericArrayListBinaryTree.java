package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;

import adt.BinaryTreeADT;

public class GenericArrayListBinaryTree< T, K extends Comparable<K> >  implements BinaryTreeADT<T, K>{

	
	private GenericArrayListBinaryTree<T, K> less,more;
	private K key;
	private ArrayList<T> elements;
	private int N;
	

	public GenericArrayListBinaryTree(){
		elements = new ArrayList<T>();
		N = 0;
	}
	public GenericArrayListBinaryTree(T element, K key){
		super();
		this.key = key;
		elements.add(element);
	}

	@Override
	public Collection<T> getElemets(K searchKey) {
		int comparation = key.compareTo(searchKey);
		Collection<T> ret = null;
		if(comparation == 0) {
			ret = elements;			
		}else if(comparation > 0) {
			ret = less.getElemets(searchKey);
		}else {
			ret = more.getElemets(searchKey);
		}
		return ret;
	}

	@Override
	public void addElement(T element, K addKey) {
		int comparation = key.compareTo(addKey);
		if(comparation == 0) {
			elements.add(element);
		}else if(comparation > 0) {
			if(less != null)
				less.addElement(element,addKey);
			else less = new GenericArrayListBinaryTree<T, K>();
		}else {
			if(more != null)
				more.addElement(element, addKey);
			else more = new GenericArrayListBinaryTree<T, K>();
		}
		
	}

	@Override
	public int size() {
		return N;
	}

	public int allTreeSize() {
		int ret = N;
		if(less != null)
			ret += less.allTreeSize();
		if(more != null)
			ret += more.allTreeSize();
		return ret;
	}
	
	
	@Override
	public int depth() {
		int dL = 0,dM = 0;
		if(less != null )
			dL = less.depth();
		if(more != null)
			dM = more.depth();
		
		return Math.max(dL, dM) + 1;
	 	
	}


	@Override
	public void removeElement(T element, K remKey) {
		int comparation = key.compareTo(remKey);
		if(comparation == 0) {
			elements.remove(element);
		}else if(comparation > 0) {
			if(less != null)
				less.removeElement(element,remKey);			
		}else {
			if(more != null)
				more.removeElement(element, remKey);
		}
		
	}

}
