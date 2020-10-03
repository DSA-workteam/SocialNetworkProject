package socialNetworkPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Scanner;

import abstractDataTypesImplemented.GenericArrayListHashMap;
import adt.DataBlockADT;
import adt.HashMapADT;
import dataStructuresImplemented.ArrayListDataBlock;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFound;
import exceptions.ImpulsoryAttributeRequiredException;

public class DataHolder{
	public static class StringDataBlock extends ArrayListDataBlock<String,String>{
		public StringDataBlock(String key) {
			super(key);
		}
	}
	
	private static HashMapADT<DataBlockADT<String,String>, String>[] stringHashMaps;	
	private static HashMapADT<Person, String> personHashMap;
	
	
	@SuppressWarnings("unchecked")
	public DataHolder(int hashMapsSize) {
		personHashMap = new GenericArrayListHashMap<Person, String>(hashMapsSize);
		
		stringHashMaps = (HashMapADT<DataBlockADT<String, String>, String>[]) Array.newInstance(GenericArrayListHashMap.class, SocialNetwork.NPARAMETERS-1);
		
		for(int i = 0; i < SocialNetwork.NPARAMETERS-1;i++)
			stringHashMaps[i] = new GenericArrayListHashMap<DataBlockADT<String, String>, String>(hashMapsSize);
	}
	
	
	private static DataBlockADT<String, String> getDataBlockOf(int attribute, String key) throws ElementNotFound{
		
		Collection<DataBlockADT<String, String>> resultsFromHashMap = stringHashMaps[attribute].get(key);
		DataBlockADT<String, String> ret = null;
		for(DataBlockADT<String, String> sdb: resultsFromHashMap)
			if(key.equals(sdb.getKey()))
				ret = sdb;
		
		if(ret == null) 
			throw new ElementNotFound("There's not such stringdatablock");
			
		return ret;
	}
	
	
	
	
	private DataBlockADT<String, String> constructorUseDataBlock(int attribute, String key) {
		DataBlockADT<String, String> ret;
		try {
		ret = getDataBlockOf(attribute, key);
		}catch(ElementNotFound e) {
			ret = new StringDataBlock(key);
			stringHashMaps[attribute].put(key, ret);		
		}
		
		return ret;
	}
	
	
	
	
	
	
	
	
	protected void addPersonToNetwork(Person p) {
		String id = p.getAttribute(SocialNetwork.ID)[0];
		if(!personHashMap.isIn(id, p)) {
			
			// Put the person in the Person Hash map
			personHashMap.put(id, p);		
			
			// Put the person's attributes in the corresponding StringDataBlock
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					attributes[i][j] = constructorUseDataBlock(i, attributes[i][j].getKey());
					attributes[i][j].add(id);
				}
			
		}else {
			// Already on the network, duplicated
		}
		
	}	
	
	public boolean removePersonFromNetwork(Person p) {
		String id = p.getAttribute(SocialNetwork.ID)[0];
		
		
		DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
		for(int i = 0; i < attributes.length; i++)
			if(attributes[i] != null)
			for(int j = 0; j < attributes[i].length;j++) {
				DataBlockADT<String, String> dataBlock = attributes[i][j];
				
				// If the collection of datablock is empty, removes that datablock from the hashmap
				//String key = dataBlock.getKey();

				if(attributes[i][j].remove(id) == 0) {
					stringHashMaps[i].remove(dataBlock.getKey(), dataBlock);
				}
			}
				
		
		
		return false;
	}
	
	
	public static Person getPersonByID(String id) {
		Collection<Person> people = personHashMap.get(id);
		Person ret = null;
		for(Person p : people) {
			if(id.equals(p.getAttribute(SocialNetwork.ID)[0])) {
				ret = p;
			}
		}
		return ret;
	}
	public static Person[] searchPeopleByAttribute(int attribute, String value) {
		return null;
	}
	
	
	
	
	

	public void loadFile(String fileName) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName;
		File f = new File(path);
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter("\n");
			while(s.hasNext())
				loadPerson(s.next());
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("You introduced an invalid filename.");
		}
		
		System.out.println("All people loaded");
		
	}
	
	
	/**
	 * Takes the input and 
	 * @param data String - csv format
	 */
	private void loadPerson(String data) {
		System.out.println(data);
		try {
			addPersonToNetwork(new Person(data));
		} catch (ImpulsoryAttributeRequiredException e) {			
			e.printStackTrace();
		}
		
	}
	
	
	public void printIntoFile(String fileName) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File writeFile = new File(path);
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
			Collection<Person> col = personHashMap.getAllElements();
			for(Person p: col) {
			
				writerPrinter.print(p.toString());
			}
			writerPrinter.close();
			System.out.println("Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}

