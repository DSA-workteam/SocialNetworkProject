package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import adt.DataBlockADT;
import adt.NodeADT;
import dataStructuresImplemented.ArrayListDataBlock;
import dataStructuresImplemented.Person;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;

class TestingProjectJUnit {
	DataHolder dh;
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	
	
	private class PersonTester{
		
		void testingEquals() {
			// Create person with different and same attributes
			
			//Add a person into the network, get it and test if they are equals
			
			
		}
		
	}
	
	
	private class FileManagement{
		
		
		@Test
		void checkLoadFile() {
			
			// create person
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			
			Person p = new Person(data);	
			// load file that contains that person and compare all attributes
			dh.loadFile("peopleJUnit", 0);
			
			
			
			Assertions.assertEquals(p, dh.getPersonByID("Pepe77"));
			
		}
		
		
		@Test
		void printIntoFile() {
			
			//Load file
			dh.loadFile("peoPersonit", 0);

			Collection<Person> people = dh.getPeople();
			//Print file
			dh.printIntoFile("peopleJUnitPrintTest");
			
			//Create another network
			dh = new DataHolder();
			//Load new file and compare both people collections
			dh.loadFile("peopleJUnitPrintTest", 0);
			
			Assertions.assertTrue(people.containsAll(dh.getPeople()));
			
			
		}
		
		
		
		
	}
	
	private class DataHolderLimitTesting{
		@Test
		void addElement() {
			
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			// Add element
			dh.addPersonToNetwork(p);
			
			// Check if same
			assertEquals(p,dh.getPersonByID("Pepe77"));
			
			// Remove element
			dh.removePersonFromNetwork(p);
			
			// Check that it doesn't have the element
			assertThrows(ElementNotFoundException.class, dh.getPersonByID("Pepe77"));
			
			
			
		}
		
		@Test
		void addDuplicatedElement() {
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			// Add element
			dh.addPersonToNetwork(p);
			
			// Listen for AlreadyOnTheCollectionException
			assertThrows(AlreadyOnTheCollectionException.class, dh.addPersonToNetwork(p));
			
			p = new Person(data);
			
			assertThrows(AlreadyOnTheCollectionException.class, dh.addPersonToNetwork(p));


		}
		
		
		@Test
		void relatedElements() {
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			data = "Pipo,PinPin,PonPon,4-2-2003,Los Angeles,San Francisco,San Francisco,Los Angeles,Pipo,G77371\n";
			Person p2 = new Person(data);	
			// Add element
			dh.addPersonToNetwork(p);
			dh.addPersonToNetwork(p2);
			// Check if p2 is inside the related data block of birthplace
			assertEquals(p.getAttributesRelatedDataBlocks(Person.BIRTHPLACE), p2.getAttributesRelatedDataBlocks(Person.BIRTHPLACE));
			
			
		}
		
		
	}
	
	private class TestingPeopleLoader {
		
		@Test
		void createEmptyDataHolder() {
			dh = new DataHolder();
			
		}
		@Test
		void addPerson() {
			// Create person and add it
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			// Add element
			dh.addPersonToNetwork(p);
			// Create another person with the same data
			Person p2 = new Person(data);	

			// Check if equals
			assertEquals(p2, dh.getPersonByID("Pepe77"));
			
		}
		
		void addPersonWithoutId() throws ImpulsoryAttributeRequiredException {
			
			// Give data without id
			String data = ",Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p;	
			// Listen for exception
			assertThrows(ImpulsoryAttributeRequiredException.class,p = new Person(data));
			
		}
		
				
		
		void multipleInputsInAttributes() {
			
			// Give data with multiple attributes and create people
			
			// Get attributes datablocks of the person and check if they have the same
			
		}
		
		
		
	}
	
	
	
	private class RelationshipsTests{
		
		@Test
		void createRelationships() {
			
			// Add two friends
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527\n";
			Person p2 = new Person(data);	
			
			p.getNode().link(p2.getNode());
			p2.getNode().link(p.getNode());

			// Check if they are friends
			assertTrue(p.getNode().getLinkedNodes().contains(p2.getNode()));
			
			
		}
		
		
		@Test
		void removeAllRelationships() {
			
			// Add two friends
			// Add two friends
			String data = "Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
			Person p = new Person(data);	
			data = "Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,,Cadena Perpetua;Your voice,G25527\n";
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
		
		
		void removeAPersonFromNetwork() {
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
	
	
	private class DataBlockTesting{
	
		void createDatablock() {
			
			// Create datablock
			DataBlockADT<String, Integer> DB = new ArrayListDataBlock<String, Integer>(7);
			// Check if has the key correctly
			assertEquals(7,DB.getKey());
			
		}
		
		void addIntoDataBlock() {
			
			// Create datablock
			DataBlockADT<String, Integer> DB = new ArrayListDataBlock<String, Integer>(7);
			// Add element into datablock
			DB.add("Pepe");
			// Check if the element is in the collection
			assertTrue(DB.getCollection().contains("Pepe"));
			// Add another element
			DB.add("Pipo");
			// Check both elements in the collection
			assertTrue(DB.getCollection().contains("Pepe"));
			assertTrue(DB.getCollection().contains("Pipo"));


		}
		
		void removeFromDataBlock() {
			
			// Have a datablock with various elements, then check if the element is in the collection
			DataBlockADT<String, Integer> DB = new ArrayListDataBlock<String, Integer>(7);
			DB.add("Pepe");
			assertTrue(DB.getCollection().contains("Pepe"));

			// Remove that element from the collecion
			DB.remove("Pepe");
			// Check if the removed element was correctly removed
			assertFalse(DB.getCollection().contains("Pepe"));
			
		}
		
		
	}
	

}
