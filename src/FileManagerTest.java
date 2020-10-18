import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructuresImplemented.Person;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;

class FileManagerTest {

	
	@BeforeEach
	void setupDH() {
		dh = new DataHolder(128);
	}
	
	DataHolder dh;
	@Test
	void checkLoadFile() throws ImpulsoryAttributeRequiredException, ElementNotFoundException {
		
		// create person
		String data = "Pepe77,Don Pepe,Balloon,3-10-2003,male,Los Angeles,San Francisco,San Francisco,New York,Tiana,G77371\n";
		
		Person p = new Person(data);	
		// load file that contains that person and compare all attributes
		dh.loadFile("peopleJUnit", 0);
		// File not found
		dh.loadFile("A", 0);
		
		Assertions.assertEquals(p, dh.getPersonByID("Pepe77"));
		Assertions.assertEquals(p, dh.searchPeopleByAttribute(Person.ID, "Pepe77")[0]);

	}
	
	@Test
	void testLoadRelationshipsWithoutPeople() {
		dh.loadFile("friendsJUnit", 1);
	}
	
	@Test
	void testDuplicated() {
		//Load file
		dh.loadFile("peopleJUnit", 0);
		int nOfPeople = dh.getNumberOfPeople();
		
		//Load again same file, it shouldn't increase the number of people
		dh.loadFile("peopleJUnit", 0);
		assertEquals(nOfPeople, dh.getNumberOfPeople());
		
		
	}
	
	@Test
	void printIntoFile() {
		
		//Load file
		dh.loadFile("peopleJUnit", 0);

		Collection<Person> people = dh.getPeople();
		//Print file
		dh.printIntoFile("peopleJUnitPrintTest");
		
		//Create another network
		dh = new DataHolder(128);
		//Load new file and compare both people collections
		dh.loadFile("peopleJUnitPrintTest", 0);
		
		Assertions.assertTrue(people.containsAll(dh.getPeople()));
		
		
	}

}
