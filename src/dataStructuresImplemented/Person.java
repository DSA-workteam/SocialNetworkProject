package dataStructuresImplemented;


import java.lang.reflect.Array;


import abstractDataTypesImplemented.GenericArrayListDataBucket;
import abstractDataTypesImplemented.GenericArrayListNode;
import abstractDataTypesImplemented.GenericBinaryTreeNode;
import abstractDataTypesPackage.DataBucketADT;
import abstractDataTypesPackage.NodeADT;
import abstractDataTypesPackage.NodeADT2;
import enums.PersonAttributesEnum;
import exceptions.ImpulsoryAttributeRequiredException;
/**
 * This class is the main data object that our project uses.
 * @author Imanol Maraña Hurtado and Borja Moralejo Tobajas
 *
 */
public class Person implements Comparable<Person>{
	
	
	private DataBucketADT<String, String>[][] attributes;
	private String id;
	private NodeADT2<String> personalNode;
	
	/**
	 * Constructor of Person class. It uses unchecked casting.
	 * @param combinedData - String. Input format: idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode. If an attribute needs multiple inputs, just add ";" to separate them.
	 * @throws ImpulsoryAttributeRequiredException - Id is the only obligatory attribute.
	 */
	@SuppressWarnings("unchecked")
	public Person(String combinedData) throws ImpulsoryAttributeRequiredException{
		// Initializing the DataBucketADT[][] 
		attributes = (DataBucketADT<String, String>[][]) Array.newInstance(DataBucketADT[].class, PersonAttributesEnum.values().length-1);
		
		
		// Separating the input parameter in arrays of attributes
		String[] separatedData = combinedData.split(",");
		
		// Checks if id is empty or not
		if(!separatedData[0].equals(""))
			id = separatedData[0];
		else throw new ImpulsoryAttributeRequiredException("Id required");
		
		// Main loop for setting all attributes
		for(int i =1;i < separatedData.length;i++) {
			if(!separatedData[i].equals("")) {
				// Separating each individual attribute in the multiple inputs. For example, multiple favorite movies
				String[] attributesData = separatedData[i].split(";");
				
				// Limiting number of attributes
				int nOfAttributes = attributesData.length;
				switch(PersonAttributesEnum.values()[i]) {
				case BIRTHDATE:
					if(nOfAttributes > 1)
						nOfAttributes = 1;
					break;
				case BIRTHPLACE:
					if(nOfAttributes > 1)
						nOfAttributes = 1;
					break;
				case GENDER:
					if(nOfAttributes > 1)
						nOfAttributes = 1;
					break;
					
				default:
					break;
				}
				
				// Initializing the array with the same size as different attribute choices are
				if(nOfAttributes != 0) {
				
					attributes[i-1] = (DataBucketADT<String, String>[]) Array.newInstance(DataBucketADT.class,nOfAttributes);
					
					
					
					// This loop assigns each attribute into each array position
					for(int j = 0; j < nOfAttributes;j++)						
						attributes[i-1][j] = new GenericArrayListDataBucket<String, String>(attributesData[j]);
				}
			}
		}
		
		
		personalNode = new GenericBinaryTreeNode<String>(id);
		
	}
	
	/**
	 * Creates a reciprocal link between the person and the given person
	 * @param p - {@link Person}
	 */
	public void createRelationshipWith(Person p) {
		
		personalNode.link(p.getAttribute(PersonAttributesEnum.ID)[0]);
		p.getNode().link(id);
	}
	
	
	/**
	 * Returns all the collection of DataBucketADT&lt;String, String&gt;[][] type. Inside of it there is all the information about that person.
	 * It's mainly use is for removing the person from the DataBuckets and then if the DataBuckets are left empty, they are removed from the main HashMap 
	 * @return attributes - DataBucket&lt;String, String&gt;[][]
	 */
	public DataBucketADT<String, String>[][] getDataBuckets(){
		return attributes;
	}
	
	/**
	 * It only returns the array of DataBucketADT&lt;String, String&gt; of the given attribute. This can be used to search for data neighbors. 
	 * @param attribute - {@link PersonAttributesEnum}. This is used for selecting which attribute do we want. Use the enumerator for selecting the attribute.
	 * @return {@link DataBucketADT}[]
	 */
	public DataBucketADT<String, String>[] getAttributesRelatedDataBuckets(PersonAttributesEnum attribute){
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
	public NodeADT2<String> getNode() {
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
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * Given an attribute, returns a string with all the parameters of that attribute
	 * @param attribute Attribute wanted to print parameters from
	 * @return String with all the parameters of the attribute in String form
	 */
	public String attributeToString(PersonAttributesEnum attribute) {
		String ret;
		String[] selAttribute = getAttribute(attribute);
		if (selAttribute.length == 0){ // If the attribute is null
			ret = "-";
		}
		else // If the attribute is not null
			ret = selAttribute[0]; 
			for (int i = 0; i < selAttribute.length - 1; i++)
				ret += "; " + selAttribute[i + 1];
		return (ret);
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

	@Override
	public int compareTo(Person o) {
		
		return id.compareTo(o.id);//new PersonComparators.ByBirthdateSurnameAndName().compare(this, o);
	}
	
}
