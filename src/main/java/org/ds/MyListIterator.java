package org.ds;

public interface MyListIterator<T extends Comparable<T>> {

	/**
	 * 
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * 
	 * @return
	 */
	public T next();
}
