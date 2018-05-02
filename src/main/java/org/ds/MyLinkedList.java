package org.ds;

public class MyLinkedList<T extends Comparable<T>> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public static class Node<T extends Comparable<T>> {
		T data;
		Node<T> next;
	}

	/**
	 * 
	 * @param data
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
	 *            in the list to insert the node
	 */
	public void insert(T data, int pos) {
		if (pos <= 0) {
			// TODO : Throw an exception.
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
	 * @return data of the deleted node
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
	 * @param data
	 */
	public void deleteNodeGreaterThanData(T data) {
		Node<T> t1 = head;
		Node<T> t2 = t1;

		while (t1 != null) {
			if (t1.data.compareTo(data) == 1) {
				t2.next = t1.next;
				t1.next = null;
				t1 = t2.next;
				size--;
			} else {
				t2 = t1;
				t1 = t1.next;
			}
		}

	}

	public void display() {
		MyListIterator<T> iter = this.listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
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
					// TODO: Throw an exception
				}
				T data = temp.data;
				temp = temp.next;
				return data;
			}

		};
	}

	private Node<T> getNode(T data) {
		Node<T> node = new Node<>();
		node.data = data;
		return node;
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.insertFirst(4);
		list.insertFirst(5);
		list.insertFirst(10);
		list.insertFirst(9);
		list.insertLast(15);
		list.insert(12, 2);
		list.display();
	}

}
