package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;

class PeopleLoaderTester {

	DataHolder dh;
	
	@BeforeEach
	void createEmptyDataHolder() {
		DataHolder.instantiate(100);
		dh = DataHolder.getInstance();
		
	}
	@Test
	void addPerson() throws ImpulsoryAttributeRequiredException, AlreadyOnTheCollectionException, ElementNotFoundException {
		// Create person and add it
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);	
		// Add element
		dh.addPersonToNetwork(p);
		// Create another person with the same data
		Person p2 = new Person(data);	

		// Check if equals
		assertEquals(p2, dh.getPersonByID("Pepe77"));
		
	}
	@Test
	void addPersonWithoutId() throws ImpulsoryAttributeRequiredException {
		
		// Give data without id
		String data = ",Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		// Listen for exception
		assertThrows(ImpulsoryAttributeRequiredException.class, () -> {new Person(data);});
		
	}
	@Test
	void getAttributeFromPerson() throws ImpulsoryAttributeRequiredException {
		String data = "Pepe66,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371";
		Person p = new Person(data);
		assertEquals("Pepe66",p.getAttribute(PersonAttributesEnum.ID)[0]);
		assertEquals("Balloon",p.getAttribute(PersonAttributesEnum.SURNAME)[0]);

	}
	
	void multipleInputsInAttributes() {
		
		// Give data with multiple attributes and create people
		
		// Get attributes datablocks of the person and check if they have the same
		
	}
	

}
