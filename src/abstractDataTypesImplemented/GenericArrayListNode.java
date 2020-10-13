package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Iterator;

import adt.NodeADT;
import exceptions.ElementNotFoundException;

/**
 * 
 * @author Imanol Maraña Hurtado & Borja Moralejo Tobajas
 *
 * @param <T> Generic type
 */
public class GenericArrayListNode<T> implements NodeADT<T>{

	private T content;
	private int count;
	private ArrayList<NodeADT<T>> nodes;
	
	
	/**
	 * Main constructor of the class
	 * @param element Element of the node
	 */
	public GenericArrayListNode(T element) {
		content = element;
		count = 0;
		nodes = new ArrayList<NodeADT<T>>();
	}
	
	
	/**
	 * Constructor of the class without parameters
	 */
	public GenericArrayListNode() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public T getContent() {
		return content;
	}

	@Override
	public void link(NodeADT<T> node) {
		
		int i = 0;
		boolean found = false;
		// Checks if the length is 0 to take different ways to act
		if(count == 0) {
			found = true;
		}
		
		// Checks if the length is 1 to take different ways to act
		else if(count == 1) {
			if(!nodes.get(0).getContent().equals(node.getContent())) {
				System.out.println(nodes.get(0).getContent().toString().compareTo(node.getContent().toString()));
				if(0 < nodes.get(0).getContent().toString().compareTo(node.getContent().toString())) {
					found = true;
				}
				else {
					i = 1;
					found = true;
				}
				System.out.println(nodes.get(0).getContent().toString().compareTo(nodes.get(1).getContent().toString()));
			}
		}
		// If the length is 2 or bigger
		else {
			int min = 0;
			int max = count;
			i = count/2;
			
			// Checks if the difference between the possible options is 2 or lower, or if the element has already been found
			while(max - min > 2 && !found) {
				//Checks if the node is the same as the selected
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
				}
				// Checks whether the given node is bigger than this node
				else if( 0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
					min = i;
					i += (max-min)/2;
				}
				// Checks whether this node is bigger than the given node
				else {
					max = i;
					i -= (max-min)/2;
				}
				
			}
			
			// If the element wasn't in the list and the while has ended
			if(!found) {
				if(!nodes.get(i).getContent().equals(node.getContent())) {
					// Chacks if the correct position is the previous one
					if(0 < nodes.get(i-1).getContent().toString().compareTo(node.getContent().toString()) && 0 < nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
						i = i-1;
						found = true;
					}
					// Checks if the correct position is the next one
					else if(0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
						i = i+1;
						found = true;
					}
					// The current position is the good one
					else
						found = true;
				}
			}
			// The element was already in the list, so we don't have the good position to add it
			else
				found = false;
		}
		// Checks if the appropiate position has been found, then adds the element to that position and increases the number of links counter
		if (found) {
			nodes.add(i, node);
			count++;
		}
			
	}
	
	@Override
	public T[] getLinkedNodes() {
		T[] nodesInfo = (T[]) new String[getLinkNumber()];
		
		//Goes through all the linked nodes to add them to the returning value
		for(int i = 0; i < getLinkNumber(); i++) {
			nodesInfo[i] = nodes.get(i).getContent();
		}
		
		return nodesInfo;
	}

	@Override
	public int getLinkNumber() {
		return count;
	}
	

	@Override
	public void unlink(NodeADT<T> node) throws ElementNotFoundException{
		
		boolean found = false;
		int i = 0;
		
		// Checks if the length is 1 to take different ways to act
		if(count == 1) {
			if(nodes.get(0).getContent().equals(node.getContent())) {
				found = true;
			}
		}
		// Checks if the length is higher than one, not being neither 0 nor 1 so we don't take any extra measures
		else if(count > 1) {
			int min = 0;
			int max = count;
			i = count/2;
			// While the different between the 2 possible positions is bigger than 2 or the element has already been found 
			while(max - min > 2 && !found) {
				// The element we were searching for is found in the position i
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
				}
				// The element we are searching for is bigger than the element in the position i
				else if(0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
					min = i;
					i += (max-min)/2;
				}
				// The element we are searching for is smaller than the element in the position i
				else {
					max = i;
					i -= (max-min)/2;
				}
			}
			// Checks whether the element is still not found and searches between the remaining possible options 
			if(!found)
				// The element we were searching for is found in the position i
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
				}
				// The element we were searching for is found in the position i - 1
				else if(nodes.get(i-1).getContent().equals(node.getContent())){
					found = true;
					i = i-1;
				}
				// The element we were searching for is found in the position i + 1
				else if(nodes.get(i+1).getContent().equals(node.getContent())) {
					found = true;
					i = i+1;
				}
		}
		
		// If the element is still not found, it throws an error
		if (!found)
			throw new ElementNotFoundException("That node is not between the linked nodes");
		// If the element is found, it's removed from the list and lowered the number of link counter
		else
			nodes.remove(i);
			count--;
		
	}

}
