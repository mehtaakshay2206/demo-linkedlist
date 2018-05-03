package org.ds;

import java.util.function.Predicate;

import org.ds.exception.BadRequestException;
import org.ds.exception.InvalidInputException;

/**
 * 
 */
public class MyLinkedList<T extends Comparable<T>> {

	/**
	 * Reference pointer pointing to the start of the list
	 */
	private Node<T> head;

	/**
	 * Reference pointer pointing to the end of the list
	 */
	private Node<T> tail;

	/**
	 * Contains the size of the list
	 */
	private int size;

	/**
	 * 
	 * @param data
	 *            Inserts a new node with data of type T at the beginning of the
	 *            list
	 */
	public void insertFirst(T data) {
		Node<T> node = getNode(data);

		if (size == 0) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head = node;
		}

		size++;
		node = null;
	}

	/**
	 * 
	 * @param data
	 *            Inserts a new node with data of type T at the end of the list
	 */
	public void insertLast(T data) {
		if (size == 0) {
			insertFirst(data);
		} else {
			Node<T> node = getNode(data);
			tail.next = node;
			tail = node;
			node = null;
		}
	}

	/**
	 * 
	 * @param data
	 * @param pos
	 *            - the position at which the node is to be inserted in the list
	 */
	public void insert(T data, int pos) {
		if (pos <= 0) {
			throw new InvalidInputException("Position should be greater than or equal to 1");
		}

		if (size == 0 || pos == 1) {
			insertFirst(data);
		} else {
			Node<T> temp = head;
			Node<T> node = getNode(data);

			pos--;
			while (pos > 1) {
				temp = temp.next;
				pos--;
			}
			node.next = temp.next;
			temp.next = node;
			node = null;

		}

	}

	/**
	 * 
	 * @return data of the deleted node Deletes the last element in the list
	 */
	public T deleteLast() {
		if (size == 0) {
			// TODO: Throw an exception
		}
		Node<T> t1 = head;
		Node<T> t2 = t1;
		while (t1.next != null) {
			t2 = t1;
			t1 = t1.next;
		}
		t2.next = null;
		tail = t2;

		T data = t1.data;
		t1 = null;
		size--;
		return data;
	}

	/**
	 * 
	 * @param maxData
	 *            removes all the nodes whose data is greater than maxData
	 */
	public void deleteNodeGreaterThanMaxData(T maxData) {
		Predicate<T> cond = (s1) -> {
			if (s1.compareTo(maxData) == 1)
				return true;
			else
				return false;
		};
		deleteNode(cond);
	}

	/**
	 * 
	 * @param pred
	 *            Deletes all nodes based on the predicate
	 */
	private void deleteNode(Predicate<T> pred) {
		Node<T> t1 = head;
		Node<T> t2 = t1;

		while (t1 != null) {
			if (pred.test(t1.data)) {
				t2.next = t1.next;
				t1.next = null;
				t1 = t2.next;
			} else {
				t2 = t1;
				t1 = t1.next;
			}
		}
	}

	/**
	 * Iterates the list and prints the toString() method of the data of type T
	 */
	public void display() {
		MyListIterator<T> iter = this.listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().toString());
		}
	}

	@FunctionalInterface
	private static interface BiCond<T> {
		public abstract boolean apply(T t);
	}

	public MyListIterator<T> listIterator() {
		return new MyListIterator<T>() {
			Node<T> temp = head;

			@Override
			public boolean hasNext() {
				return temp != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					new BadRequestException();
				}
				T data = temp.data;
				temp = temp.next;
				return data;
			}
		};
	}

	/**
	 * 
	 * @param data
	 * @return constructed node {@link Node} Constructs a node with data of type
	 *         T
	 */
	private Node<T> getNode(T data) {
		Node<T> node = new Node<>();
		node.data = data;
		return node;
	}

	/**
	 * 
	 * @param <T>
	 */
	public static class Node<T extends Comparable<T>> {
		/**
		 * contains the data of type T which extends Comparable interface
		 */
		T data;

		/**
		 * Pointer to the next node in the linked list
		 */
		Node<T> next;
	}

	public interface MyListIterator<T extends Comparable<T>> {
		/**
		 * 
		 * @return true if there are elements which are not iterated in the
		 *         list, false , otherwise
		 */
		public boolean hasNext();

		/**
		 * 
		 * @return data of type T returns the next element in the list
		 */
		public T next();
	}

	// TODO: Need to remove after testing
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.insertFirst(4);
		list.insertFirst(5);
		list.insertLast(15);
		list.insertLast(25);
		list.insertLast(35);
		list.insertLast(55);
		list.insertLast(95);
		System.out.println("Initial list");
		list.display();
		System.out.println("Insert 12 at 2nd position in the list");
		list.insert(12, 2);
		System.out.println("Print after adding 12 at the 2nd position");
		list.display();

		System.out.println("Delete the last element of the list");
		list.deleteLast();
		System.out.println("Print after deleting last element");
		list.display();

		System.out.println("Delete all nodes greater than 15");
		list.deleteNodeGreaterThanMaxData(15);
		System.out.println("Print after deleting all nodes greater than 15");
		list.display();
	}

}
