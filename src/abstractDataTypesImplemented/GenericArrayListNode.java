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
		
		if(count == 0) {
			nodes.add(node);
			count++;
		}
		else if(count == 1) {
			if(!nodes.get(0).getContent().equals(node.getContent())) {
				System.out.println(nodes.get(0).getContent().toString().compareTo(node.getContent().toString()));
				if(0 < nodes.get(0).getContent().toString().compareTo(node.getContent().toString()))
					nodes.add(0, node);
				else
					nodes.add(node);
				System.out.println(nodes.get(0).getContent().toString().compareTo(nodes.get(1).getContent().toString()));
				count++;
			}
		}
		else {
			int min = 0;
			int max = count;
			int i = count/2;
			boolean found = false;
			
			while(max - min > 2 && !found) {
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
					nodes.add(i,node);
				}
				else if( 0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
					min = i;
					i += (max-min)/2;
				}
				else {
					max = i;
					i -= (max-min)/2;
				}
				
			}
			
			if(!found)
				if(!nodes.get(i).getContent().equals(node.getContent())) {
					if(0 < nodes.get(i-1).getContent().toString().compareTo(node.getContent().toString()) && 0 < nodes.get(i).getContent().toString().compareTo(node.getContent().toString()))
						nodes.add(i-1, node);
					else if(0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString()))
						nodes.add(i+1, node);
					else
						nodes.add(i,node);
					count++;
				}
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
		
		boolean found = false;
		int i = 0;
		
		if(count == 1) {
			if(nodes.get(0).getContent().equals(node.getContent())) {
				found = true;
			}
		}
		else if(count > 1) {
			int min = 0;
			int max = count;
			i = count/2;
			while(max - min > 2 && !found) {
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
				}
				else if(0 > nodes.get(i).getContent().toString().compareTo(node.getContent().toString())) {
					min = i;
					i += (max-min)/2;
				}
				else {
					max = i;
					i -= (max-min)/2;
				}
			}
			if(!found)
				if(nodes.get(i).getContent().equals(node.getContent())) {
					found = true;
				}
				else if(nodes.get(i-1).getContent().equals(node.getContent())){
					found = true;
					i = i-1;
				}
				else if(nodes.get(i+1).getContent().equals(node.getContent())) {
					found = true;
					i = i+1;
				}
		}
		
		if (!found)
			throw new ElementNotFoundException("That node is not between the linked nodes");
		else
			nodes.remove(i);
			count--;
		
		return found;
		
	}

}
