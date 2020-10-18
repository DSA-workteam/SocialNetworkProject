package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adt.NodeADT;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;

class RelationshipsTesting {
	DataHolder dh;
	
	@BeforeEach
	void setupDH() {
		dh = new DataHolder(128);
	}
	
	@Test
	void createRelationships() throws ImpulsoryAttributeRequiredException {
		
		// Add two friends
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527";
		Person p2 = new Person(data);	
		
		p.getNode().link(p2.getNode());
		p2.getNode().link(p.getNode());

		// Check if they are friends
		assertTrue(p.getNode().getLinkedNodes().contains(p2.getNode()));
		
		
	}
	
	
	@Test
	void removeAllRelationships() throws ImpulsoryAttributeRequiredException, ElementNotFoundException {
		
		// Add two friends
		// Add two friends
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527";
		Person p2 = new Person(data);	
		
		p.getNode().link(p2.getNode());
		p2.getNode().link(p.getNode());

		// Check if they are linked
		assertTrue(p.getNode().getLinkedNodes().contains(p2.getNode()));
		
		// Remove the links
		p.getNode().unlink(p2.getNode());
		// Check if they are not linked
		assertFalse(p.getNode().getLinkedNodes().contains(p2.getNode()));

	}
	
	@Test
	void removeAPersonFromNetwork() throws ElementNotFoundException {
		// Load test social network
		dh.loadFile("peopleJUnit", 0);
		dh.loadFile("friendsJUnit", 1);
		// Check their relationships
		NodeADT<String> peruNode = dh.getPersonByID("Peru57").getNode();
		Collection<NodeADT<String>> c = peruNode.getLinkedNodes();
		// Remove from network
		dh.removePersonFromNetwork(dh.getPersonByID("Peru57"));
		// Check if their friends are still linked or not
		boolean found = false;
		Iterator<NodeADT<String>> it = c.iterator();
		if(it.hasNext())
			found = found | it.next().getLinkedNodes().contains(peruNode);
		assertFalse(found);
	}

}
