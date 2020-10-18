import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adt.DataBlockADT;
import dataStructuresImplemented.ArrayListDataBlock;

class DataBlockTesting {

	@Test
	void createDatablock() {
		
		// Create datablock
		DataBlockADT<String, Integer> DB = new ArrayListDataBlock<String, Integer>(7);
		// Check if has the key correctly
		assertEquals(7,DB.getKey());
		
	}
	@Test
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
	@Test
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
