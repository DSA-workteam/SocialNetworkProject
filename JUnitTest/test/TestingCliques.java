package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abstractDataTypesImplemented.IntegerUndirectedAdjacencyListIndexedGraph;
import dataStructuresImplemented.GraphFunctions;

class TestingCliques {

	private IntegerUndirectedAdjacencyListIndexedGraph graph;
	
	@BeforeEach
	void setup() {
		graph = new IntegerUndirectedAdjacencyListIndexedGraph();
		for(int i = 0; i < 10; i++)
			graph.addVertex(i);
	}
	
	@Test
	void testingCliqueFunction() {
		
		// Creating clique of 0, 1, 2, and 3
		for(int i = 0; i < 3; i++)
			for(int j = i+1; j < 4; j++)
				assertTrue(graph.addEdge(i, j));
		
		// Creating list to check clique
		ArrayList<Integer> clique1 = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++)
			clique1.add(i);
		
		
		GraphFunctions gf = new GraphFunctions(graph);
		
		// Check if clique1 and clique are equal
		for(Integer[] clique: gf.retrieveCliques())
			for(int i = 0; i < clique.length; i++)
			assertEquals(clique1.get(i), clique[i]);
		
		// Adding another clique that contains the same elements as the first clique minus one
		for(int i = 0; i < 3; i++)			
			assertTrue(graph.addEdge(i, 4));
		
		// Clique 2
		ArrayList<Integer> clique2 = new ArrayList<Integer>();
		clique2.add(0);
		clique2.add(1);
		clique2.add(2);
		clique2.add(4);
		
		// Creating list of different cliques
		ArrayList<ArrayList<Integer>> cliques = new ArrayList<ArrayList<Integer>>();
		cliques.add(clique1);
		cliques.add(clique2);
		
		int cliqueNumber = 0;
		// Checking if they are equal
		for(Integer[] clique: gf.retrieveCliques()) {
			for(int i = 0; i < clique.length; i++)
				assertEquals(cliques.get(cliqueNumber).get(i), clique[i]);
			cliqueNumber++;
		}
		
		// Merging two cliques, clique1 and clique2, so it should give this clique: 0, 1, 2, 3, 4
		assertTrue(graph.addEdge(3, 4));
		
		// Creating clique in a list that is supposed to equal to.
		ArrayList<Integer> cliqueM = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++)
			cliqueM.add(i);
		
		// Checking if they are equal
		for(Integer[] clique: gf.retrieveCliques())
			for(int i = 0; i < clique.length; i++)
				assertEquals(cliqueM.get(i), clique[i]);		
		}
		
		
	

}
