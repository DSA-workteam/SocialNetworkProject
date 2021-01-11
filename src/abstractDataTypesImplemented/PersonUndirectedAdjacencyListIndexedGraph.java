package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Iterator;

import abstractDataTypesPackage.GraphADT;
import abstractDataTypesPackage.HashTableADT;
import dataStructuresImplemented.GraphFunctions;
import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.ElementNotFoundException;
/**
 *  This class is an implementation of GraphADT with the type Person. It's an undirected graph using adjacency list.
 *  Uses an {@link IntegerUndirectedAdjacencyListIndexedGraph} as base and assigns each {@link Person} an ID that symbolizes a vertex in the graph.
 * @author Borja Moralejo Tobajas & Imanol Maraña Hurtado
 *
 */
public class PersonUndirectedAdjacencyListIndexedGraph implements GraphADT<Person>{

	
	// Attributes
	
	private IntegerUndirectedAdjacencyListIndexedGraph graph;
	private ArrayList<Person> people;
	private ArrayList<HashTableADT<Person, Integer>> adjacencyList;
	public GraphFunctions graphFunctions;

	/**
	 * Constructor of the class, initializes the variables
	 */
	public PersonUndirectedAdjacencyListIndexedGraph() {
		graph = new IntegerUndirectedAdjacencyListIndexedGraph();
		people = new ArrayList<Person>();
		adjacencyList = new ArrayList<HashTableADT<Person, Integer>>();
		graphFunctions = new GraphFunctions(graph);
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
	 * This function returns the collection of cliques of the graph.
	 * @return {@link Iterable} collection of arrays of Person objects that form a clique.
	 */
	public Iterable<Person[]> getCliques(){
		ArrayList<Person[]> ret = new ArrayList<Person[]>();
		Person[] cliquePerson;
		
		// Gets the cliques in symbols and transforms them into Person[]
		for(Integer[] clique : graphFunctions.retrieveCliques()){
			cliquePerson = new Person[clique.length];
			for(int i = 0; i < clique.length; i++) {
				cliquePerson[i] = people.get(clique[i]);
			}
			ret.add(cliquePerson);
		}
		
		
		
		return ret;
	}
	
	/**
	 * Returns an Iterable<Person> containing the shortest path between two people if they are at less than 6 steps. Returns null if anyone of them is not in the graph or if there wasn't a connection between them at a distance less than 6. Returns a single element if both of them are equal. The path returned is from person1 to person2
	 * @param person1
	 * @param person2
	 * @return
	 */
	public Iterable<Person> pathAtDistance6(Person person1, Person person2) {
		ArrayList<Person> ret = null;
		if(person1.getGraphID() != -1 && person2.getGraphID() != -1) { // Checks if both of them are in the graph or not
			Iterable<Integer> BFS = graphFunctions.BFS(person1.getGraphID(), person2.getGraphID()); // Calls to the method that makes the appropriate operations
			if(BFS != null) { // Checks if there has been found a connection between both of them and transforms the array of Integer into an array of Person
				ret = new ArrayList<Person>();
				Iterator<Integer> it = BFS.iterator();
				while(it.hasNext())
					ret.add(people.get(it.next()));
			}
		}
		return ret;
	}
	
	/**
	 * Returns an Iterable<Person> containing the longest path between two people. Returns null if anyone of them is not in the graph or if there wasn't a connection between them. Returns a single element if both of them are equal. The path returned is from person1 to person2
	 * @param person1 First person of the search
	 * @param person2 Second person of the search
	 * @return Iterable<Person> containing the longest path
	 */
	public Iterable<Person> largerConnectionBetween(Person person1, Person person2){
		ArrayList<Person> ret = null;
		if(person1.getGraphID() != -1 && person2.getGraphID() != -1) { // Checks if both of them are in the graph or not 
			Iterable<Integer> DFS = graphFunctions.DFS(person1.getGraphID(), person2.getGraphID()); // Calls to the method that makes the appropriate operations
			if(DFS != null) { // Checks if there has been found a connection between both of them and transforms the array of Integer into an array of Person
				ret = new ArrayList<Person>();
				Iterator<Integer> it = DFS.iterator();
				while(it.hasNext())
					ret.add(people.get(it.next()));
			}
		}
		return ret;
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
