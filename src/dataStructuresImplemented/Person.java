package dataStructuresImplemented;


import adt.DataBlockADT;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder.StringDataBlock;
import socialNetworkPackage.SocialNetwork;

public class Person {
	
	// Attributes
	
	
	private DataBlockADT<String, String>[][] attributes;
	private String id;
	
	
	public Person(String combinedData) throws ImpulsoryAttributeRequiredException{
		
		//INSEAD OF USING STRING DATA BLOCK, USE ARRAY.NEW INSTANCE
		
		
		attributes = new StringDataBlock[SocialNetwork.NPARAMETERS-1][];
		String[] separatedData = combinedData.split(",");
		if(separatedData[0] != "")
			id = separatedData[0];
		else throw new ImpulsoryAttributeRequiredException("Id required");
		
		for(int i =1;i < separatedData.length;i++) {
			String[] attributesData = separatedData[i].split(";");
			attributes[i-1] = new StringDataBlock[attributesData.length];
			for(int j = 0; j < attributesData.length;j++) {				
				ArrayListDataBlock<String, String> aldb = new StringDataBlock(attributesData[j]);
			
				attributes[i-1][j] =aldb ;

			}
			
		}
	}
	
	public DataBlockADT<String, String>[][] getDataBlocks(){
		return attributes;
	}
	
	public DataBlockADT<String, String>[] getAttributesRelatedDataBlocks(int attribute){
		return attributes[attribute];
	}
	
	public void putInNetwork() {
	
	}
	public void removeFromNetwork() {
		
	}
	
	
	public String[] getAttribute(int attribute) {
		String[] ret = null;
		
		if(attribute != SocialNetwork.ID) {		
			int size = attributes[attribute].length;
			ret = new String[size];
			for(int i = 0; i < size;i++)
				ret[i] = attributes[attribute][i].getKey();
			
		}else { 
			ret = new String[1];
			ret[0] = id;
		}
		
		return ret;
	}
	
	
	public String toString(){
		String ret = id;
	
		for(int i = 0; i < attributes.length; i++) {
			if(i != attributes.length)
				ret += ",";
			if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					ret+= attributes[i][j].getKey();				
					if(j != attributes[i].length-1)
						ret += ";";
					
				}
		}
		
		return ret;
	}
	
}
