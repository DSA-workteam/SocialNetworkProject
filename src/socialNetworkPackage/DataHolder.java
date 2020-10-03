package socialNetworkPackage;

import java.util.ArrayList;
import java.util.Collection;

import abstractDataTypesImplemented.GenericArrayListHashMap;
import adt.DataBlockADT;
import adt.HashMapADT;
import dataStructuresImplemented.ArrayListDataBlock;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFound;

public class DataHolder{
	public static class StringDataBlock extends ArrayListDataBlock<String,String>{
		public StringDataBlock(String key) {
			super(key);
		}
	}
	
	private static HashMapADT<DataBlockADT<String,String>, String>[] stringHashMaps;	
	private static HashMapADT<Person, String> personHashMap;
	
	
	public DataHolder(int hashMapsSize) {
		personHashMap = new GenericArrayListHashMap<Person, String>(hashMapsSize);
		for(int i = 0; i < SocialNetwork.NPARAMETERS;i++)
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
	
	
	
	
	
	
	
	
	public void addPersonToNetwork(Person p) {
		String id = p.getAttribute(SocialNetwork.ID);
		if(!personHashMap.isIn(id, p)) {
			
			// Put the person in the Person Hash map
			personHashMap.put(id, p);		
			
			// Put the person's attributes in the corresponding StringDataBlock
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			
			for(int i = 0; i < attributes.length; i++)
				for(int j = 0; j < attributes[i].length;j++)
					attributes[i][j] = constructorUseDataBlock(i, attributes[i][j].getKey());
			
			
		}else {
			// Already on the network, duplicated
		}
		
	}	
	
	public boolean removePersonFromNetwork(Person p) {
		String id = null;
		
		
		DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
		
		for(int i = 0; i < attributes.length; i++)
			for(int j = 0; j < attributes[i].length;j++) {
				DataBlockADT<String, String> dataBlock = attributes[i][j];
				
				// If the collection of datablock is empty, removes that datablock from the hashmap
				if(dataBlock.remove(id) == 0)
					stringHashMaps[i].remove(dataBlock.getKey(), dataBlock);
			}
				
		
		
		return false;
	}
	
	
	public static Person getPersonByID(String id) {
		return null;
	}
	public static Person[] searchPeopleByAttribute(int attribute, String value) {
		return null;
	}
	
}

