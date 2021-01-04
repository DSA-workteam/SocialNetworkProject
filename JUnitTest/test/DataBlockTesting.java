package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import abstractDataTypesImplemented.GenericArrayListDataBucket;
import abstractDataTypesPackage.DataBucketADT;

class DataBlockTesting {

	@Test
	void createDatablock() {
		
		// Create datablock
		DataBucketADT<String, Integer> DB = new GenericArrayListDataBucket<String, Integer>(7);
		// Check if has the key correctly
		assertEquals(7,DB.getKey());
		
	}
	@Test
	void addIntoDataBlock() {
		
		// Create datablock
		DataBucketADT<String, Integer> DB = new GenericArrayListDataBucket<String, Integer>(7);
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
		DataBucketADT<String, Integer> DB = new GenericArrayListDataBucket<String, Integer>(7);
		DB.add("Pepe");
		assertTrue(DB.getCollection().contains("Pepe"));

		// Remove that element from the collecion
		DB.remove("Pepe");
		// Check if the removed element was correctly removed
		assertFalse(DB.getCollection().contains("Pepe"));
		
	}
	

}
