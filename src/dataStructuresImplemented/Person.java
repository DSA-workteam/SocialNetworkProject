package dataStructuresImplemented;


import java.lang.reflect.Array;

import abstractDataTypesImplemented.GenericArrayListNode;
import adt.DataBlockADT;
import adt.NodeADT;
import enums.PersonAttributesEnum;
import exceptions.ImpulsoryAttributeRequiredException;
/**
 * This class is the main data object that our project uses.
 * @author Imanol Maraña Hurtado and Borja Moralejo Tobajas
 *
 */
public class Person {
	
	
	private DataBlockADT<String, String>[][] attributes;
	private String id;
	private NodeADT<String> personalNode;
	
	/**
	 * Constructor of Person class. It uses unchecked casting.
	 * @param combinedData - String. Input format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode. If an attribute needs multiple inputs, just add ";" to separate them.
	 * @throws ImpulsoryAttributeRequiredException - Id is the only obligatory attribute.
	 */
	@SuppressWarnings("unchecked")
	public Person(String combinedData) throws ImpulsoryAttributeRequiredException{
		// Initializing the DataBlockADT[][] 
		attributes = (DataBlockADT<String, String>[][]) Array.newInstance(DataBlockADT[].class, PersonAttributesEnum.values().length-1);
		
		
		// Separating the input parameter in arrays of attributes
		String[] separatedData = combinedData.split(",");
		
		// Checks if id is empty or not
		if(!separatedData[0].equals(""))
			id = separatedData[0];
		else throw new ImpulsoryAttributeRequiredException("Id required");
		
		// Main loop for setting all attributes
		for(int i =1;i < separatedData.length;i++) {
			// Separating each individual attribute in the multiple inputs. For example, multiple favorite movies
			String[] attributesData = separatedData[i].split(";");
			
			// Initializing the array with the same size as different attribute choices are
			attributes[i-1] = (DataBlockADT<String, String>[]) Array.newInstance(DataBlockADT.class, attributesData.length);
			
			// This loop assigns each attribute into each array position
			for(int j = 0; j < attributesData.length;j++) 									
				attributes[i-1][j] = new ArrayListDataBlock<String, String>(attributesData[j]);
			
		}
		
		personalNode = new GenericArrayListNode<String>(id);
		
	}
	
	/**
	 * Returns all the collection of DataBlockADT&lt;String, String&gt;[][] type. Inside of it there is all the information about that person.
	 * It's mainly use is for removing the person from the DataBlocks and then if the DataBlocks are left empty, they are removed from the main HashMap 
	 * @return attributes - DataBlock&lt;String, String&gt;[][]
	 */
	public DataBlockADT<String, String>[][] getDataBlocks(){
		return attributes;
	}
	
	/**
	 * It only returns the array of DataBlockADT&lt;String, String&gt; of the given attribute. This can be used to search for data neighbors. 
	 * @param attribute - {@link PersonAttributesEnum}. This is used for selecting which attribute do we want. Use the enumerator for selecting the attribute.
	 * @return {@link DataBlockADT}[]
	 */
	public DataBlockADT<String, String>[] getAttributesRelatedDataBlocks(PersonAttributesEnum attribute){
		if(attribute == PersonAttributesEnum.ID)
			return null;
		return attributes[attribute.ordinal()-1];
	}
	
	
	/**
	 * Returns the collection of the asked attribute.
	 * @param attribute - {@link PersonAttributesEnum}. Asked attribute
	 * @return String[] - Varying size.
	 */
	public String[] getAttribute(PersonAttributesEnum attribute) {
		String[] ret = null;
		
		
		if(attribute != PersonAttributesEnum.ID) {	
			int index = attribute.ordinal()-1;
			if(attributes[index] != null) {
				// Creates a string array
				int size = attributes[index].length;
				ret = new String[size];
				
				for(int i = 0; i < size;i++)
					ret[i] = attributes[index][i].getKey();
			}
			
		}else {
			
			ret = new String[1];
			ret[0] = id;
		}
		
		return ret;
	}
	
	
	/**
	 * Gets the node of this person
	 * @return The node of this person
	 */
	public NodeADT<String> getNode() {
		return personalNode;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj != null) {
			if(obj instanceof Person ) {
				Person p = (Person) obj;
				if(id.equals(p.getAttribute(PersonAttributesEnum.ID)[0])) {
					ret = true;				
					}
					
				
			
			
			}
		}
			return ret;
	}
	
	@Override
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
