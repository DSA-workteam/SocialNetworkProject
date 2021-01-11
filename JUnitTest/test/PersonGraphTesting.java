package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import abstractDataTypesImplemented.PersonUndirectedAdjacencyListIndexedGraph;
import abstractDataTypesPackage.GraphADT;
import dataStructuresImplemented.Person;
import exceptions.ImpulsoryAttributeRequiredException;

class PersonGraphTesting {
	GraphADT<Person> graph;
	Person p1, p2, p3;
	@BeforeEach
	void setupGraph() {
		graph = new PersonUndirectedAdjacencyListIndexedGraph();
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		try {
			p1 = new Person(data);
			data = "Pipo,PinPin,PonPon,4-2-2003,male,Los Angeles,San Francisco,San Francisco,Los Angeles,Pipo,G77371";
			p2 = new Person(data);	
			data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527";
			p3 = new Person(data);	
		} catch (ImpulsoryAttributeRequiredException e) {
			
		}	
		
	}
	@DisplayName("Managing Vertices") 
	@Nested
	class manageVertex {	
		
		
			
			@Test
			@DisplayName("Adding Vertices")
			 void addingVertices() {
				// Checking that graph is empty
				assertEquals(0, graph.getVertexNumber());
								
				// Adding elements to the graph
				assertTrue(graph.addVertex(p1));
				assertTrue(graph.addVertex(p2));
				assertTrue(graph.addVertex(p3));
				
				// Element is already in the graph
				assertFalse(graph.addVertex(p1));
				// It has 3 elements
				assertEquals(3, graph.getVertexNumber());
				
				
				// Removed some elements
				assertTrue(graph.removeVertex(p1));
				assertTrue(graph.removeVertex(p2));

				// try adding them again with another order, only removed order will work, so only 7 should assert true
				assertTrue(graph.addVertex(p2));
				assertFalse(graph.addVertex(p3));
				assertTrue(graph.addVertex(p1));
				
				
				assertEquals(3, graph.getVertexNumber());
				
				

				// Now getIndex() should return 3 and then 5, and then 10 after adding each element
				assertEquals(1, p1.getGraphID());			
				assertEquals(0, p2.getGraphID());
				assertEquals(2, p3.getGraphID());

				ArrayList<Person> list = new ArrayList<Person>();
				list.add(p2);
				list.add(p1);
				list.add(p3);
				
				assertEquals(list, graph.getAllElements());
				
			
			
			
		}
		
		
			
			@Test
			@DisplayName("Removing Vertices")
			 void removingVertices() {
				// Empty case
				assertFalse(graph.removeVertex(p1));
				
				// Adding elements to the graph
				assertTrue(graph.addVertex(p1));
				assertTrue(graph.addVertex(p2));
				assertTrue(graph.addVertex(p3));	
				
				
				// Check for number of vertices
				assertEquals(3, graph.getVertexNumber());
				
				// Can't add this elements because already it's on the graph
				assertFalse(graph.addVertex(p1));

				// Element removed
				assertTrue(graph.removeVertex(p1));
				// Checked that number of vertices has decreased
				assertEquals(2, graph.getVertexNumber());

				
				// Now that the element is removed, it should be able to add it
				assertTrue(graph.addVertex(p1));
				assertEquals(3, graph.getVertexNumber());
				
				
				ArrayList<Person> list = new ArrayList<Person>();
				list.add(p1);
				list.add(p2);
				list.add(p3);
				
				
				
				
				// It doesn't have the base elements
				assertTrue(graph.getAllElements().equals(list));
				
				
				
				assertTrue(graph.removeVertex(p2));
				assertFalse( graph.getAllElements().equals(list));
				list.remove(p2);
				assertTrue( graph.getAllElements().equals(list));

				
				// Adding back removed element
				assertTrue(graph.addVertex(p2));
				list.add(2,p2);
				assertFalse( graph.getAllElements().equals(list));

					
				

			}
			
		
			
			
		
		
	}
	
	@DisplayName("Managing Edges") 
	@Nested
	class manageEdge {
		
