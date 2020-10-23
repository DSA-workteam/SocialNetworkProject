package abstractDataTypesImplemented;

import java.util.Iterator;

import abstractDataTypesPackage.StackADT;
/**
 * Implementation of Stack abstract data type. This is the class for the stack of type T implemented with an array.
 * @author Borja Moralejo Tobajas
 *
 * @param <T> T type
 */
public class GenericArrayStack <T> implements StackADT<T>,Iterable<T>{

	private T[] collection;
	private static final int DEFAULT_SIZE = 256;
	private int top, actualSize;
	
	/**
	 * Default constructor
	 */
	public GenericArrayStack() { this(DEFAULT_SIZE);	}
	
	/**
	 * Constructor with size parameter
	 * @param size - int. Initial and/or final size of the array
	 */
	public GenericArrayStack(int size) {
		collection = (T[]) (new Object[size]);
		top = 0;
		actualSize = size;
	}
	
	@Override
	public void push(T element) { collection[top++] = element; }

	@Override
	public T pop() { return collection[--top]; }

	@Override
	public T peek() { return collection[top-1];	}

	@Override
	public int size() { return top;	}

	@Override
	public boolean isEmpty() { return top == 0;}



	@Override
	public Iterator<T> iterator() { return new GenericArrayStackIterator();	}
	
	
	/**
	 * Private Iterator class of GenericArrayStack. Implements Iterator<T>
	 * @author Borja Moralejo Tobajas
	 *
	 */
	private class GenericArrayStackIterator implements Iterator<T>{
		
		private int i = top;
		@Override
		public boolean hasNext() { return i > 0; }

		@Override
		public T next() { return collection[--i]; }
		
	}
 
}