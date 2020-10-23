package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;
import socialNetworkPackage.DataManager;

class FileManagerTest {

	
	@BeforeEach
	void setupDH() {
		DataHolder.instantiate(100);
		dh = DataHolder.getInstance();
		
	}
	
	DataHolder dh;
	@Test
	void checkLoadFile() throws ImpulsoryAttributeRequiredException, ElementNotFoundException {
		
		// create person
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
		
		Person p = new Person(data);	
		// load file that contains that person and compare all attributes
		DataManager.getInstance().loadFile("peopleJUnit", 0);
		// File not found
		DataManager.getInstance().loadFile("A", 0);
		
		Assertions.assertEquals(p, dh.getPersonByID("Pepe77"));
		Assertions.assertEquals(p, dh.searchPeopleByAttribute(PersonAttributesEnum.ID, "Pepe77")[0]);

	}
	
	@Test
	void testLoadRelationshipsWithoutPeople() {
		DataManager.getInstance().loadFile("friendsJUnit", 1);
	}
	
	@Test
	void testDuplicated() {
		//Load file
		DataManager.getInstance().loadFile("peopleJUnit", 0);
		int nOfPeople = dh.getNumberOfPeople();
		
		//Load again same file, it shouldn't increase the number of people
		DataManager.getInstance().loadFile("peopleJUnit", 0);
		assertEquals(nOfPeople, dh.getNumberOfPeople());
		
		
	}
	
	@Test
	void printIntoFile() {
		
		//Load file
		DataManager.getInstance().loadFile("peopleJUnit", 0);

		Collection<Person> people = dh.getPeople();
		//Print file
		DataManager.getInstance().printIntoFile("peopleJUnitPrintTest");
		
		//Create another network
		DataHolder.instantiate(100);
		//Load new file and compare both people collections
		DataManager.getInstance().loadFile("peopleJUnitPrintTest", 0);
		
		Assertions.assertTrue(people.containsAll(dh.getPeople()));
		
		
	}

}
