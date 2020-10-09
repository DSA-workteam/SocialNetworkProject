package dataStructuresImplemented;

import abstractDataTypesImplemented.GenericArrayListNode;
import adt.NodeADT;

/**
 * This is the clas that 
 * @author IMANOL
 *
 */
public class RelationshipStructure {
	
	private NodeADT<String> nodes;
	
	/**
	 * Constructor of the class
	 */
	public RelationshipStructure() {
		nodes = new GenericArrayListNode<String>();
	}
}
