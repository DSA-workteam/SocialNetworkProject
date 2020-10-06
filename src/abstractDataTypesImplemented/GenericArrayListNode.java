package abstractDataTypesImplemented;

import java.util.ArrayList;
import java.util.Iterator;

import adt.NodeADT;
// TODO imoke
public class GenericArrayListNode<T> implements NodeADT<T>{

	private T content;
	private int count;
	private ArrayList<NodeADT<T>> nodes;
	
	public GenericArrayListNode(T element) {
		content = element;
		count = 0;
	}
	
	
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
	public int getLinkNumber() {
		return count;
	}

	@Override
	public boolean unlink(NodeADT<T> node) {
		
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
		
		return found;
		
	}

}
