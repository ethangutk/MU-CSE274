/**
 * 
 * @author Professor J. Kiper
 *
 * @date September 3, 2020
 * 
 * Purpose: this is a utility class to implement a generic linked list that can be used
 * 			to implement other complex data structures like stacks, queues, etc.
 * Course: CSE 274 Data Abstraction and Data Structures
 * 
 */


public class LinkedList<T> {

	// inner Node class. Make it private:
	// Outer LinkedList274 class has free access to the inner class instance
	// variables.

	private class Node<T>  {
		protected T data;
		protected Node<T> next;

		Node(T data) {
			this.data = data;
			next = null;
		}
		public void setData(T data) {
			this.data = data;
		}
		public T getData() {
			return this.data;
		}
		public void setLink(Node<T> next) {
			this.next = next;
		}
		public Node<T> getLink() {
			return this.next;
		}
	}

	private int size;
	private Node<T> head;

	/*
	 * Constructs an empty list
	 */
	public LinkedList() {
		size = 0;
		head = null;
	}

	/*
	 * Returns a string representation of this collection.
	 */
	@Override
	public String toString() {
		String result = "";
		Node<T> nodePtr = head;
		while ( nodePtr != null) {
			result += nodePtr.data + " ";
			nodePtr = nodePtr.next;
		}
		result = result.trim();
		
		return result;
	}

	/*
	 * Appends the specified element to the front of this list.
	 */
	public void addFront(T item) {
		Node<T> node = new Node<T>(item);
		node.next = head;
		head = node;
		size++;
	}

	/*
	 * Appends the specified element to the front of this list.
	 */
	public void addRear(T item) {
		Node<T> node = new Node<T>(item);
		Node<T> nodePtr = head;
		
		if (head == null) {
			head = node;
		} else {
			while (nodePtr.next != null) {
				nodePtr.next = node;
			}
			nodePtr.next = node;
		}
		size++;
	}

		/*
	 * Returns the number of elements in this list.
	 */
	public int size() {
		return size;
	}

	/*
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/*
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(String item) {
		return indexOf(item) != -1;
	}

	/*
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 */
	public int indexOf(String item) {
		Node nodePtr = head;
		int index = 0;
		while ( nodePtr != null) {
			if ( nodePtr.data.equals(item)) return index;
			nodePtr = nodePtr.next;
			index++;
		} 
		
		return -1;
	}

	
	
	//Methods below will not be graded? Do in freetime for practice.
	
	
	
	/*
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 */
	public int lastIndexOf(String item) {
		// you complete this
		// strategy: you don't need to move backward through the list.
		// Instead, move forward through the list, and check every node.
		// Keep track of the last location where you found a match.
		return -1;
	}

	/*
	 * Returns the element at the specified position in this list. Throws:
	 * IndexOutOfBoundsException - if the index is out of range (index < 0 || index
	 * >= size())
	 */
	public String get(int index) {
		// you complete this
		return null;
	}

	/*
	 * Replaces the element at the specified position in this list with the
	 * specified element. Returns: the element previously at the specified position
	 * Throws: IndexOutOfBoundsException - if the index is out of range (index < 0
	 * || index >= size())
	 */
	public String set(int index, String value) {
		// you complete this
		// This does not require creating a new node. Just change the data
		// in the existing node.
		return null;
	}

	/*
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns:
	 * the element that was removed from the list Throws: IndexOutOfBoundsException
	 * - if the index is out of range (index < 0 || index >= size())
	 */
	public String remove(int index) {
		// fill in together. We throw an IndexOutOfBoundsException for a bad index.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}

		return null;

	}

}