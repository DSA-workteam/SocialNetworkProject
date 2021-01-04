package abstractDataTypesPackage;

/**
 * Interface of the Graph abstract data type. This interface hold the basic methods that must be implemented.
 * @author Borja Moralejo Tobajas
 *
 * @param <T> Generic type
 */
public interface GraphADT<T> {

	
	
	
	/**
	 * Returns the current number of vertices in the graph.
	 * @return - Integer - Current number of vertices.
	 */
	public int getVertexNumber();
	
	/**
	 * Returns the current number of edges in the graph.
	 * @return - Integer - Current number of edges.
	 */
	public int getEdgeNumber();
	
	/**
	 * Tries to add the element to the graph.
	 * @param element - Element to add.
	 * @return boolean - returns true if it was added, false otherwise.
	 */
	public boolean addVertex(T element);
	
	/**
	 * Tries to remove the element from the graph.
	 * @param element - Element to remove, doesn't need to be in the graph, if it's not in the graph it will return false.
	 * @return boolean - return true if the element was removed, false otherwise. 
	 */
	public boolean removeVertex(T element);
	
	/**
	 * Tries to create an edge between element1 and element2. If one or both of the elements aren't in the graph, it will return false.
	 * @param element1 - Element to create an edge from.
	 * @param element2 - Element to create an edge to.
	 * @return boolean - returns true if the edge was created, false otherwise.
	 */
	public boolean addEdge(T element1, T element2);
	
	/**
	 * Tries to remove an edge between two given elements. If the edge doesn't exist, it returns false.
	 * @param element1 - Element to remove the edge
	 * @param element2 - Element to remove the edge
	 * @return boolean - return true if the edge was removed, false otherwise.
	 */
	public boolean removeEdge(T element1, T element2);
	
	/**
	 * Checks if two elements are connected by one edge.
	 * @param element1 - Origin element
	 * @param to - Destination element
	 * @return boolean - returns true if the elements are connected, false otherwise. 
	 */
	public boolean edgeTo(T element1, T to);
	
	/**
	 * Gets the adjacent elements of the given element
	 * @param adjsOfT - Element to check adjacent.
	 * @return {@link Iterable} of adjacents of element
	 */
	public Iterable<T> getAdjacentsOf(T adjsOfT);
	
	/**
	 * Gets all the vertices in the graph and returns it
	 * @return {@link Iterable} - All the vertices in the graph.
	 */
	public Iterable<T> getAllElements();
	
	
	
	
}
