package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import abstractDataTypesPackage.GraphADT;
import exceptions.ElementNotFoundException;

/**
 * This class is the implementation of GraphADT that uses Integers as the base type. 
 * The vertex is the element and it's index is itself. That's why it's an indexed graph, this is not using references, it's using values for retrieving the vertex.
 * The graph is undirected and it uses an adjacency list.
 * @author Borja Moralejo Tobajas
 *
 */
public class IntegerUndirectedAdjacencyListIndexedGraph implements GraphADT<Integer>{
	
	// Attributes
	private int maxVertexIndex = 0, nOfVertices = 0;
	private int nOfEdges = 0;
	
	
	private ArrayList<LinkedBinarySearchTree<Integer>> adjacencyList; //It needs to be arraylist of bst<Interger>
	private LinkedList<Integer> vacantIntegers;
	private ArrayList<Boolean> isVacant; 
	
	/**
	 * Constructor of the class
	 */
	public IntegerUndirectedAdjacencyListIndexedGraph() {
		adjacencyList = new ArrayList<LinkedBinarySearchTree<Integer>>(10);
		vacantIntegers = new LinkedList<Integer>();
		isVacant = new ArrayList<Boolean>(10);
	}
	
	

	@Override
	public int getVertexNumber() { return nOfVertices; }

	@Override
	public int getEdgeNumber() { return nOfEdges; }

	/**
	 * Returns the maximum size that the list has achieved, this value is not going to drop down even if you delete elements.
	 * @return - int - Maximum size
	 */
	public int getMaxNumber() { return maxVertexIndex; }
	
	/**
	 * Returns the int that must be used as index.
	 * @return int - index to be used.
	 */
	public int getIndex() {
		int index = 0;
		if(vacantIntegers.isEmpty()) {
			index = maxVertexIndex;
		}else {
			 index = vacantIntegers.get(0);
			 
		}
		return index;
	}
	
	/**
	 * This method should be used with getIndex().
	 * @param element - Integer - Symbol element to add.
	 * @return boolean - Whether or not was added.
	 */
	@Override
	public boolean addVertex(Integer element) {
		boolean added = true;
		
		// Check if it can be added following an order
		if(element == getIndex()) { 
			nOfVertices+=1;
			if(element == maxVertexIndex){ // The vacant queue is empty, must take the last index
				adjacencyList.add(maxVertexIndex, new LinkedBinarySearchTree<Integer>());
				isVacant.add(maxVertexIndex, false);
				
				maxVertexIndex++;

			}else { // Is going to replace the place of a removed element
				
				adjacencyList.set(vacantIntegers.removeFirst(), new LinkedBinarySearchTree<Integer>());
				isVacant.set(element, false);
			}
		}else
			added = false;
		return added;
		
	}

	@Override
	public boolean removeVertex(Integer element) {
		boolean removed = false;
		if(element < maxVertexIndex)
			if(!isVacant.get(element)) {
				
				// Remove all edges of element
				for(int other : getAdjacentsOf(element)) {
					try {
						adjacencyList.get(other).removeElement(element);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
					nOfEdges--;
				}
				
				isVacant.set(element, true);				
				vacantIntegers.add(element);
				removed = true;
				nOfVertices-=1;
			}
		
		return removed;
	}
	
	
	@Override
	public boolean addEdge(Integer element1, Integer element2) {
		boolean added = false;
		if(areElementsValid(element1, element2)) {
				if(!adjacencyList.get(element1).contains(element2)) {
					adjacencyList.get(element1).addElement(element2);
					adjacencyList.get(element2).addElement(element1);
					added = true;
					nOfEdges++;
				}
				// else it has already the edge
			} // else one of them or both are not in the network
		return added;
		
	}

	@Override
	public boolean removeEdge(Integer element1, Integer element2) {
		boolean removed = false;
		if(areElementsValid(element1, element2)){
				if(adjacencyList.get(element1).contains(element2)) {
					try {
						adjacencyList.get(element1).removeElement(element2);
						adjacencyList.get(element2).removeElement(element1);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
					removed = true;
					nOfEdges--;
				}else {
					// Has no edge
				}
			}
		return removed;
	}

	@Override
	public Iterable<Integer> getAdjacentsOf(Integer adjsOfT) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Iterator<Integer> it;
		if(!isVacant.get(adjsOfT)) {
			it = adjacencyList.get(adjsOfT).iteratorInOrder();
			while(it.hasNext()) ret.add(it.next());
		}
		return ret;
	}

	
	
	@Override
	public Iterable<Integer> getAllElements() {
		ArrayList<Integer> elements = new ArrayList<Integer>(nOfVertices);
		for(int i = 0, j = 0; i < maxVertexIndex && j < nOfVertices;i++) {
			if(!isVacant.get(i)) {
				j++;
				elements.add(i);
			}
		}
		return elements;
	}

	
	/**
	 * Checks if the elements given are valid for the operation, it checks the range and equality.
	 * @param element1 - Integer - Element1.
	 * @param element2 - Integer - Element2.
	 * @return boolean - Whether or not the elements are valid.
	 */
	private boolean areElementsValid(Integer element1, Integer element2) {
		boolean ret = false;
		if(!element1.equals(element2) && element1 < maxVertexIndex && element2 < maxVertexIndex)
			if(!isVacant.get(element1) && !isVacant.get(element2)) {
				ret = true;
			}
		return ret;
	}
	
	@Override
	public boolean edgeTo(Integer element1, Integer to) {
		boolean ret = false;
		if(areElementsValid(element1, to))
			ret = adjacencyList.get(element1).contains(to);
			
		return ret;
	}

	

}
