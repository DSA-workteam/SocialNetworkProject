package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Iterator;

import adt.NodeADT;
import exceptions.ElementNotFoundException;

/**
 * 
 * @author Imanol Maraña Hurtado
 *
 * @param <T> Generic type
 */
public class GenericArrayListNode<T> implements NodeADT<T>{

	private T content;
	private int count;
	private ArrayList<NodeADT<T>> nodes;
	
	
	/**
	 * Main constructor of the class
	 * @param element Element of the node
	 */
	public GenericArrayListNode(T element) {
		content = element;
		count = 0;
		nodes = new ArrayList<NodeADT<T>>();
	}
	
	
	/**
	 * Constructor of the class without parameters
	 */
	public GenericArrayListNode() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public T getContent() {
		return content;
	}

	@Override
	public void link(NodeADT<T> node) {
		
		int i = 0;		
		boolean found = false;
		
		while(i < count && !found) {
			if(content.equals(nodes.get(i).getContent()))
				found = true;
			i++;
		}
			if(!found) {
				nodes.add(node);
				count++;
			}
		
	}
	
	@Override
	public T[] getLinkedNodes() {
		T[] nodesInfo = (T[]) new String[getLinkNumber()];
		
		for(int i = 0; i < getLinkNumber(); i++) {
			nodesInfo[i] = nodes.get(i).getContent();
		}
		
		return nodesInfo;
	}

	@Override
	public int getLinkNumber() {
		return count;
	}

	@Override
	public boolean unlink(NodeADT<T> node) throws ElementNotFoundException{
		
		int i = 0;		
		boolean found = false;
		
		while(i < count && !found) {
			if(content.equals(nodes.get(i).getContent())) {
				found = true;
				nodes.remove(i);
				count--;
			}			
			i++;
		}
		
		if(!found)
			throw new ElementNotFoundException("There isn't a link with that node");
		
		return found;
		
	}

}
