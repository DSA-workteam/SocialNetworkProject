package dataStructuresImplemented;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import abstractDataTypesImplemented.GenericArrayListDataBucket;
import abstractDataTypesImplemented.GenericArrayListHashMap;
import abstractDataTypesImplemented.GenericArrayListHashTable;
import abstractDataTypesPackage.DataBucketADT;
import abstractDataTypesPackage.HashMapADT;
import abstractDataTypesPackage.HashTableADT;
import abstractDataTypesPackage.NodeADT;
import abstractDataTypesPackage.NodeADT2;
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

	
	private static HashMapADT<String, String>[] attributesHashMaps;	
	private static HashTableADT<Person, String> personLookTable;
	private static HashMapADT<Person, String> dates; 
	
	
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
		personLookTable = new GenericArrayListHashTable<Person, String>(hashMapsSize);
		
		attributesHashMaps = (HashMapADT<String, String>[]) Array.newInstance(GenericArrayListHashMap.class, PersonAttributesEnum.values().length-1);
		
		dates = new GenericArrayListHashMap<Person, String>(hashMapsSize);
		
		for(int i = 0; i < PersonAttributesEnum.values().length-1;i++)
			attributesHashMaps[i] = new GenericArrayListHashMap<String, String>(hashMapsSize);
	}
	
	/**
	 * Gets the attribute's data bucket with the given key, if there isn't any, it throws {@link ElementNotFoundException}.
	 * @param attribute - {@link PersonAttributesEnum}.
	 * @param key - String. Wanted key inside of the data bucket.
	 * @return databucket - {@link DataBucketADT}.
	 * @throws ElementNotFoundException
	 */
	private DataBucketADT<String, String> getDataBucketOf(PersonAttributesEnum attribute, String key) throws ElementNotFoundException{
		// Searches inside the conflicts inside the hash map
		if(attribute == PersonAttributesEnum.ID)
            throw new ElementNotFoundException("id databucket");

		return attributesHashMaps[attribute.ordinal()-1].getBucket(key);       
      
	}
	
	
	
	/**
	 * This private function returns a data bucket with the requested key and requested attribute, if an ElementNotFoundException is thrown, it creates a new one, adds it into the hash map and returns that.
	 * @param attribute - {@link PersonAttributesEnum}. Requested attribute.
	 * @param key - String. Wanted key.
	 * @return DataBucketADT<String, String>. {@link DataBucketADT} It returns an object, it could be recently created or from the hash map created.
	 */
	private DataBucketADT<String, String> constructorUseDataBucket(PersonAttributesEnum attribute, String key, String value) {
		DataBucketADT<String, String> ret;
		try {
			ret = getDataBucketOf(attribute, key);
			ret.add(value);
		 }catch(ElementNotFoundException e) {	
			ret = attributesHashMaps[attribute.ordinal()-1].createBucket(value, key);		
			attributesHashMaps[attribute.ordinal()-1].put(ret);
		}
		return ret;
	}
	
	
	
	/**
	 * Returns the collection of all the elements in the network. For example, if you request all the name attribute, this will return the collection of all the names in the network.
	 * @param attribute - {@link PersonAttributesEnum}.
	 * @return {@link Collection} {@link DataBucketADT}
	 */
	public Collection<DataBucketADT<String, String>> getCollectionOfAttribute(PersonAttributesEnum attribute){
		return attributesHashMaps[attribute.ordinal()-1].getAllBuckets();
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
			try {
				getYearOfBirth(year).add(p);
			}catch(ElementNotFoundException e) {					
				dates.put(p, year);			
			}
		}
	}
	
	
	
	/**
	 * Get the databucket of the year of birth given by the user
	 * @param year - String
	 * @return {@link DataBucketADT}
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}
	 */
	public DataBucketADT<Person, String> getYearOfBirth(String year) throws ElementNotFoundException{
		return dates.getBucket(year);
	}
	
	
	/**
	 * Adds person into the network data base, it takes the related data buckets and adds Person p's id into it.
	 * @param p - {@link Person}.
	 * @throws AlreadyOnTheCollectionException - {@link AlreadyOnTheCollectionException} If the person that was trying to add was already on the collection.
	 */
	public void addPersonToNetwork(Person p) throws AlreadyOnTheCollectionException{
		String id = p.getAttribute(PersonAttributesEnum.ID)[0];
		if(!personLookTable.isIn(p, id)) {
			System.out.println(p.toString());
			// Put the person in the Person Hash map
			personLookTable.put(p ,id);		
			
			// Put the person's attributes in the corresponding StringDataBucket
			DataBucketADT<String, String>[][] attributes = p.getDataBuckets();
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null) {
					for(int j = 0; j < attributes[i].length;j++) {
						attributes[i][j] = constructorUseDataBucket(PersonAttributesEnum.values()[i+1], attributes[i][j].getKey(), id);
						
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
	 * Removes person from the network data and if the person's data bucket is left empty, it removes the data bucket from the hash map.
	 * @param p - {@link Person}.
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}. Throws this exception if the Person p isn't in the network data.
	 */
	public void removePersonFromNetwork(Person p) throws ElementNotFoundException{
		String id = p.getAttribute(PersonAttributesEnum.ID)[0];
		if(personLookTable.remove(p, id)){
			
			removeRelationshipsOfPerson(p);			
			
			//Removes the person's attribute from the database so it's unrelated again from it			
			DataBucketADT<String, String>[][] attributes = p.getDataBuckets();
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					
					DataBucketADT<String, String> dataBucket = attributes[i][j];				
					// If the collection of databucket is empty, removes that databucket from the hashmap
					
					if(attributes[i][j].remove(id) ) {
						if(attributes[i][j].size() == 0 )
							attributesHashMaps[i].remove(id, dataBucket.getKey());
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
		Iterator<String> it = p.getNode().getLinkedNodes().iterator();
		NodeADT2<String> node;
		while(it.hasNext()) {
			node = getPersonByID(it.next()).getNode();
			System.out.println(node.getContent() +" unlinked of "+p.getNode().getContent()  );
			node.unlink(p.getAttribute(PersonAttributesEnum.ID)[0]);
		}
	}
	
	/**
	 * Gets person by its id.
	 * @param id - String.
	 * @return Person - {@link Person}. Person with the given id.
	 * @throws ElementNotFoundException - {@link ElementNotFoundException}. If there's no one with that ID.
	 */
	public Person getPersonByID(String id) throws ElementNotFoundException{
		return personLookTable.get(id);	
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
		}else { //If the selected attribute is not ID, searches for the output by other way
			Collection<String> IDs = getDataBucketOf(attribute, value).getCollection();
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
		return personLookTable.getAllElements();
	}
	
	/**
	 * Returns number of people in the network.
	 * @return int
	 */
	public int getNumberOfPeople() {
		return personLookTable.size();
	}
	
}

