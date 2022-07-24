import java.util.NoSuchElementException;
import java.util.stream.Stream;

// Put your prologue comments here

public class LinkedAlgorithms {

	private class Node {
		private String data;
		private Node next;

		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}

	public Node head;
	public int numItems;

	/**
	 * Creates the empty list.
	 */
	public LinkedAlgorithms() {
		head = null;
		numItems = 0;
	}

	/**
	 * Creates a list containing all the elements of the passed array.
	 * {@code data[0]} will be the first element of the list, {@code data[1]} will
	 * be the second element of the list, and so on.
	 * 
	 * @param data The array of values
	 * @throws NullPointerException - data is null
	 */
	public LinkedAlgorithms(String[] data) {

		if (data == null)
			throw new NullPointerException();
		if (data.length == 0) {
			head = null;
			numItems = 0;
		}
		for (int i = 0; i < data.length; i++) {
			this.insertLast(data[i]);
		}

	}

	/**
	 * Constructs a deep copy of the linked list {@code original}
	 * 
	 * @param original The list to be copied
	 * @throws NullPointerException - original is null
	 */
	public LinkedAlgorithms(LinkedAlgorithms original) {

		if (original.head == null) {
			head = null;
			numItems = 0;
		} else {
			head = original.head;
			numItems = 1;
		}

		Node parameterPointer = original.head;
		Node nodePtr = head;
		while (parameterPointer.next != null) {
			nodePtr.next = parameterPointer.next;
			numItems++;

			nodePtr = nodePtr.next;
			parameterPointer = parameterPointer.next;
		}
	}

	/**
	 * Returns array with all the elements.
	 * 
	 * @return Array containing all elements.
	 */
	public String[] toArray() {
		String[] returnArray = null;
		if (numItems > 0)
			returnArray = new String[numItems];

		Node nodePtr = head;

		for (int i = 0; i < numItems; i++) {
			returnArray[i] = nodePtr.data;

			nodePtr = nodePtr.next;
		}

		return returnArray;
	}

	/**
	 * Formats the elements in the list using leading and ending brackets (i.e.,
	 * []), with the values comma separated. There will be one space between each of
	 * these but none at the beginning nor at the end. Some examples: if the list is
	 * empty, toString() gives [] if the list has these three elements in this
	 * order: "hello", "world", "everyone", then the result should be [hello, world,
	 * everyone]
	 */
	@Override
	public String toString() {
		String result = "[";

		Node nodePtr = head;

		while (nodePtr != null) {
			result = result + nodePtr.data + " ";

			nodePtr = nodePtr.next;
		}
		if (numItems > 0) {
			result = result.substring(0, result.length() - 1);
		}

		result += "]";
		return result;
	}

	/**
	 * Returns the number of items in the list
	 * 
	 * @return Number of items in the list
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Determines if two lists contain exactly the same values, in exactly the same
	 * order.
	 * 
	 * @return {@code true} if and only if obj is an list that is equivalent to the
	 *         incoming list.
	 */
	public boolean equalsLinkedList(LinkedAlgorithms inputList) {
		if (inputList.size() != this.size())
			return false;

		boolean equals = true;
		Node thisNodePtr = head;
		Node inputNodePtr = inputList.head;

		while (inputNodePtr != null) {
			if (!inputNodePtr.data.equals(thisNodePtr.data)) {
				equals = false;
				break; 
			}

			inputNodePtr = inputNodePtr.next;
			thisNodePtr = thisNodePtr.next;
		}

		return equals;
	}

	/**
	 * Determines if {@code key} is in the linked list.
	 * 
	 * @param key The value to be found
	 * @return true if and only if {@code key} is in the list
	 */
	public boolean contains(String key) {
		return (this.find(key) != -1);
	}

