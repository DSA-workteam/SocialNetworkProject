package socialNetworkPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import abstractDataTypesImplemented.GenericArrayListHashMap;
import abstractDataTypesPackage.DataBlockADT;
import abstractDataTypesPackage.HashMapADT;
import abstractDataTypesPackage.NodeADT;
import dataStructuresImplemented.ArrayListDataBlock;
import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;



/**
 * This is the main class which contains the data while in runtime. The data is stored in hash maps. The only thing that is not stored here is the friends relationships. They are stored in Person class, inside one of their attributes.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 */
public class DataHolder{

	
	private static HashMapADT<DataBlockADT<String,String>, String>[] stringHashMaps;	
	private static HashMapADT<Person, String> personHashMap;
	private static HashMapADT<DataBlockADT<Person,String>, String> dates; 
	
	
	// Singleton
	
	private static DataHolder instance;
	
	/**
	 * Singleton's instance getter
	 * @return {@link DataHolder}
	 */
	public static DataHolder getInstance() {
		return instance;
	}
	
	/**
	 * Singleton's instance initiliarizers
	 * @param hashmapsize - int.
	 */
	public static void instantiate(int hashmapsize) {
		instance = new DataHolder(hashmapsize);
	}
	
	
	
	
	
	/**
	 * Constructor of the class. Input the desired initial size of the hash maps.
	 * @param hashMapsSize - int. Desired size.
	 */
	@SuppressWarnings("unchecked")
	private DataHolder(int hashMapsSize) {
		personHashMap = new GenericArrayListHashMap<Person, String>(hashMapsSize);
		
		stringHashMaps = (HashMapADT<DataBlockADT<String, String>, String>[]) Array.newInstance(GenericArrayListHashMap.class, PersonAttributesEnum.values().length-1);
		
		dates = new GenericArrayListHashMap<DataBlockADT<Person,String>, String>(hashMapsSize);
		
		for(int i = 0; i < PersonAttributesEnum.values().length-1;i++)
			stringHashMaps[i] = new GenericArrayListHashMap<DataBlockADT<String, String>, String>(hashMapsSize);
	}
	
	/**
	 * Gets the attribute's data block with the given key, if there isn't any, it throws {@link ElementNotFoundException}.
	 * @param attribute - {@link PersonAttributesEnum}.
	 * @param key - String. Wanted key inside of the data block.
	 * @return datablock - {@link DataBlockADT}.
	 * @throws ElementNotFoundException
	 */
	private static DataBlockADT<String, String> getDataBlockOf(PersonAttributesEnum attribute, String key) throws ElementNotFoundException{
		// Searches inside the conflicts inside the hash map
		if(attribute == PersonAttributesEnum.ID)
            throw new ElementNotFoundException("id datablock");

		Iterator<DataBlockADT<String, String>> resultsFromHashMap = stringHashMaps[attribute.ordinal()-1].get(key).iterator();
        DataBlockADT<String, String> ret = null;        
        
        boolean found = false;
        // Searches inside the collection the data block
        while(!found && resultsFromHashMap.hasNext()) {
            ret = resultsFromHashMap.next();
            if(key.equals(ret.getKey())) {
                found = true;
            }            
        }
        
        if(!found) 
            throw new ElementNotFoundException("key "+key.toString() + " datablock");
            
        return ret;
	}
	
	
	
	/**
	 * This private function returns a data block with the requested key and requested attribute, if an ElementNotFoundException is thrown, it creates a new one, adds it into the hash map and returns that.
	 * @param attribute - {@link PersonAttributesEnum}. Requested attribute.
	 * @param key - String. Wanted key.
	 * @return DataBlockADT<String, String>. {@link DataBlockADT} It returns an object, it could be recently created or from the hash map created.
	 */
	private DataBlockADT<String, String> constructorUseDataBlock(PersonAttributesEnum attribute, String key) {
		DataBlockADT<String, String> ret;
		try {
		ret = getDataBlockOf(attribute, key);
		}catch(ElementNotFoundException e) {
			ret = new ArrayListDataBlock<String, String>(key);
			stringHashMaps[attribute.ordinal()-1].put(key, ret);		
		}
		
		return ret;
	}
	
	
	
	/**
	 * Returns the collection of all the elements in the network. For example, if you request all the name attribute, this will return the collection of all the names in the network.
	 * @param attribute - {@link PersonAttributesEnum}.
	 * @return {@link Collection} {@link DataBlockADT}
	 */
	public Collection<DataBlockADT<String, String>> getCollectionOfAttribute(PersonAttributesEnum attribute){
		return stringHashMaps[attribute.ordinal()-1].getAllElements();
	}
	
	
	
	/**
	 * Joins elements by birth date
	 * @param key - String. Birth date of Person p
	 * @param p - {@link Person}
	 */
	private void datesAgrupator(String key, Person p) {
		String year = "";
		try {
			year = key.split("-")[2];
		}catch(ArrayIndexOutOfBoundsException e) {
			year = "";
		}
		
		if(year != "") {
			DataBlockADT<Person, String> db;
			try {
				getYearOfBirth(year).add(p);
			}catch(ElementNotFoundException e) {
			
				db = new ArrayListDataBlock<Person, String>(year);
				db.add(p);
				dates.put(year, db);			
			}
		}
	}
	
	
	
