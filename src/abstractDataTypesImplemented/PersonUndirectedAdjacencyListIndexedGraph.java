package abstractDataTypesImplemented;

import java.util.ArrayList;

import abstractDataTypesPackage.GraphADT;
import abstractDataTypesPackage.HashTableADT;
import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.ElementNotFoundException;
/**
 *  This class is an implementation of GraphADT with the type Person. It's an undirected graph using adjacency list.
 *  Uses an {@link IntegerUndirectedAdjacencyListIndexedGraph} as base and assigns each {@link Person} an ID that symbolizes a vertex in the graph.
 * @author Borja Moralejo Tobajas
 *
 */
public class PersonUndirectedAdjacencyListIndexedGraph implements GraphADT<Person>{

	
	// Attributes
	
	private IntegerUndirectedAdjacencyListIndexedGraph graph;
	private ArrayList<Person> people;
	private ArrayList<HashTableADT<Person, Integer>> adjacencyList;
	
	/**
	 * Constructor of the class, initializes the variables
	 */
	public PersonUndirectedAdjacencyListIndexedGraph() {
		graph = new IntegerUndirectedAdjacencyListIndexedGraph();
		people = new ArrayList<Person>();
		adjacencyList = new ArrayList<HashTableADT<Person, Integer>>();
	}
	
	
	

	@Override
	public int getVertexNumber() { return graph.getVertexNumber(); }

	@Override
	public int getEdgeNumber() { return graph.getEdgeNumber(); }

	
	@Override
	public boolean addVertex(Person element) {
		boolean added = false;
		if(element.getGraphID() == -1) {
			int index = graph.getIndex();
			boolean last = graph.getMaxNumber() == index;
			if(graph.addVertex(index)) {
				element.setGraphID(index);
				added = true;
				if(last) {
					people.add(index, element);
					adjacencyList.add(index, new GenericArrayListHashTable<Person, Integer>());

				}else {
					people.set(index, element);
					adjacencyList.set(index, new GenericArrayListHashTable<Person, Integer>());

				}
			}
				
		}
		return added;
	}

	@Override
	public boolean removeVertex(Person element) {
		boolean removed = false;
		int index = element.getGraphID();
		if(index != -1) {
			if(graph.removeVertex(index)) {
				people.set(index, null);
				removed = true;
				element.setGraphID(-1);
			}
		}
		return removed;
	}

	@Override
	public boolean addEdge(Person element1, Person element2) {
		boolean added = false;
		if(element1.getGraphID() != -1 && element2.getGraphID() != -1) {
			if(graph.addEdge(element1.getGraphID(), element2.getGraphID())) {
				added = true;
				adjacencyList.get(element1.getGraphID()).put(element2, element2.getGraphID());
				adjacencyList.get(element2.getGraphID()).put(element1, element1.getGraphID());

			}
		}
		
		return added;
	}

	@Override
	public boolean removeEdge(Person element1, Person element2) {
		boolean removed = false;
		if(element1.getGraphID() != -1 && element2.getGraphID() != -1) {
			if(graph.removeEdge(element1.getGraphID(), element2.getGraphID())) {
				removed = true;
				adjacencyList.get(element2.getGraphID()).remove(element1, element1.getGraphID());
				adjacencyList.get(element2.getGraphID()).remove(element1, element1.getGraphID());

			}
			
		}
		
		return removed;
	}

	// Time before spatial
	@Override
	public boolean edgeTo(Person element1, Person to) {
		try {
			return adjacencyList.get(element1.getGraphID()).get(to.getGraphID()).equals(to) ? true : false;
		} catch (ElementNotFoundException | IndexOutOfBoundsException | NullPointerException e) {
			return false;
		}
	}
	
	// Spatial before time
	@Override
	public Iterable<Person> getAdjacentsOf(Person adjsOfT) {
		ArrayList<Person> list = null;
		int index = adjsOfT.getGraphID();
		if(index != -1) {
			list = new ArrayList<Person>();
			for(int adj : graph.getAdjacentsOf(index))
				list.add(people.get(adj));
		}
		return list;
	}

	
	
	
	@Override
	public Iterable<Person> getAllElements() {
		ArrayList<Person> verteces = new ArrayList<Person>();
		for(int index : graph.getAllElements())
			verteces.add(people.get(index));
		
		return verteces;
	}
	
	/**
	 * Accustomed to the project needs, it returns the string that the writer uses for printing out relationships
	 */
	@Override
	public String toString() {
		String ret = "";
		String id; 
		boolean[] printed = new boolean[graph.getMaxNumber()];
		for(Person person : getAllElements()) {
			printed[person.getGraphID()] = true;
			id = person.getAttribute(PersonAttributesEnum.ID)[0];
			for(Person other : getAdjacentsOf(person)) {
				if(!printed[other.getGraphID()]) {
					ret += id+","+other.getAttribute(PersonAttributesEnum.ID)[0]+"\n";
				}
			}
		}
		
		return ret;
	}

	

}
