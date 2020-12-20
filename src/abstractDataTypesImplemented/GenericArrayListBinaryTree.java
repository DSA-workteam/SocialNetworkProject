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
	
	
	//Empty constructor to create and initialise the root of each binary tree element
	public GenericArrayListBinaryTree(){
		left = null;
		right = null;
		element = null;
	}
	
	
	//Main constructor of the class
	public GenericArrayListBinaryTree(T element){
		this.element = element;
		root = new GenericArrayListBinaryTree<T>();
		root.element = element;
	}
	
	
	
	@Override
	public Collection<T> getAllElements()
	{
		Collection<T> ret = new ArrayList<T>();
		inOrder(root, ret);
		return ret;
	}
	
	
	
	/**
	 * Returns the left node of the root
	 * @return Left
	 */
	public GenericArrayListBinaryTree<T> getLeft(){
		return root.left;
	}
	
	
	
	/**
	 * Returns the right node of the root
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
		int comparation = root.element.compareTo(element); //Makes the comparison for further checking
		if(comparation == 0) //Checks if the element is already added
			added = false;
		else if(comparation > 0) { //Checks if the element to add should be in the left
			if(root.left != null) //Checks if the left node is empty or not and calls recursively if it's not null
				added = root.left.addElement(element);
			else { //If it's null, it adds the element at the left of the current one
				root.left = new GenericArrayListBinaryTree<T>(element);
			}
			//This step is repeated with the right side
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
	public T removeElement(T remElement) throws ElementNotFoundException{
		T result = null;
		if(root != null) { //Checks if the root is null
			int comparation = remElement.compareTo(element); //Makes the initial comparison for further checking
			if(comparation == 0) { //Checks if the element to remove is the root itself
				result = element;
				root = rootReplacement(root); //Calls for the worker method specially made for this case
				element = root.element; 
			}
			else {
				GenericArrayListBinaryTree<T> current, parent = root;
				boolean found = false;
				if(comparation < 0) //Checks if the element to remove is in the left of the root
					current = root.left;
				else
					current = root.right;
				
				while(current != null && !found) { //Iterates until the element to remove is found or there are no more elements to compare with
					int nComparation = remElement.compareTo(current.element); //Makes internal comparisons for further checking
					if(nComparation == 0) { //Checks if we've reached the element to remove
						found = true;
						result = current.element;
						if(current == parent.left) { //Chooses the way to which call the worker method
							parent.root.left = replacement(current);
						}
						else {
							parent.root.right = replacement(current);
						}
					}
					else {
						parent = current;
						if(nComparation < 0) //Checks if the element to remove is at the left of the current one
							current = current.root.left;
						else
							current = current.root.right;
					}
				}
				
				if(!found) //Checks if the element has been removed successfully
					throw new ElementNotFoundException(remElement + "is not in the tree");
			}
		}
		return result;
	}
	
	
	/**
	 * Worker method of RemoveElement for a common replacement
	 * @param node Node  over which the replacement has to be made
	 * @return Returns the new node with which be replaced
	 */
	private GenericArrayListBinaryTree<T> replacement(GenericArrayListBinaryTree<T> node){
		GenericArrayListBinaryTree<T> result = null;
		if(node.root.left == null && node.root.right == null) //checks if the node has any son
			result = null;
		else if(node.root.left != null && node.root.right == null)
			result = node.root.left;
		else if(node.root.right != null && node.root.left == null)
			result = node.root.right;
		else {
			GenericArrayListBinaryTree<T> current = node.root.right;
			GenericArrayListBinaryTree<T> parent = node;
			while (current.root.left != null) { //Iterates until it finds a null left son
				parent = current;
				current = current.root.left;
			}
			if(node.root.right == current)
				current.root.left = node.root.left;
			else {
				parent.root.left = current.root.right;
				current.root.right = node.root.right;
				current.root.left = node.root.left;
			}
			result = current;
		}
		return result; //Returns the new tree with which replace the old one
	}
	
	
	/**
	 * Worker method of RemoveElement used only when the root is the element to eliminate. Works similar as the other worker mehtod
	 * @param node Root node over which make the remove operation
	 * @return Returns the new binary tree
	 */
	private GenericArrayListBinaryTree<T> rootReplacement(GenericArrayListBinaryTree<T> node){
		GenericArrayListBinaryTree<T> result = null;
		if(node.left == null && node.right == null)
			result = null;
		else if(node.left != null && node.right == null)
			result = node.left.root;
		else if(node.right != null && node.left == null)
			result = node.right.root;
		else {
			GenericArrayListBinaryTree<T> current = node.right.root;
			GenericArrayListBinaryTree<T> parent = node;
			while (current.left != null) {
				parent = current;
				current = current.left.root;
			}
			if(node.right.root == current)
				current.left.root = node.left.root;
			else {
				parent.left.root = current.right.root;
				current.right.root = node.right.root;
				current.left.root = node.left.root;
			}
			result = current;
		}
		return result;
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
