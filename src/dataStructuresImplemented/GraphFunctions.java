package dataStructuresImplemented;

import java.util.ArrayList;
import java.util.Iterator;

import abstractDataTypesImplemented.GenericArrayListHashTable;
import abstractDataTypesPackage.GraphADT;
import abstractDataTypesPackage.HashTableADT;
import comparator.Quicksort;
import exceptions.ElementNotFoundException;

/**
 * This class contains functions that are going to be used with a GraphADT of type Integer. 
 * Dataholder's PersonUndirectedAdjacencyListIndexedGraph has an IntegerUndirectedAdjacencyListIndexedGraph that is going to be used here.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 */
public class GraphFunctions {
	// Graph that is working on
	private GraphADT<Integer> graph;
	
	// Variables used in retrieveCliques
		private HashTableADT<Integer[], String> solutions;
		private ArrayList<Integer> friendsFromRoot;
		private int n, element;
		
	// variables used in DFS
		private ArrayList<Integer> largerDFS;
		private ArrayList<Integer> currentTry;
	
	public GraphFunctions(GraphADT<Integer> graph) {
		this.graph = graph;
	}
	
	/**
	 * Returns an array of Integers that is the shortest path between the two points at a maximum distance of 6.
	 * If there's no path or is too long, it returns null;
	 * @param source - Source Integer that the functions must start looking at
	 * @param objective - Objective Integer that is the search target.
	 * @return {@link Iterable} of Integer if there's path, null if there is not
	 */
	public Iterable<Integer> BFS(Integer source, Integer objective){
		ArrayList<Integer> ret = new ArrayList<Integer> ();
		if(!source.equals(objective)) { // Checks if the two elements are the same
			ArrayList<Integer> queue = new ArrayList<Integer>(); // Creates a queue and some other useful arrays for further use
			int vN = graph.getVertexNumber(); // As this value is used more than once, a new constant is generated
			boolean[] marked = new boolean[vN];
			int[] edgeTo = new int[vN];
			int[] distTo = new int[vN];
			for(int i = 0; i < vN; i++) // Sets all the values in the array to a preset value
				distTo[i] = Integer.MAX_VALUE;
			marked[source] = true; // Marks the source as visited as well as added to the queue and set its distance to 0
			distTo[source] = 0;
			queue.add(0, source);

			Integer actual; // Initializes some Variables
			Integer adj;
			Integer last = 0;
			int cont = 1;
			boolean done = false;
			Iterator<Integer> it;
			while(!queue.isEmpty() && cont < 6 && !done) { // Iterates over all the elements until it reaches the end of the queue or reaches the try limit or the final is found
				actual = queue.remove(0);
				it = graph.getAdjacentsOf(actual).iterator();
				while(it.hasNext() && !done) { // Iterates over all the adjacent unless the element to search is found
					adj = it.next();
					if(!marked[adj]) { // Makes some operations if the element wasn't previously checked
						edgeTo[adj] = actual;
						distTo[adj] = distTo[actual] + 1;
						if(adj.equals(objective)) { // Checks if we've found the element we were searching for
							done = true;
							last = adj;
						}
						else {
							marked[adj] = true;
							queue.add(0, adj);
						}
					}
				}
				cont++;
			}

			if(!done) // Checks if the element has been found, indicating whether there's a connection between them or not
				ret = null;
			else {
				int dist = distTo[last];
				actual = last;
				while(dist != 0) { // Goes over all the elements until reaching the source
					ret.add(0, actual);
					dist--;
					if(dist != 0)
						actual = edgeTo[actual];
				}
				ret.add(0, source);
			}
		}
		else
			ret.add(source); // Adds itself only if the element to search is the same as the source
		return ret;
	}
	
	
	
	/**
	 * Makes a DFS search between two elements and returns the longest path between them using backtracking
	 * @param source Initial node from which start the search
	 * @param objective Objective to reach
	 * @return An {@link Iterable} Integer type with the longest path to the objective. It will be null if there's no path and at least an element if there is. If the source and objective are the same, returns a single element
	 */
	public Iterable<Integer> DFS(Integer source, Integer objective) {
		largerDFS = null;
		if(!source.equals(objective)) { //Checks if the elements are equal.
			currentTry = new ArrayList<Integer>();
			currentTry.add(source);
			DFSWorker(source, objective); // Calls to the worker method to make the appropriate operations
			if(largerDFS != null) // Checks if the connection has been found, and in case it has been, adds the objective as the final step
				largerDFS.add(objective);
		}
		else {
			largerDFS = new ArrayList<Integer>();
			largerDFS.add(objective); //Adds itself to the final list
		}
		return largerDFS;
	}
	
	
	/**
	 * This function uses recursion to traverse the graph in depth first search and searches for the objective. It only returns the longest chain that relates source and objective.
	 * @param source - Integer element
	 * @param objective - Integer objective
	 */
	private void DFSWorker (Integer source, Integer objective){
		Iterator<Integer> adjacents = graph.getAdjacentsOf(source).iterator();
		Integer actual;
		while(adjacents.hasNext()) { // Iterates over all the adjacent of the source node
			actual = adjacents.next();
			if(actual.equals(objective)) { // Checks if the objective has been found
				if(largerDFS == null) { // Initializes the final array in case it wasn't
					largerDFS = new ArrayList<Integer>();
					Iterator<Integer> cloner = currentTry.iterator(); // Clones the elements from the current try into the final list
					while (cloner.hasNext())
						largerDFS.add(cloner.next());
				}
				else if(currentTry.size() > largerDFS.size()) { // Checks if the current try is longer than the previous one
					largerDFS = new ArrayList<Integer>();
					Iterator<Integer> cloner = currentTry.iterator(); // Clones the elements from the current try into the final list
					while (cloner.hasNext())
						largerDFS.add(cloner.next());
				}
			}
			else if(!currentTry.contains(actual)) { // Continues with the other adjacent in case that they weren't already visited 
				currentTry.add(actual);
				DFSWorker(actual, objective);
				currentTry.remove(actual);
			}
		}
	}
	
	
	
