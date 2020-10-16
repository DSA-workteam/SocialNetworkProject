package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.ImpulsoryAttributeRequiredException;

class TestingProjectJUnit {

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
		
		
		
		void checkLoadFile() {
			
			// create person
			
			// load file that contains that person and compare all attributes
			
		}
		
		
		
		void printIntoFile() {
			
			//Load file
			
			//Get all people
			
			//Print file
			
			//Create another network
			
			//Load new file and compare both people collections
			
			
			
		}
		
		
		
		
	}
	
	private class DataHolderLimitTesting{
		
		void addElement() {
			
			// Add element
			
			
			// Check if same
			
			
			// Remove element
			
			// Check that it doesn't have the element
			
			
			// Adde element with different attributes
			
		}
		
		void fillDataHolder() {
			// Add elements untill it cannot hold anymore
		}
		
		void emptyDataHolder() {
			// Empty all the data by removing all the elements
		}
		
	}
	
	private class TestingPeopleLoader {
		
		void createEmptyDataHolder() {
			// 
			
		}
		
		void addPersonWithAllAttributes() {
			// Create person and add it
			// Create another person with the same data
			// Check if equals
			
		}
		
		void addPersonWithoutId() throws ImpulsoryAttributeRequiredException {
			
			// Give data without id
			
			// ???
			
			// Profit
			
		}
		
		void multipleInputsInUniqueAttributes() {
			
			// Give data with multiple attributes in unique attributes and create people
			
			// Wait for error or exception like: toomanyattributes
			
			
		}
		
		
		void multipleInputsInAttributes() {
			
			// Give data with multiple attributes and create people
			
			// Get attributes datablocks of the person and check if they have the same
			
		}
		
		
		
	}
	
	
	
	private class RelationshipsTests{
		
		void createRelationships() {
			
			// Add two friends
			
			// Check if the links are reciprocal
			
		}
		
		void removeAllRelationships() {
			
			// Add two friends
			
			// Check if they are linked
			
			// Remove the links
			
			// Check if they are not linked
		}
		
		
		void removeAPersonFromNetwork() {
			// Add person into network
			
			// Check their relationships
			
			// Remove from network
			
			// Check if their friends are still linked or not
			
		}
		
	}
	
	
	private class DataBlockTesting{
	
		void createDatablock() {
			
			// Create datablock
			
			// Check if has the key correctly
			
		}
		
		void addIntoDataBlock() {
			
			// Create datablock
			
			// Add element into datablock
			
			// Check if the element is in the collection
			
			// Add another element
		}
		
		void removeFromDataBlock() {
			
			// Have a datablock with various elements, then check if the element is in the collection
			
			// Remove that element from the collecion
			
			// Check if the removed element was correctly removed
		}
		
		void checkRelatedInDataBlock() {
			
			// Have various related elements added
			
			// Check if the datablock holds
		}
		
	}
	

}
