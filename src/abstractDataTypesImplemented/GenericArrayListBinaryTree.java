package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.BinaryTreeADT;
import exceptions.ElementNotFoundException;
/**
 * Implementation made with ArrayList of BinaryTreeADT.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 * @param <T> Element. T type must extend ComparableT
 */
public class GenericArrayListBinaryTree<T extends Comparable<T>> implements BinaryTreeADT<T>{

	

	private GenericArrayListBinaryTree<T> left,right;
	private T element;
	private GenericArrayListBinaryTree<T> root;
	

	public GenericArrayListBinaryTree(){
		left = null;
		right = null;
		element = null;
	}
	
	
	public GenericArrayListBinaryTree(T element){
		this.element = element;
		root = new GenericArrayListBinaryTree<T>();
		root.element = element;
	}
	
	
	
	@Override
	public Collection<T> getAllElemets() throws ElementNotFoundException
	{
		Collection<T> ret = new ArrayList<T>();
		inOrder(root, ret);
		return ret;
	}
	
	
	
	/**
	 * Getter for the GenericArrayListBinaryTree&lt;T&gt; type parameter Left.
	 * @return Left
	 */
	public GenericArrayListBinaryTree<T> getLeft(){
		return root.left;
	}
	
	
	
	/**
	 * Getter for the GenericArrayListBinaryTree&lt;T&gt; type parameter Right.
	 * @return Right
	 */
	public GenericArrayListBinaryTree<T> getRight(){
		return root.right;
	}
	
	
	
	@Override
	public T getKeyElement() {
		return root.element;
	}

	
	
	@Override
	public boolean addElement(T element) {
		boolean added = true;
		int comparation = root.element.compareTo(element);
		if(comparation == 0) 
			added = false;
		else if(comparation > 0) {
			if(root.left != null)
				added = root.left.addElement(element);
			else {
				root.left = new GenericArrayListBinaryTree<T>(element);
			}
		}else {
			if(root.right != null)
				added = root.right.addElement(element);
			else {
				root.right = new GenericArrayListBinaryTree<T>(element);
			}
		}
		return added;
		
	}

	
	
	@Override
	public int depthOfElement(T element) throws ElementNotFoundException{
		if(root == null)
			throw new ElementNotFoundException(element + " is not in the tree");
		int comparation = root.element.compareTo(element);
		int ret = 0;
		if(comparation == 0)
			ret = 1;
		else
			if(root != null) {
				if(root.left != null)
					if(comparation > 0)
						ret = 1 + root.left.depthOfElement(element);
				if (root.right != null)
					if(comparation < 0)
						ret = 1 + root.right.depthOfElement(element);
			}
		
		return ret;
	}


	@Override
	public void removeElement(T remElement) throws ElementNotFoundException{
		// TODO
		
	}



	@Override
	public int longestDepth() {
		int dL = 0,dM = 0;
		if(root != null) {
			if(root.left != null )
				dL = root.left.longestDepth();
			if(root.right != null)
				dM = root.right.longestDepth();
		}
		
		return Math.max(dL, dM) + 1;
	}



	@Override
	public int shortestDepth() {
		int dL = 0,dM = 0;
		if(root != null) {
			if(root.left != null )
				dL = root.left.shortestDepth();
			if(root.right != null)
				dM = root.right.shortestDepth();
		}
		
		return Math.min(dL, dM) + 1;
	}


	
	@Override
	public Iterator<T> iteratorInOrder() {
		Collection<T> list = new ArrayList<T>();
		inOrder(root, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorPreOrder() {
		Collection<T> list = new ArrayList<T>();
		preOrder(root, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorPostOrder() {
		Collection<T> list = new ArrayList<T>();
		postOrder(root, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorLevelOrder() {
		ArrayList<T> list = new ArrayList<T>();
		ArrayList<GenericArrayListBinaryTree<T>> queue = new ArrayList<GenericArrayListBinaryTree<T>>();
		list.add(root.element);
		queue.add(root);
		
		while (queue.size() > 0) {
			if(queue.get(0).root.left != null)
				queue.add(queue.get(0).root.left.root);
			if(queue.get(0).root.right != null)
				queue.add(queue.get(0).root.right.root);
			queue.remove(0);
			if (queue.size() != 0)
				list.add(queue.get(0).root.element);
		}
		
		return list.iterator();
	}
	

	
	/**
	 * Given a tree node, starting from it's root, and a list, may it be empty or not, adds the key element of each of the nodes that the given tree has to the list in in-order form 
	 * @param node tree node
	 * @param list list
	 */
	private void inOrder(GenericArrayListBinaryTree<T> node, Collection<T> list) {
		if(node != null) {
			if(node.left != null)
				inOrder(node.left.root, list);
			list.add(node.element);
			if(node.right != null)
				inOrder(node.right.root, list);
		}
	}
	
	
	
	/**
	 * Given a tree node, starting from it's root, and a list, may it be empty or not, adds the key element of each of the nodes that the given tree has to the list in pre-order form 
	 * @param node tree node
	 * @param list list
	 */
	private void preOrder(GenericArrayListBinaryTree<T> node, Collection<T> list) {
		if(node != null) {
			list.add(node.element);
			preOrder(node.left, list);
			preOrder(node.right, list);
		}
	}
	
	
	
	/**
	 * Given a tree node, starting from it's root, and a list, may it be empty or not, adds the key element of each of the nodes that the given tree has to the list in post-order form 
	 * @param node tree node
	 * @param list list
	 */
	private void postOrder(GenericArrayListBinaryTree<T> node, Collection<T> list) {
		if(node != null) {
			inOrder(node.left, list);
			inOrder(node.right, list);
			list.add(node.element);
		}
	}
}