	/**
	 * Checks if the clique was already made and if it wasn't made, it is added into the solutions.
	 * @param solVector - Solution vector with the vertices that form a clique.
	 * @param k - int depth of the backtracking.
	 */
	private void processSolution(Integer[] solVector, int k) {

		// Makes a copy of the solution vector for the function Quicksort to prevent messing with the backtrack.
		Integer[] clone = new Integer[k+1]; 
		for(int i = 0; i< k+1; i++) {
			clone[i] = solVector[i];
		}
		Quicksort.sort(clone);
		
		// Merges the vertex symbols into an string that then it's used to check whether or not that clique was already formed.
		String string = "";
		for(int i = 0; i < k+1; i++)if(solVector[i] != -1) string+= clone[i]+ " ";
		
		try {
			solutions.get(string);
		}catch(ElementNotFoundException e) {
			solutions.put(clone, string);			
		}

	}
	
	
	/**
	 * Checks if the solution vector forms a clique and that clique is not inside a bigger clique.
	 * @param solVector - Solution vector with the Vertices used.
	 * @param k - Depth of the backtracking.
	 * @return boolean - whether or not is a solution.
	 */
	private boolean isSolution(Integer[] solVector, int k) {
		boolean ret = false;
		
		// If the clique has more than 4 friends
		if(k >= 3) { 
			int[] candidates = new int[n - k+1];
			if(constructCandidates(solVector, k, candidates)[0] == -1) { // If it can generate more options from the actual group, it's a clique
				ret = true;
			}
		}
		
		
		return ret;
	}
	
	
	/**
	 * Returns the vertices that can be used to form a clique or continue forming a clique.
	 * @param solVector - Solution vector with the vertices used.
	 * @param k - Depth of the backtracking.
	 * @param candidates - empty array
	 * @return candidates - could use candidates parameter as return.
	 */
	private int[] constructCandidates(Integer[] solVector, int k, int[] candidates) {
		
		boolean hasEdge = true;
		int cand;
		int t = 0;
		
		// Loops over all the friends remaining of the first vertex
		for(int i = 0; i < friendsFromRoot.size(); i++) {
			int j = 0;
			cand = friendsFromRoot.get(i);
			// Check that the potential candidate has edges with all the vertices in the solution vector
			while(hasEdge && j < k) {
				hasEdge = graph.edgeTo(cand, solVector[j]);
				j++;
			}
			if(hasEdge) {
				candidates[t++] = cand;
			}
		}
		// Leaves the empty symbols as -1 because 0 can be an id, could change that but rather not.
		for(int i = t; i <candidates.length;i++ )
			candidates[i] = -1;
		
		return candidates;
	}

	/**
	 * Skiena structure backtracking algorithm that retrieves the cliques
	 * @param solVector - Solution vector
	 * @param k - int depth of the backtrack
	 */
	private void retrieveCliques(Integer[] solVector,int k) {
		if(isSolution(solVector, k)) {
			processSolution(solVector, k);
		}else {
			k++;
			
			int[] candidates = new int[n - k+1];
			candidates = constructCandidates(solVector, k, candidates);
			for(int i = 0; i < n-k+1; i++)
				if(candidates[i] != -1) {
					friendsFromRoot.remove(((Integer)candidates[i]));
					solVector[k] = candidates[i];
					retrieveCliques(solVector, k);
					friendsFromRoot.add(candidates[i]);
				}
					
		}
		
	}
	
	
	
	/**
	 * Front end method that retrieves all the cliques on the graph.
	 * @return {@link Iterable} of Integer[] where each array is the collection of vertices that form a clique. 
	 */
	public Iterable<Integer[]> retrieveCliques() {
		// Initialize
		solutions = new GenericArrayListHashTable<Integer[], String>();
		
		
		// Iterate all the elements of the graph
		for(Integer i: graph.getAllElements()) {
			element = i;
			
			// Fills friends list
			friendsFromRoot = new ArrayList<Integer>();
			for(Integer friend: graph.getAdjacentsOf(i))
				friendsFromRoot.add(friend);
			
			// Maximum depth of the backtrack
			n = friendsFromRoot.size()+1;
			Integer[] solVector = new Integer[n];
			solVector[0] = element;
			
			// Starts the backtracking algorithm
			retrieveCliques(solVector, 0);
			
		}
		
		return solutions.getAllElements();
	}
	
}