	/**
	 * Determines the index of {@code key}. -1 is returned if the value is not
	 * present in the linked list. If {@code key} is present present more than once,
	 * the first index is returned.
	 * 
	 * @param key The value to be found
	 * @return The index of the {@code key}
	 */
	public int find(String key) {
		Node nodePtr = head;
		boolean found = false;
		int i;

		for (i = 0; i < numItems; i++) {
			if (nodePtr.data.endsWith(key)) {
				found = true;
				break;
			}

			nodePtr = nodePtr.next;
		}

		// return results
		if (found == false)
			return -1;
		else
			return i;
	}

	/**
	 * Returns the value of the first element of the list.
	 * 
	 * @return The first element of the list.
	 * @throws NoSuchElementException the list is empty
	 */
	public String getFirst() {
		if (numItems == 0)
			throw new NoSuchElementException();
		return head.data;
	}

	/**
	 * Returns the value of the last element of the list.
	 * 
	 * @return The last element of the list.
	 * @throws NoSuchElementException the list is empty
	 */
	public String getLast() {
		if (numItems == 0)
			throw new NoSuchElementException();

		Node nodePtr = head;

		while (nodePtr.next != null) {
			nodePtr = nodePtr.next;
		}
		return nodePtr.data;
	}

	/**
	 * Returns the value of the {@code ith} element of the list (0 based).
	 * 
	 * @param i The target index
	 * @return The value of the ith element of the list.
	 * @throws IndexOutOfBoundsException {@code i} is illegal
	 */
	public String getAt(int index) {

		if (index < 0 || index >= numItems)
			throw new IndexOutOfBoundsException();

		Node nodePtr = head;
		for (int i = 0; i < index; i++) {
			nodePtr = nodePtr.next;
		}

		return nodePtr.data;
	}

	/**
	 * Adds {@code data} to the beginning of the list. All other values in the list
	 * remain but they are "shifted to the right."
	 * 
	 * @param data The value to add to the list
	 */
	public void insertFirst(String data) {
		Node node = new Node(data);
		node.next = head;
		head = node;
		numItems++;
	}

	/**
	 * Adds {@code data} to the end of the list. All other values in the list remain
	 * in their current positions.
	 * 
	 * @param data The value to add to the list
	 */
	public void insertLast(String data) {

		Node node = new Node(data);

		if (head == null)
			head = node;
		else {
			Node nodePtr = head;

			while (nodePtr.next != null) {
				nodePtr = nodePtr.next;
			}
			nodePtr.next = node;
		}

		numItems++;
	}

	/**
	 * Adds data to a specific spot in the list. The values in the list remain
	 * intact but {@code data} is inserted in the list at position {@code i}. When
	 * {@code i=0}, the result is the same as {@code insertFirst}.
	 * 
	 * @param i    The target index
	 * @param data The value to add to the list
	 * @throws IndexOutOfBoundsException {@code i} is illegal
	 */
	public void insertAt(int index, String data) {
		if (index < 0 || index > numItems)
			throw new IndexOutOfBoundsException();
		else if (index == 0)
			this.insertFirst(data);
		else if (index == numItems)
			this.insertLast(data);
		else {
			Node nodePtr = head;
			Node prev = null;

			for (int i = 0; i <= index; i++) {
				// inserts new node
				if (i == index) {
					Node newNode = new Node(data);

					newNode.next = nodePtr;
					prev.next = newNode;
					numItems++;
				}

				// increments
				prev = nodePtr;
				nodePtr = nodePtr.next;
			}
		}

	}

	/**
	 * Adds {@code newData} the position immediately preceding {@code existingData}.
	 * If the {@code existingData} appears multiple times, only the first one is
	 * used.
	 * 
	 * @param newData      The value to add to the list
	 * @param existingData The value used to control where insertion is to take
	 *                     place
	 * @throws NoSuchElementException {@code existingData} is not in the list
	 */
	public void insertBefore(String newData, String existingData) {
		Node nodePtr = head;
		Node prev = null;

		while (nodePtr != null) {
			if (nodePtr.data.equals(existingData))
				break;
			prev = nodePtr;
			nodePtr = nodePtr.next;
		}

		if (nodePtr == null)
			throw new NoSuchElementException();

		// create new node
		Node node = new Node(newData);

		// insert node
		node.next = nodePtr;
		if (prev != null)
			prev.next = node;
		else
			head = node;
		numItems++;
	}

