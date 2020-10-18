package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructuresImplemented.Person;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;

class DataHolderTester {
	DataHolder dh;
	@BeforeEach
	void setupDH() {
		dh = new DataHolder(128);
	}
	
	
	@Test
	void addElement() throws ImpulsoryAttributeRequiredException, AlreadyOnTheCollectionException, ElementNotFoundException {
		
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		// Add element
		dh.addPersonToNetwork(p);
		
		// Check if same
		assertEquals(p,dh.getPersonByID("Pepe77"));
		
		// Remove element
		dh.removePersonFromNetwork(p);
		
		// Check that it doesn't have the element
		assertThrows(ElementNotFoundException.class, ()->{dh.getPersonByID("Pepe77");});
		
		
		
	}
	
	@Test
	void addDuplicatedElement() throws AlreadyOnTheCollectionException {
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = null;
		try {
			p = new Person(data);
		} catch (ImpulsoryAttributeRequiredException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Add element
		dh.addPersonToNetwork(p);
		
		// Listen for AlreadyOnTheCollectionException
		assertThrows(AlreadyOnTheCollectionException.class,() -> {dh.addPersonToNetwork( new Person(data));});
		
		


	}
	
	
	@Test
	void relatedElements() throws ImpulsoryAttributeRequiredException, AlreadyOnTheCollectionException {
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Pipo,PinPin,PonPon,4-2-2003,male,Los Angeles,San Francisco,San Francisco,Los Angeles,Pipo,G77371";
		Person p2 = new Person(data);	
		// Add element
		dh.addPersonToNetwork(p);
		dh.addPersonToNetwork(p2);
		// Check if p2 is inside the related data block of birthplace
		assertEquals(p.getAttributesRelatedDataBlocks(Person.BIRTHPLACE)[0], p2.getAttributesRelatedDataBlocks(Person.BIRTHPLACE)[0]);
		
		
	}
	
	@Test
	void relatedElementsViaDataHolder() throws ImpulsoryAttributeRequiredException, AlreadyOnTheCollectionException, ElementNotFoundException {
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		data = "Pipo,PinPin,PonPon,4-2-2003,male,Los Angeles,San Francisco,San Francisco,Los Angeles,Pipo,G777";
		Person p2 = new Person(data);	
		// Add element
		dh.addPersonToNetwork(p);
		dh.addPersonToNetwork(p2);
		// Search them
		assertEquals(p, dh.searchPeopleByAttribute(Person.BIRTHPLACE,"Los Angeles")[0]);
		assertEquals(p2, dh.searchPeopleByAttribute(Person.GROUPCODE, "G777")[0]);
		

		
	}
	
	@Test 
	void removePersonThatIsntInNetwork() throws ImpulsoryAttributeRequiredException {
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		assertThrows(ElementNotFoundException.class, () -> dh.removePersonFromNetwork(p));
	}

}