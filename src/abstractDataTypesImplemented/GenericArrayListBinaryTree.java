package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.BinaryTreeADT;
import exceptions.ElementNotFoundException;
/**
 * Implementation made with ArrayList of BinaryTreeADT.
 * @author Borja Moralejo Tobajas & Imanol Maraña Hurtado
 *
 * @param <T> Element.
 * @param <K> Key. K type must extend ComparableK
 */
public class GenericArrayListBinaryTree< T, K extends Comparable<K> >  implements BinaryTreeADT<T, K>{

	
	public GenericArrayListBinaryTree<T, K> less,more,root;
	private K key;
	private ArrayList<T> elements;
	private int N;
	
	public K getKey() {return key;}
	public GenericArrayListBinaryTree(T element, K key){
		elements = new ArrayList<T>();
		less = null;
		more = null;
		root = this;
		this.key = key;
		elements.add(element);
		N = 0;
	}

	
	
	@Override
	public Collection<T> getElemets(K searchKey) {
		int comparation = key.compareTo(searchKey);
		Collection<T> ret = null;
		if(comparation == 0) {
			ret = elements;
		}
		else 
			if(less != null)
				if(comparation > 0) {
					ret = less.getElemets(searchKey);
				}
			else if (more != null)
				if(comparation < 0)
					ret = more.getElemets(searchKey);
		
		return ret;
	}

	
	
	@Override
	public boolean addElement(T element, K addKey) {
		boolean added = true;
		
		int comparation = key.compareTo(addKey);
		if(comparation == 0) {
			elements.add(element);
			added = false;
		}else if(comparation > 0) {
			if(less != null)
				added = less.addElement(element,addKey);
			else {
				less = new GenericArrayListBinaryTree<T, K>(element, addKey);
				N++;
			}
		}else {
			if(more != null)
				added = more.addElement(element, addKey);
			else {
				more = new GenericArrayListBinaryTree<T, K>(element, addKey);
				N++;
			}
		}
		return added;
		
	}

	@Override
	public int numberOfElements() {
		return N;
	}
	
	
	@Override
	public int depth(K key) {
		int comparation = key.compareTo(key);
		int ret = 0;
		if(comparation == 0) {
			ret = 1;
		}
		else 
			if(less != null)
				if(comparation > 0) {
					ret = 1 + less.depth(key);
				}
			else if (more != null)
				if(comparation < 0)
					ret = 1 + more.depth(key);
		
		return ret;
	}


	@Override
	public void removeElement(K remKey) throws ElementNotFoundException{
		// TODO
		int lessComparation = less.key.compareTo(remKey);
		int moreComparation = more.key.compareTo(remKey);
		GenericArrayListBinaryTree<T, K> aux1;
		GenericArrayListBinaryTree<T, K> aux2;
		if(lessComparation == 0) {
			if (less.less == null && less.more == null)
				less = null;
			else if(less.less == null)
				less = less.more;
			else if(less.more == null)
				less = less.less;
			else {
				aux1 = less.less;
				less = less.more;
				aux2 = less;
				while (aux2.less != null)
					aux2 = aux2.less;
				aux2.less = aux1;
			}
		}
		else if (moreComparation == 0) {
			if (more.less == null && more.more == null)
				more = null;
			else if(more.less == null)
				more= more.more;
			else if(more.more == null)
				more = more.less;
			else {
				aux1 = more.more;
				more = more.less;
				aux2 = more;
				while (aux2.more != null)
					aux2 = aux2.more;
				aux2.more = aux1;
			}
		}
		else {
			if(moreComparation < 0) {
				if(more != null)
					more.removeElement(remKey);
				else
					throw new ElementNotFoundException("There's no node with such key");
			}
			else {
				if(less != null)
					less.removeElement(remKey);
				else
					throw new ElementNotFoundException("There's no node with such key");
			}
		}
	}



	@Override
	public int longestDepth() {
		int dL = 0,dM = 0;
		if(less != null )
			dL = less.longestDepth();
		if(more != null)
			dM = more.longestDepth();
		
		return Math.max(dL, dM) + 1;
	}



	@Override
	public int shortestDepth() {
		int dL = 0,dM = 0;
		if(less != null )
			dL = less.shortestDepth();
		if(more != null)
			dM = more.shortestDepth();
		
		return Math.min(dL, dM) + 1;
	}



	
}