	/**
	 * Adds {@code newData} the position immediately after {@code existingData}. If
	 * the {@code existingData} appears multiple times, only the first one is used.
	 * 
	 * @param newData      The value to add to the list
	 * @param existingData The value used to control where insertion is to take
	 *                     place
	 * @throws NoSuchElementException {@code existingData} is not in the list
	 */
	public void insertAfter(String newData, String existingData) {
		Node nodePtr = head;
		Node next = nodePtr.next;

		while (nodePtr.next != null) {
			if (nodePtr.data.equals(existingData))
				break;
			nodePtr = next;
			next = next.next;
		}

		if (nodePtr == null)
			throw new NoSuchElementException();

		// create new node
		Node node = new Node(newData);

		// insert node
		nodePtr.next = node;
		if (next != null)
			node.next = next;
		numItems++;
	}

	/**
	 * Removes the first element of the list. The remaining elements are kept in
	 * their existing order.
	 * 
	 * @return The value removed from the list
	 * @throws NoSuchElementException if the list is empty.
	 */
	public String removeFirst() {
		if (numItems == 0)
			throw new NoSuchElementException();
		String returnValue = head.data;

		head = head.next;
		numItems--;
		return returnValue;
	}

	/**
	 * Removes the last element of the list. The remaining elements are kept in
	 * their existing order.
	 * 
	 * @return The value removed from the list
	 * @throws NoSuchElementException if the list is empty.
	 */
	public String removeLast() {
		String returnValue = "";

		if (numItems == 0)
			throw new NoSuchElementException();
		else if (numItems == 1) {
			returnValue = head.data;
			head = null;
		} else {
			Node nodePtr = head;
			Node prev = null;

			while (nodePtr != null) {
				if (nodePtr.next == null) {
					returnValue = nodePtr.data;
					prev.next = null;
				}
				prev = nodePtr;
				nodePtr = nodePtr.next;
			}
		}

		numItems--;

		return returnValue;
	}

	/**
	 * Removes the ith element of the list. The remaining elements are kept in their
	 * existing order.
	 * 
	 * @param i The target index
	 * @return The value removed from the list
	 * @throws IndexOutOfBoundsException i does not represent a legal index
	 */
	public String removeAt(int i) {
		String returnValue = "";

		if (i < 0 || i > numItems - 1)
			throw new IndexOutOfBoundsException();
		else if (i == 0) {
			returnValue = head.data;
			head = head.next;
		} else {
			Node nodePtr = head;
			Node prev = null;

			for (int counter = 0; counter <= i; counter++) {
				if (counter == i) {
					returnValue = nodePtr.data;
					prev.next = nodePtr.next;
				}
				prev = nodePtr;
				nodePtr = nodePtr.next;
			}
		}
		numItems--;
		return returnValue;
	}

	/**
	 * Removes the first occurrence of data in the linked list.
	 * 
	 * @param data The value to be removed.
	 * @return {@code true} if and only if {@code data} was removed
	 */
	public boolean removeFirstOccurrenceOf(String data) {
		if (head.data.equals(data)) {
			if (head.next == null)
				head = null;
			else
				head = head.next;
			numItems--;
			return true;
		}

		Node nodePtr = head;
		Node prev = null;
		
		while (nodePtr != null) {
			if (nodePtr.data.equals(data) && nodePtr.next == null) {
				prev.next = null;
				return true;
			} else if (nodePtr.data.equals(data)) {
				prev.next = nodePtr.next;
				return true;
			}
			prev = nodePtr;
			nodePtr = nodePtr.next;
		}
		return false;
	}

