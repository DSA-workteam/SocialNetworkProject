package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.NodeADT;
import abstractDataTypesPackage.NodeADT2;
import abstractDataTypesImplemented.GenericArrayListBinaryTree;
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
	
	@Override
	public Collection<T> getLinkedNodes() {
		Collection<T> ret = new ArrayList<T>();
		try {
			ret = root.getAllElemets(root);
			root.removeElement((T) "Ane52");
			ret = root.getAllElemets(root);
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
	}
	
//	@Override
//	public String toString() {
//		String ret = "";
//		Iterator<NodeADT<T>> it = nodes.iterator();
//		while(it.hasNext())
//			ret+= content.toString() +","+it.next().getContent().toString()+"\n";
//		return ret;
//	}

}
