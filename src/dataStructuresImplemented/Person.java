package dataStructuresImplemented;


import adt.DataBlockADT;
import exceptions.ImpulsoryAttributeRequiredException;
import socialNetworkPackage.DataHolder;
import socialNetworkPackage.DataHolder.StringDataBlock;
import socialNetworkPackage.SocialNetwork;

public class Person {
	
	// Attributes
	
	
	private DataBlockADT<String, String>[][] attributes;
	private String id;
	
	
	public Person(String combinedData) throws ImpulsoryAttributeRequiredException{
		
		attributes = new StringDataBlock[SocialNetwork.NPARAMETERS-1][];
		String[] separatedData = combinedData.split(",");
		if(separatedData[0] != "")
			id = separatedData[0];
		else throw new ImpulsoryAttributeRequiredException("Id required");
		
		for(int i =1;i < separatedData.length;i++) {
			String[] attributesData = separatedData[i].split(";");
			attributes[i-1] = new StringDataBlock[attributesData.length];
			for(int j = 0; j < attributesData.length;j++) {				
					attributes[i-1][j] = new ArrayListDataBlock<String, String>(attributesData[j]);

			}
			
		}
	}
	
	public DataBlockADT<String, String>[][] getDataBlocks(){
		return attributes;
	}
	public void putInNetwork() {
	
	}
	public void removeFromNetwork() {
		
	}
	
	public String/*[]*/ getAttribute(int attribute) {
		return attributes[attribute][0].getKey();
	}
	public String toString(){
		return null;
	}
	
}
