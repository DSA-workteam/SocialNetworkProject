package dataStructuresImplemented;


import java.lang.reflect.Array;

import abstractDataTypesImplemented.GenericArrayListNode;
import adt.DataBlockADT;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
/**
 * This class is the main data object that our project uses.
 * @author Imanol Maraña Hurtado & Borja Moralejo Tobajas
 *
 */
public class Person {
	
	public final static int NPARAMETERS = 11;	
	public final static int ID = -1, NAME =0, SURNAME = 1, BIRTHDATE = 2, GENDER = 3, BIRTHPLACE = 4, HOME = 5,STUDIEDAT = 6,
			WORKEDAT =7, MOVIES = 8, GROUPCODE = 9;
	
	private DataBlockADT<String, String>[][] attributes;
	private String id;
	private GenericArrayListNode<String> personalNode;
	
	/**
	 * Constructor of Person class. It uses unchecked casting.
	 * @param combinedData - String. Input format: idperson,name,lastname,birthdate,birthplace,home,studiedat,workplaces,films,groupcode. If an attribute needs multiple inputs, just add ";" to separate them.
	 * @throws ImpulsoryAttributeRequiredException - Id is the only obligatory attribute.
	 */
	@SuppressWarnings("unchecked")
	public Person(String combinedData) throws ImpulsoryAttributeRequiredException{
		// Initializing the DataBlockADT[][] 
		attributes = (DataBlockADT<String, String>[][]) Array.newInstance(DataBlockADT[].class, NPARAMETERS-1);
		
		
		// Separating the input parameter in arrays of attributes
		String[] separatedData = combinedData.split(",");
		
		// Checks if id is empty or not
		if(separatedData[0] != "")
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
	 * Returns all the collection of DataBlockADT<String, String>[][] type. Inside of it there is all the information about that person.
	 * It's mainly use is for removing the person from the DataBlocks and then if the DataBlocks are left empty, they are removed from the main HashMap 
	 * @return attributes - DataBlock<String, String>[][]
	 */
	public DataBlockADT<String, String>[][] getDataBlocks(){
		return attributes;
	}
	
	/**
	 * It only returns the array of DataBlockADT<String, String> of the given attribute. This can be used to search for data neighbors. 
	 * @param attribute - int. This is used for selecting which attribute do we want. The Person class has public static constants for this.
	 * @return DataBlockADT<String, String>[]
	 */
	public DataBlockADT<String, String>[] getAttributesRelatedDataBlocks(int attribute){
		return attributes[attribute];
	}
	
	
	/**
	 * Returns the collection of the asked attribute.
	 * @param attribute - int. Asked attribute
	 * @return String[] - Varying size.
	 */
	public String[] getAttribute(int attribute) {
		String[] ret = null;
		
		
		if(attribute != Person.ID) {	
			
			if(attributes[attribute] != null) {
				// Creates a string array
				int size = attributes[attribute].length;
				ret = new String[size];
				
				for(int i = 0; i < size;i++)
					ret[i] = attributes[attribute][i].getKey();
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
	public GenericArrayListNode<String> getNode() {
		return personalNode;
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
		return ret+"\n";
	}
	
}
