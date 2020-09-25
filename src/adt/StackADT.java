package adt;

public interface StackADT<T> {

	/**
	 * Pushes given element into the stack
	 * @param T element - This element goes on top of the stack 
	 */
	public void push(T element);
	
	/**
	 * Returns and removes the top element from the stack if there's any
	 * @return T - Top stack element
	 */
	public T pop();
	
	/**
	 * Returns the top element from the stack if there's any
	 * @return T - Top stack element
	 */
	public T peek();
	
	/**
	 * Gives the size of the stack
	 * @return int - Size of the stack
	 */
	public int size();
	
	
	/**
	 * Whether is empty or not
	 * @return boolean 
	 */
	public boolean isEmpty();
	
	public String toString();
	
}
