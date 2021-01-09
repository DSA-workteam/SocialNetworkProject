package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abstractDataTypesImplemented.PersonUndirectedAdjacencyListIndexedGraph;
import abstractDataTypesPackage.GraphADT;
import dataStructuresImplemented.Person;
import exceptions.ImpulsoryAttributeRequiredException;

class TestingDFSBFS {
	PersonUndirectedAdjacencyListIndexedGraph graph;
	Person p1, p2, p3, p4, p5, p6, p7;
	
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
			data = "Jh0nny90X,,,,,,,,,,";
			p4 = new Person(data);
			data = "PeonnyLvr,,,,,,,,,,";
			p5 = new Person(data);
			data = "Hasflun,,,,,,,,,,";
			p6 = new Person(data);
			data = "NSFW_NTR,,,,,,,,,,";
			p7 = new Person(data);
		} catch (ImpulsoryAttributeRequiredException e) {
			
		}
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addVertex(p4);
		graph.addVertex(p5);
		graph.addVertex(p6);
		graph.addVertex(p7);
		
		graph.addEdge(p1, p2);
		graph.addEdge(p2, p3);
		graph.addEdge(p3, p4);
		graph.addEdge(p2, p4);
		graph.addEdge(p5, p1);
		graph.addEdge(p6, p5);
	}
	
	@Test
	void testDFS() throws ImpulsoryAttributeRequiredException {
		//Testing DFS with the same person. Should return an array containing only himself.
		Iterator<Person> method = graph.largerConnectionBetween(p7, p7).iterator();
		ArrayList<Person> tester = new ArrayList<Person>();
		while(method.hasNext())
			tester.add(method.next());
		ArrayList<Person> aux = new ArrayList<Person>();
		aux.add(p7);
		assertTrue(tester.size() == 1);
		assertTrue(tester.equals(aux));
		
		
		//Testing DFS with two people that are not connected to each other not directly nor indirectly. Should return null
		assertTrue(p7.getGraphID() != -1 && p1.getGraphID() != -1); //Checking whether both of them are in the graph or not
		assertNull(graph.largerConnectionBetween(p7, p1));
		
		
		//Testing DFS with a person that doesn't exist, Should return null
		assertTrue(new Person("XDDX,,,,,,,,,,").getGraphID() == -1); //GraphID == -1 means that it's not in the graph
		assertNull(graph.largerConnectionBetween(new Person("XDDX,,,,,,,,,,"), p7));
		
		
		//Testing DFS with two person that are at distance 1. Should return an array of size 2
		method = graph.largerConnectionBetween(p1, p2).iterator();
		tester = new ArrayList<Person>();
		while(method.hasNext())
			tester.add(method.next());
		aux = new ArrayList<Person>();
		aux.add(p1);
		aux.add(p2);
		assertTrue(tester.size() == 2);
		assertTrue(tester.equals(aux));
		
		
		//Testing DFS with two people that aren't at distance 1. Should return an array of size 4
		method = graph.largerConnectionBetween(p1, p4).iterator();
		tester = new ArrayList<Person>();
		while(method.hasNext())
			tester.add(method.next());
		aux = new ArrayList<Person>();
		aux.add(p1);
		aux.add(p2);
		aux.add(p3);
		aux.add(p4);
		assertTrue(tester.size() == 4);
		assertTrue(tester.equals(aux));
	}
	
//	@Test
//	void testBFS() throws ImpulsoryAttributeRequiredException{
//		//Testing DFS with the same person. Should return an array containing only himself.
//		Iterator<Person> method = graph.pathAtDistance6(p7, p7).iterator();
//		ArrayList<Person> tester = new ArrayList<Person>();
//		while (method.hasNext())
//			tester.add(method.next());
//		ArrayList<Person> aux = new ArrayList<Person>();
//		aux.add(p7);
//		assertTrue(tester.size() == 1);
//		assertTrue(tester.equals(aux));
//		
//		
//		//Testing BFS with two people that are not connected to each other not directly nor indirectly. Should return null
//		assertTrue(p2.getGraphID() != -1 && p7.getGraphID() != -1); //Checking whether both of them are in the graph or not
//		assertNull(graph.pathAtDistance6(p2, p7));
//		
//		
//		//Testing DFS with a person that doesn't exist, Should return null
//		assertTrue(new Person("asdfg,,,,,,,,,,").getGraphID() == -1); //GraphID == -1 means that it's not in the graph
//		assertNull(graph.pathAtDistance6(new Person("asdfg,,,,,,,,,,"), p7));
//		
//		
//		//Testing DFS with two person that are at distance 1. Should return an array of size 2
//		method = graph.pathAtDistance6(p1, p2).iterator();
//		tester = new ArrayList<Person>();
//		while (method.hasNext())
//			tester.add(method.next());
//		aux = new ArrayList<Person>();
//		aux.add(p1);
//		aux.add(p2);
//		assertTrue(tester.size() == 2);
//		assertTrue(tester.equals(aux));
//		
//		
//		//Testing DFS with two people that aren't at distance 1. Should return an array of size 3
//		method = graph.pathAtDistance6(p1, p4).iterator();
//		tester = new ArrayList<Person>();
//		while (method.hasNext())
//			tester.add(method.next());
//		aux = new ArrayList<Person>();
//		aux.add(p1);
//		aux.add(p2);
//		aux.add(p4);
//		assertTrue(tester.size() == 3);
//		assertTrue(tester.equals(aux));
//	}
}