	/**
	 * Removes the all occurrence of {@code data} in the linked list.
	 * 
	 * @param data The value to be removed.
	 * @return The number of times {@code data} was removed
	 */
	public int removeAllOccurrencesOf(String data) {
		Node nodePtr = head;
		Node prev = null;
		int  numTimesRemoved = 0;
		
		while (nodePtr != null) {
			if (nodePtr == head && nodePtr.next != null && nodePtr.data.equals(data)) {
				nodePtr = nodePtr.next;
				head = head.next;
				numTimesRemoved++;
			} else if (nodePtr == head && nodePtr.next == null && nodePtr.data.equals(data)) {
				head = null;
				numTimesRemoved++;
				return numTimesRemoved; //list is empty so return numTimesRemoved
			} else if (nodePtr.data.equals(data) && nodePtr.next == null) {
				prev.next = null;
				numTimesRemoved++;
				return numTimesRemoved; //reached last item in the list so return numTimesRemoved
			} else if (nodePtr.data.equals(data)) {
				prev.next = nodePtr.next;
				nodePtr = nodePtr.next; //increment
				numTimesRemoved++;
			} else {
				prev = nodePtr;
				nodePtr = nodePtr.next;
			}
			
		}
		
		return numTimesRemoved;
	}

	/**
	 * Reverses the elements in the incoming linked list.
	 */
	public void reverse() {
		Node nodePtr = head;
		Node prev = null;
		Node next = null;

		while (nodePtr != null) { 
			next = nodePtr.next; // stores next value

			if (nodePtr.next == null) // changes head to the last value
				head = nodePtr;

			nodePtr.next = prev; // changes current node.next to previous
			prev = nodePtr; // changes previous to nodeptr
			nodePtr = next; // changes nodeptr to stored next
		}
	}

	/**
	 * Puts all the elements in the list to uppercase.
	 */
	public void toUpper() {
		Node nodePtr = head;

		while (nodePtr != null) {
			nodePtr.data = nodePtr.data.toUpperCase();
			nodePtr = nodePtr.next;
		}
	}

	/**
	 * Returns the concatenation of all strings, from left to right. No extra spaces
	 * are inserted.
	 * 
	 * @return Concatenation of all string in the list
	 */
	public String getConcatenation() {
		Node nodePtr = head;
		String result = "";

		while (nodePtr != null) {
			result += nodePtr.data;
			nodePtr = nodePtr.next;
		}
		return result;
	}

	/**
	 * Returns the alphabetically last value in the list.
	 * 
	 * @return The alphabetically last value in the list
	 * @throws NoSuchElementException list is empty
	 */
	public String getAlphabeticallyLast() {
		if (numItems == 0)
			throw new NoSuchElementException();

		String[] array = this.toArray();
		array = Stream.of(array).sorted().toArray(String[]::new);

		return array[array.length - 1];
	}

	/**
	 * Returns the index where the alphabetically last value value resides. If this
	 * value appears multiple times, the first occurrence is used.
	 * 
	 * @return Index value of where maximum value resides
	 * @throws NoSuchElementException list is empty
	 */
	public int indexOfAlphabeticallyLast() {
		String[] array = this.toArray();
		int result = 0;
		Node nodePtr = head;

		// sort array
		array = Stream.of(array).sorted().toArray(String[]::new);

		// look for index
		for (int i = 0; i < numItems; i++) {
			if (nodePtr.data.equals(array[array.length - 1]) == true)
				result = i;
			nodePtr = nodePtr.next;
		}

		return result;
	}

	/*
	 * Determines if the two list contain elements that have exactly the same value,
	 * with the same list sizes, but with the elements perhaps in different order.
	 * 
	 * @returns true only if the two lists are permutations of one another.
	 */
	public boolean anagrams(LinkedAlgorithms other) {
		if (other.size() != size())
			return false;
		boolean equals = true;

		// create and sort array
		String[] thisArray = this.toArray();
		String[] otherArray = other.toArray();
		thisArray = Stream.of(thisArray).sorted().toArray(String[]::new);
		otherArray = Stream.of(otherArray).sorted().toArray(String[]::new);

		for (int i = 0; i < thisArray.length; i++) {
			if (!thisArray[i].equals(otherArray[i])) {
				equals = false;
				break;
			}
		}

		return equals;
	}
}
