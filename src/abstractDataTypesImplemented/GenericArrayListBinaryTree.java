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
 * @param <T> Element. T type must extend ComparableT
 */
public class GenericArrayListBinaryTree<T extends Comparable<T>> implements BinaryTreeADT<T>{

	

	private GenericArrayListBinaryTree<T> left,right;
	private T element;
	

	public GenericArrayListBinaryTree(){
		left = null;
		right = null;
		element = null;
	}
	
	
	public GenericArrayListBinaryTree(T element){
		left = null;
		right = null;
		this.element = element;
	}
	
	
	
	@Override
	public Collection<T> getAllElemets(GenericArrayListBinaryTree<T> tree) throws ElementNotFoundException
	{
		Collection<T> ret = new ArrayList<T>();
		inOrder(tree, ret);
		return ret;
	}
	
	
	
	/**
	 * Getter for the GenericArrayListBinaryTree<T> type parameter Left.
	 * @return Left
	 */
	public GenericArrayListBinaryTree<T> getLeft(){
		return left;
	}
	
	
	
	/**
	 * Getter for the GenericArrayListBinaryTree<T> type parameter Right.
	 * @return Right
	 */
	public GenericArrayListBinaryTree<T> getRight(){
		return right;
	}
	
	
	
	@Override
	public T getKeyElement() {
		return element;
	}

	
	
	@Override
	public boolean addElement(T element) {
		boolean added = true;
		int comparation = this.element.compareTo(element);
		if(comparation == 0) 
			added = false;
		else if(comparation > 0) {
			if(left != null)
				added = left.addElement(element);
			else {
				left = new GenericArrayListBinaryTree<T>(element);
			}
		}else {
			if(right != null)
				added = right.addElement(element);
			else {
				right = new GenericArrayListBinaryTree<T>(element);
			}
		}
		return added;
		
	}

	
	
	@Override
	public int depthOfElement(T element) {
		int comparation = this.element.compareTo(element);
		int ret = 0;
		if(comparation == 0) {
			ret = 1;
		}
		else 
			if(left != null)
				if(comparation > 0) {
					ret = 1 + left.depthOfElement(element);
				}
			else if (right != null)
				if(comparation < 0)
					ret = 1 + right.depthOfElement(element);
		
		return ret;
	}


	@Override
	public void removeElement(T remElement) throws ElementNotFoundException{
		// TODO
		GenericArrayListBinaryTree<T> aux1;
		GenericArrayListBinaryTree<T> aux2;
		boolean tracker1 = left == null;
		boolean tracker2 = right == null;
		if(right == null && left == null)
			throw new ElementNotFoundException("There's no node with such key");
		if(right != null) {
			T tracker4 = right.element;
			int moreComparation = right.element.compareTo(remElement);
			if (moreComparation == 0) {
				if (right.left == null && right.right == null)
					right = null;
				else if(right.left == null)
					right = right.right;
				else if(right.right == null)
					right = right.left;
				else {
					aux1 = right.right;
					right = right.left;
					aux2 = right;
					while (aux2.right != null)
						aux2 = aux2.right;
					aux2.right = aux1;
				}
			}
			else if(moreComparation < 0)
				right.removeElement(remElement);
			else
				if(left != null)
					left.removeElement(remElement);
		}
		if(left != null) {
			T tracker3 = left.element;
			int lessComparation = left.element.compareTo(remElement);
			if(lessComparation == 0) {
				if (left.left == null && left.right == null)
					left = null;
				else if(left.left == null)
					left = left.right;
				else if(left.right == null)
					left = left.left;
				else {
					aux1 = left.left;
					left = left.right;
					aux2 = left;
					while (aux2.left != null)
						aux2 = aux2.left;
					aux2.left = aux1;
				}
			}
			else if(lessComparation > 0)
				left.removeElement(remElement);
			else
				if (right != null)
					right.removeElement(remElement);
		}
	}



	@Override
	public int longestDepth() {
		int dL = 0,dM = 0;
		if(left != null )
			dL = left.longestDepth();
		if(right != null)
			dM = right.longestDepth();
		
		return Math.max(dL, dM) + 1;
	}



	@Override
	public int shortestDepth() {
		int dL = 0,dM = 0;
		if(left != null )
			dL = left.shortestDepth();
		if(right != null)
			dM = right.shortestDepth();
		
		return Math.min(dL, dM) + 1;
	}


	
	@Override
	public Iterator<T> iteratorInOrder(GenericArrayListBinaryTree<T> tree) {
		Collection<T> list = new ArrayList<T>();
		inOrder(tree, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorPreOrder(GenericArrayListBinaryTree<T> tree) {
		Collection<T> list = new ArrayList<T>();
		preOrder(tree, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorPostOrder(GenericArrayListBinaryTree<T> tree) {
		Collection<T> list = new ArrayList<T>();
		postOrder(tree, list);
		
		return list.iterator();
	}



	@Override
	public Iterator<T> iteratorLevelOrder(GenericArrayListBinaryTree<T> tree) {
		ArrayList<T> list = new ArrayList<T>();
		ArrayList<GenericArrayListBinaryTree<T>> queue = new ArrayList<GenericArrayListBinaryTree<T>>();
		list.add(tree.element);
		queue.add(tree);
		
		while (queue.size() > 0) {
			if(queue.get(0).left != null)
				queue.add(queue.get(0).left);
			if(queue.get(0).right != null)
				queue.add(queue.get(0).right);
			queue.remove(0);
			if (queue.size() != 0)
				list.add(queue.get(0).element);
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
			inOrder(node.left, list);
			list.add(node.element);
			inOrder(node.right, list);
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
