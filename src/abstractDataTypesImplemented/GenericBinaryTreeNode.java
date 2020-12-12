package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;

import abstractDataTypesPackage.NodeADT2;
import exceptions.ElementNotFoundException;

public class GenericBinaryTreeNode<T extends Comparable<T>> implements NodeADT2<T>{
	private T content;
	private int count;
	private GenericArrayListBinaryTree<T> root;
	
	
	/**
	 * Main constructor of the class
	 * @param element Element of the node
	 */
	public GenericBinaryTreeNode(T element) {
		content = element;
		count = 0;
		root = new GenericArrayListBinaryTree<T>(element);
		
	}

	
	@Override
	public T getContent() {
		return content;
	}

	@Override
	public void link(T node) {
		root.addElement(node);
		count++;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> getLinkedNodes() {
		Collection<T> ret = new ArrayList<T>();
		try {
//			unlink((T)"Ane52");
			ret = root.getAllElemets();
		} catch (ElementNotFoundException e) {
			System.err.println("There is no elements in the root");
		}
		return ret;
	}

	@Override
	public int getLinkNumber() {
		return count;
	}
	

	@Override
	public void unlink(T node) throws ElementNotFoundException{
		root.removeElement(node);
		count--;
	}
	
//	public void rootTest() {
//		System.out.println();
//		
//		System.out.println();
//	}
	
//	@Override
//	public String toString() {
//		String ret = "";
//		Iterator<NodeADT<T>> it = nodes.iterator();
//		while(it.hasNext())
//			ret+= content.toString() +","+it.next().getContent().toString()+"\n";
//		return ret;
//	}

}
