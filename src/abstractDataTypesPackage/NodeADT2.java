package abstractDataTypesPackage;

import java.util.Collection;

import exceptions.*;

/**
 * This is a node abstract data type, this will have connections to other nodes.
 * @author Imanol Maraña Hurtado and Borja Moralejo Tobajas
 * 
 * @param <T> - Generic type
 */
public interface NodeADT2<T extends Comparable<T> > {

	
	/**
	 * Gets the content inside the node
	 * @return returns type T element which is contained in the node
	 */
	public T getContent();
	
	
	/**
	 * Creates a link between given node and current node in only one direction	
	 * @param node - T Link to target
	 */
	public void link(T node);
	
	
	/**
	 * Gets all the nodes linked to this node
	 * @return Collection with the linked nodes
	 */
	public Collection<T> getLinkedNodes();
	
	
	/**
	 * Gets the number of current links that the node has
	 * @return int - Number of links
	 */
	public int getLinkNumber();
	
	
	/**
	 * Unlinks with given node
	 * @param node - NodeADT&lt;T&gt; to unlink to
	 * @throws ElementNotFoundException - Unlink element was not found
	 */
	public void unlink(T node) throws ElementNotFoundException;
	
	public void rootTest();
	
}
