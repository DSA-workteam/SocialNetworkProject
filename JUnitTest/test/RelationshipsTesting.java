package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abstractDataTypesPackage.NodeADT;
import abstractDataTypesPackage.NodeADT2;
import dataStructuresImplemented.DataHolder;
import dataStructuresImplemented.DataManager;
import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;

class RelationshipsTesting {
	DataHolder dh;
	
	@BeforeEach
	void setupDH() {
		DataHolder.instantiate(100);
		dh = DataHolder.getInstance();
	}
	
	@Test
	void createRelationships() throws ImpulsoryAttributeRequiredException {
		
		// Add two friends
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527";
		Person p2 = new Person(data);	
		
		p.getNode().link(p2.getAttribute(PersonAttributesEnum.ID)[0]);
		p2.getNode().link(p.getAttribute(PersonAttributesEnum.ID)[0]);

		// Check if they are friends
		assertTrue(p.getNode().getLinkedNodes().contains(p2.getAttribute(PersonAttributesEnum.ID)[0]));
		
		
	}
	
	
	@Test
	void removeAllRelationships() throws ImpulsoryAttributeRequiredException, ElementNotFoundException {
		
		// Add two friends
		// Add two friends
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527";
		Person p2 = new Person(data);	
		
		p.getNode().link(p2.getAttribute(PersonAttributesEnum.ID)[0]);
		p2.getNode().link(p.getAttribute(PersonAttributesEnum.ID)[0]);

		// Check if they are linked
		assertTrue(p.getNode().getLinkedNodes().contains(p2.getAttribute(PersonAttributesEnum.ID)[0]));
		
		// Remove the links
		p.getNode().unlink(p2.getAttribute(PersonAttributesEnum.ID)[0]);
		// Check if they are not linked
		assertFalse(p.getNode().getLinkedNodes().contains(p2.getAttribute(PersonAttributesEnum.ID)[0]));

	}
	
	@Test
	void removeAPersonFromNetwork() throws ElementNotFoundException {
		// Load test social network
		DataManager.getInstance().loadFile("peopleJUnit", 0);
		DataManager.getInstance().loadFile("friendsJUnit", 1);
		// Check their relationships
		NodeADT2<String> peruNode = dh.getPersonByID("Peru57").getNode();
		Collection<String> c = peruNode.getLinkedNodes();
		// Remove from network
		dh.removePersonFromNetwork(dh.getPersonByID("Peru57"));
		// Check if their friends are still linked or not
		boolean found = false;
		Iterator<String> it = c.iterator();
		if(it.hasNext())
			found = found | dh.getPersonByID(it.next()).getNode().getLinkedNodes().contains(/*peruNode this is with the previous node method*/ "Peru57");
		assertFalse(found);
	}

}
