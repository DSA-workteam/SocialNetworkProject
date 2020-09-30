package socialNetworkPackage;

import java.util.ArrayList;

import abstractDataTypesImplemented.GenericArrayListHashMap;
import dataStructuresImplemented.ArrayListDataBlock;
import exceptions.ElementNotFound;

public class DataHolder{

	
	private static GenericArrayListHashMap<StringDataBlock, String>[] stringHashMaps;
	
	private static StringDataBlock getDataBlockOf(int attribute, String key) throws ElementNotFound{
		
		ArrayList<StringDataBlock> resultsFromHashMap = stringHashMaps[attribute].get(key);
		StringDataBlock ret = null;
		for(StringDataBlock sdb: resultsFromHashMap)
			if(key.equals(sdb.getKey()))
				ret = sdb;
		
		if(ret == null) 
			throw new ElementNotFound("There's not such stringdatablock");
			
		return ret;
	}
	
	public static StringDataBlock constructorUseDataBlock(int attribute, String key) {
		StringDataBlock ret;
		try {
		ret = getDataBlockOf(attribute, key);
		}catch(ElementNotFound e) {
			ret = new StringDataBlock(key);
			stringHashMaps[attribute].put(key, ret);		
		}
		return ret;
	}
	
	public static class StringDataBlock extends ArrayListDataBlock<String,String>{
		public StringDataBlock(String key) {
			super(key);
		}
	}
}

