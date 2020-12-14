package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
//		unlink((T)"Ane52");
		ret = root.getAllElements();
		return ret;
	}

	@Override
	public int getLinkNumber() {
		return count;
	}
	

	@Override
	public boolean unlink(T node) throws ElementNotFoundException{
		boolean ret = false;
		if(!node.equals(content)) {
			root.removeElement(node);
			count--;
			ret = true;
		}
		return ret;
	}
	
//	public void rootTest() {
//		System.out.println();
//		Collection <T> friends = root.getAllElements();
//		Iterator<T> it = friends.iterator();
//		while (it.hasNext())
//			System.out.println(it.next());
//		try {
//			unlink((T) "Peru57");
//		} catch (ElementNotFoundException e) {
//			System.out.println("NO DEVERÍA DE SALIR ESTO");
//		}
//		friends = root.getAllElements();
//		it = friends.iterator();
//		while (it.hasNext())
//			System.out.println(it.next());
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
