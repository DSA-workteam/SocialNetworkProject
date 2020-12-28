package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import abstractDataTypesImplemented.IntegerUndirectedAdjacencyListIndexedGraph;
import abstractDataTypesPackage.GraphADT;

class IntegerGraphTesting {
	IntegerUndirectedAdjacencyListIndexedGraph graph;
	@BeforeEach
	void setupGraph() {
		graph = new IntegerUndirectedAdjacencyListIndexedGraph();
	}
	@DisplayName("Managing Vertices") 
	@Nested
	class manageVertex {	
		
		
			
			@Test
			@DisplayName("Adding Vertices")
			 void addingVertices() {
				// Checking that graph is empty
				assertEquals(0, graph.getVertexNumber());
				// Can't add manually in order if lower elements haven't been added yet
				for(int i = 1; i < 10;i++)
					assertFalse(graph.addVertex(i));
				// Adding elements with getIndex()
				for(int i = 0; i < 10; i++)
					assertTrue(graph.addVertex(graph.getIndex()));
				
				// Element is already in the graph
				assertFalse(graph.addVertex(0));
				// It has 10 elements
				assertEquals(10, graph.getVertexNumber());
				
				
				// Removed some elements
				assertTrue(graph.removeVertex(7));
				assertTrue(graph.removeVertex(3));
				assertTrue(graph.removeVertex(5));

				// try adding them again with another order, only removed order will work, so only 7 should assert true
				assertFalse(graph.addVertex(3));
				assertFalse(graph.addVertex(5));
				assertTrue(graph.addVertex(7));
				
				// Now getIndex() should return 3 and then 5, and then 10 after adding each element
				assertEquals(3, graph.getIndex());
				assertTrue(graph.addVertex(3));
				assertEquals(5, graph.getIndex());
				assertTrue(graph.addVertex(5));
				assertEquals(10, graph.getIndex());
				assertTrue(graph.addVertex(10));
				
				
			
			
			
		}
		
		
			
			@Test
			@DisplayName("Removing Vertices")
			 void removingVertices() {
				// Empty case
				assertFalse(graph.removeVertex(0));
				
				// Adding elements
				for(int i = 0; i < 10; i++)
					assertTrue(graph.addVertex(i));
				// Check for number of vertices
				assertEquals(10, graph.getVertexNumber());
				
				// Can't add this elements because already it's on the graph
				assertFalse(graph.addVertex(0));

				// Element removed
				assertTrue(graph.removeVertex(0));
				// Checked that number of vertices has decreased
				assertEquals(9, graph.getVertexNumber());

				
				// Now that the element is removed, it should be able to add it
				assertTrue(graph.addVertex(0));
				assertEquals(10, graph.getVertexNumber());
				
				// Remove more elements
				for(int i = 0; i < 3; i++)
					assertTrue(graph.removeVertex(i));
				
				assertEquals(7, graph.getVertexNumber());
				
				
				
				ArrayList<Integer> elements = new ArrayList<Integer>();
				for(int i = 0; i < 10; i++)
					elements.add(i);
				// It doesn't have the base elements
				assertFalse(graph.getAllElements().equals(elements));
				
				
				
				elements = new ArrayList<Integer>();	
				for(int i = 3; i < 10; i++)
					elements.add(i);
				// It has the elements it's supposed to have
				assertTrue( graph.getAllElements().equals(elements));
				
				
				// Adding back 3 removed elements
				for(int i = 1; i <= 3; i++)
					assertTrue(graph.addVertex(graph.getIndex()));
					
				elements = new ArrayList<Integer>();	
				for(int i = 0; i < 10; i++)
					elements.add(i);
				// It has the base elements
				assertTrue(graph.getAllElements().equals(elements));

			}
			
		
			
			
		
		
	}
	
	@DisplayName("Managing Edges") 
	@Nested
	class manageEdge {
		
		@Test
		@DisplayName("Adding edges")
		void addingEdges() {
			
			// Trying to add edges without vertices in the graph
				assertFalse(graph.addEdge(0, 1));
			
			// Adding vertices
			for(int i = 0; i < 10; i++)
				assertTrue(graph.addVertex(graph.getIndex()));
			
			// Checking variable
			assertEquals(0, graph.getEdgeNumber());
			// Adding edge between 0 and 1
			assertTrue(graph.addEdge(0, 1));
			// Number of edges increased by one
			assertEquals(1, graph.getEdgeNumber());
			
			// Now testing if the adjacency is undirected, so one should have an edge to 0
			ArrayList<Integer> adjacentsOfZero = new ArrayList<Integer>();
			ArrayList<Integer> adjacentsOfOne = new ArrayList<Integer>();
			
			adjacentsOfZero.add(1);
			adjacentsOfOne.add(0);
			assertEquals(adjacentsOfZero, graph.getAdjacentsOf(0));
			assertEquals(adjacentsOfOne, graph.getAdjacentsOf(1));
			
			// Can't add parallel edges, not usefull in our project
			assertFalse(graph.addEdge(0, 1));
			
			// Add a bunch of edges
			for(int i = 2; i < 10; i++) {
				assertTrue(graph.addEdge(0, i));
				adjacentsOfZero.add(i);
			}
			assertEquals(9, graph.getEdgeNumber());

			// Check if adjacents are correct
			assertEquals(adjacentsOfZero, graph.getAdjacentsOf(0));

			// Self loops not usefull in our project
			assertFalse(graph.addEdge(0, 0));
			
			// Adding more elements to test
			for(int i = 0; i < 10; i++)
				assertTrue(graph.addVertex(graph.getIndex()));
			
			// Adding edge to one of the new vertices
			assertTrue(graph.addEdge(0, 18));
			
			adjacentsOfZero.add(18);
			assertEquals(adjacentsOfZero, graph.getAdjacentsOf(0));

			
			
			
		}
		
		
		
		@Test
		@DisplayName("Removing edges")
		void removingEdges() {
			
			// Removing with an empty graph
			assertFalse(graph.removeEdge(0, 1));

			
			// Adding vertices
				for(int i = 0; i < 10; i++)
					assertTrue(graph.addVertex(graph.getIndex()));
						
			// Checking variable
				assertEquals(0, graph.getEdgeNumber());
				
			ArrayList<Integer> adjacentsOfZero = new ArrayList<Integer>();

				
			// Add a bunch of edges
			for(int i = 1; i < 10; i++) {
				assertTrue(graph.addEdge(0, i));
				adjacentsOfZero.add(i);
			}
			
			
			assertEquals(9, graph.getEdgeNumber());

			// Remove edge from 0 to 5
			assertTrue(graph.removeEdge(0, 5));
			assertFalse(graph.removeEdge(0, 5));
			assertEquals(8, graph.getEdgeNumber());

			// Add it again and try from 5 to 0
			assertTrue(graph.addEdge(0, 5));
			assertTrue(graph.removeEdge(5, 0));

			
			// Add again edge 0-5
			assertTrue(graph.addEdge(5, 0));
			assertEquals(9, graph.getEdgeNumber());

			// Remove vertex 5, so edge 0-5 should not exist anymore
			assertTrue(graph.removeVertex(5));
			adjacentsOfZero.remove((Integer) 5);
			
			// Check if the edge is removed from the adjacency list of 0 
			assertEquals(adjacentsOfZero, graph.getAdjacentsOf(0));
			assertEquals(8, graph.getEdgeNumber());

			// Vertex is not in so this shouldn't add more edges
			assertFalse(graph.addEdge(0, 5));
			assertEquals(8, graph.getEdgeNumber());

			
			
				
		}
		
	}
	
	
	
	
}
