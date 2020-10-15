package adt;

import java.util.Collection;

import exceptions.*;

/**
 * This is a node abstract data type, this will have connections to other nodes.
 * @author Imanol Mara�a Hurtado & Borja Moralejo Tobajas
 * 
 * @param <T> - Generic type
 */
public interface NodeADT<T extends Comparable<T> > {

	
	/**
	 * Gets the content inside the node
	 * @return returns type T element which is contained in the node
	 */
	public T getContent();
	
	
	/**
	 * Creates a link between given node and current node in reciprocal mode	
	 * @param node - NodeADT<T>. Link to target
	 */
	public void link(NodeADT<T> node);
	
	
	/**
	 * Gets all the nodes linked to this node
	 * @return Collection with the linked nodes
	 */
	public Collection<NodeADT<T>> getLinkedNodes();
	
	
	/**
	 * Gets the number of current links that the node has
	 * @return int - Number of links
	 */
	public int getLinkNumber();
	
	
	/**
	 * Unlinks with given node
	 * @param node - NodeADT<T> to unlink to
	 * @throws ElementNotFoundException
	 */
	public void unlink(NodeADT<T> node) throws ElementNotFoundException;
	
}
