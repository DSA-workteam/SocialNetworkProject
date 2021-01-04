package dataStructuresImplemented;

import java.util.ArrayList;

import abstractDataTypesImplemented.GenericArrayListHashTable;
import abstractDataTypesPackage.GraphADT;
import abstractDataTypesPackage.HashTableADT;
import comparator.Quicksort;
import exceptions.ElementNotFoundException;

public class GraphFunctions {
	// Graph that is working on
	private GraphADT<Integer> graph;
	
	// Variables used in retrieveCliques
		private HashTableADT<Integer[], String> solutions;
		private ArrayList<Integer> friendsFromRoot;
		private int n, element;
		
	
	public GraphFunctions(GraphADT<Integer> graph) {
		this.graph = graph;
	}
	
	public void DFS(){
		
	}
	
	public void BFS() {
		
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