		@Test
		@DisplayName("Adding edges")
		void addingEdges() {
			
			// Trying to add edges without vertices in the graph
			assertFalse(graph.addEdge(p1, p2));
			
			// Adding vertices	
			assertTrue(graph.addVertex(p1));
			assertTrue(graph.addVertex(p2));
			assertTrue(graph.addVertex(p3));	
				
			
			// Checking variable
			assertEquals(0, graph.getEdgeNumber());
			// Adding edge between 0 and 1
			assertTrue(graph.addEdge(p1, p2));
			// Number of edges increased by one
			assertEquals(1, graph.getEdgeNumber());
			
			// Now testing if the adjacency is undirected, so one should have an edge to 0
			ArrayList<Person> adjacentsOfP1 = new ArrayList<Person>();
			ArrayList<Person> adjacentsOfP2 = new ArrayList<Person>();
			
			adjacentsOfP1.add(p2);
			adjacentsOfP2.add(p1);
			assertEquals(adjacentsOfP1, graph.getAdjacentsOf(p1));
			assertEquals(adjacentsOfP2, graph.getAdjacentsOf(p2));
			
			// Can't add parallel edges, not usefull in our project
			assertFalse(graph.addEdge(p1, p2));
			
			// Add another edge
			assertTrue(graph.addEdge(p1, p3 ));
			assertEquals(2, graph.getEdgeNumber());

			adjacentsOfP1.add(p3);
			// Check if adjacents are correct
			assertEquals(adjacentsOfP1, graph.getAdjacentsOf(p1));

			// Self loops not usefull in our project so they are disabled
			assertFalse(graph.addEdge(p1, p1));
			
		
			
			
		}
		
		
		@Test
		@DisplayName("Removing edges")
		void removingEdges() {
			
			// Removing with an empty graph
			assertFalse(graph.removeEdge(p1, p2));

			
			// Adding vertices
			assertTrue(graph.addVertex(p1));
			assertTrue(graph.addVertex(p2));
			assertTrue(graph.addVertex(p3));	
			
			// Checking variable
			assertEquals(0, graph.getEdgeNumber());
				
			ArrayList<Person> adjacentsOfP1 = new ArrayList<Person>();

				
			// Add a bunch of edges
			assertTrue(graph.addEdge(p1, p2));
			assertTrue(graph.addEdge(p1, p3));
			adjacentsOfP1.add(p2);
			adjacentsOfP1.add(p3);
			
			
			assertEquals(2, graph.getEdgeNumber());

			// Remove edge
			assertTrue(graph.removeEdge(p1, p2));
			assertFalse(graph.removeEdge(p1, p2));
			assertEquals(1, graph.getEdgeNumber());

			// Add it again and try from p2 to p1
			assertTrue(graph.addEdge(p1, p2));
			assertTrue(graph.removeEdge(p1, p2));

			
			// Add again edge p1-p2
			assertTrue(graph.addEdge(p2, p1));
			assertEquals(2, graph.getEdgeNumber());

			// Remove vertex 5, so edge 0-5 should not exist anymore
			assertTrue(graph.removeVertex(p2));
			adjacentsOfP1.remove(p2);
			
			// Check if the edge is removed from the adjacency list of 0 
			assertEquals(adjacentsOfP1, graph.getAdjacentsOf(p1));
			assertEquals(1, graph.getEdgeNumber());

			// Vertex is not in so this shouldn't add more edges
			assertFalse(graph.addEdge(p1, p2));
			assertEquals(1, graph.getEdgeNumber());

			assertTrue(graph.removeVertex(p1));
			assertTrue(graph.addVertex(p1));
			assertTrue(graph.addVertex(p2));
			
			assertTrue(graph.addEdge(p1, p2));
			
			
			
				
		}
		
		@Test
		void edgeToTesting() {
			
			// Base case, empty
			assertFalse(graph.edgeTo(p1, p2));
			// Adding vertices
			assertTrue(graph.addVertex(p1));
			assertTrue(graph.addVertex(p2));
			assertTrue(graph.addVertex(p3));
			
			// Testing whether or not there is a connection between p1 and p2 with a distance of 1
			assertFalse(graph.edgeTo(p1, p2));
			
			// Adding edge so test should be positive
			assertTrue(graph.addEdge(p1, p2));
			assertTrue(graph.edgeTo(p1, p2));
			
			// Removing edge and testing again, should said false
			assertTrue(graph.removeEdge(p2, p1));
			assertFalse(graph.edgeTo(p1, p2));
			
			// Remove vertex and check for edge
			assertTrue(graph.removeVertex(p1));
			assertFalse(graph.edgeTo(p1, p2));
	}
		
	}
	
	
	
	
	
}
