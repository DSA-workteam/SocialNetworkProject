package adt;

public interface NodeADT<T> {

	
	/**
	 * Gets the content inside the node
	 * @return returns type T element which is contained in the node
	 */
	public T getContent();
	
	
	/**
	 * Creates a link between given node and current node in reciprocal mode	
	 * @param node - Link to target
	 */
	public void link(NodeADT<T> node);
	
	
	/**
	 * Gets the number of current links that the node has
	 * @return int - Number of links
	 */
	public int getLinkNumber();
	
	
	/**
	 * Unlinks with given node
	 * @param node - Node to unlink to
	 */
	public boolean unlink(NodeADT<T> node);
	
	public String toString();
}
