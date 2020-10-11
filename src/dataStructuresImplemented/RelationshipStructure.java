package dataStructuresImplemented;

import abstractDataTypesImplemented.GenericArrayListNode;
import adt.NodeADT;

/**
 * This is the class that 
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