	/**
	 * Get the datablock of the year of birth given by the user
	 * @param year - String
	 * @return {@link DataBlockADT}
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}
	 */
	public DataBlockADT<Person, String> getYearOfBirth(String year) throws ElementNotFoundException{
		DataBlockADT<Person, String> ret = null;
		Iterator<DataBlockADT<Person, String>> itDB = dates.get(year).iterator();
		boolean found = false;
		DataBlockADT<Person, String> db;
		while(itDB.hasNext()) {
			 db = itDB.next();
			if(db.getKey().equals(year)) {
				found = true;
				ret = db;
			}
		}
		if(!found)
			throw new ElementNotFoundException("datablock of year "+year );
		return ret;
	}
	
	
	/**
	 * Adds person into the network data base, it takes the related data blocks and adds Person p's id into it.
	 * @param p - {@link Person}.
	 * @throws AlreadyOnTheCollectionException - {@link AlreadyOnTheCollectionException} If the person that was trying to add was already on the collection.
	 */
	public void addPersonToNetwork(Person p) throws AlreadyOnTheCollectionException{
		String id = p.getAttribute(PersonAttributesEnum.ID)[0];
		if(!personHashMap.isIn(id, p)) {
			System.out.println(p.toString());
			// Put the person in the Person Hash map
			personHashMap.put(id, p);		
			
			// Put the person's attributes in the corresponding StringDataBlock
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null) {
					for(int j = 0; j < attributes[i].length;j++) {
						attributes[i][j] = constructorUseDataBlock(PersonAttributesEnum.values()[i+1], attributes[i][j].getKey());
						attributes[i][j].add(id);
						
					}
					if(PersonAttributesEnum.values()[i+1] == PersonAttributesEnum.BIRTHDATE) {
						datesAgrupator(attributes[i][0].getKey(),p);
					}
				}
			
		}else {
			throw new AlreadyOnTheCollectionException(p.toString()); 
		}
		
	}	
	
	
	/**
	 * Removes person from the network data and if the person's data block is left empty, it removes the data block from the hash map.
	 * @param p - {@link Person}.
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}. Throws this exception if the Person p isn't in the network data.
	 */
	public void removePersonFromNetwork(Person p) throws ElementNotFoundException{
		String id = p.getAttribute(PersonAttributesEnum.ID)[0];
		if(personHashMap.remove(id, p)){
			
			removeRelationshipsOfPerson(p);			
			
			//Removes the person's attribute from the database so it's unrelated again from it			
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					
					DataBlockADT<String, String> dataBlock = attributes[i][j];				
					// If the collection of datablock is empty, removes that datablock from the hashmap
					
					if(attributes[i][j].remove(id) ) {
						if(attributes[i][j].size() == 0 )
							stringHashMaps[i].remove(dataBlock.getKey(), dataBlock);
					}
				}
			
		}else {
			throw new ElementNotFoundException(p.toString());	
		}
		
	}
	
	/**
	 * Removes given person from the relationships of the network
	 * @param p - {@link Person}
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}
	 */
	public void removeRelationshipsOfPerson(Person p) throws ElementNotFoundException {
		//Unlinks all the nodes that were attached to this node
		p.getNode().getLinkedNodes().toArray();
		Iterator<NodeADT<String>> it = p.getNode().getLinkedNodes().iterator();
		NodeADT<String> node;
		while(it.hasNext()) {
			node = it.next();
			System.out.println(node.getContent() +" unlinked of "+p.getNode().getContent()  );
			node.unlink(p.getNode());
		}
	}
	
	/**
	 * Gets person by its id.
	 * @param id - String.
	 * @return Person - {@link Person}. Person with the given id.
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}. If there's no one with that ID.
	 */
	public Person getPersonByID(String id) throws ElementNotFoundException{
		Collection<Person> people = personHashMap.get(id);
		Person ret = null;
		for(Person p : people) {
			if(id.equals(p.getAttribute(PersonAttributesEnum.ID)[0])) {
				ret = p;
			}
		}
		if(ret == null)
			throw new ElementNotFoundException("The person with the id "+id+" isn't in the network");
		return ret;
	}
	
	
	/**
	 * Searches people by the given attribute. It returns the group that matches the search.
	 * @param attribute - {@link PersonAttributesEnum}. 
	 * @param value - String search value.
	 * @return Person[] {@link Person} group of people that matches the search.
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}. There's nobody with that attribute
	 */
	public Person[] searchPeopleByAttribute(PersonAttributesEnum attribute, String value) throws ElementNotFoundException{
		
		Person[] ret = null;
		
		//Checks if the attribute selected is ID and uses the getPersonByID method
		if(attribute == PersonAttributesEnum.ID) {
			ret = new Person[1];
			ret[0] = getPersonByID(value);
		}
		
		//If the selected attribute is not ID, searches for the output by other way
		else {
			Collection<String> IDs = getDataBlockOf(attribute, value).getCollection();
			ret = new Person[IDs.size()];
			Iterator<String> it = IDs.iterator();
			int i = 0;
			while(it.hasNext()) {
				ret[i++] = getPersonByID(it.next());
			}
		}
		
		if (ret.length == 0)
			throw new ElementNotFoundException("There is no element with those values");
		return ret;
		
	}
	
	
	
	
	
	
	/**
	 * Gets all the people of the network and returns the collection
	 * @return Collection&lt;Person&gt;
	 */
	public Collection<Person> getPeople(){
		return personHashMap.getAllElements();
	}
	
	/**
	 * Returns number of people in the network.
	 * @return int
	 */
	public int getNumberOfPeople() {
		return personHashMap.size();
	}
	
}

