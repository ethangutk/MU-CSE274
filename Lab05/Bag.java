

public class Bag<T> implements BagInterface<T>, CollectionInterface<T> {

	private Node<T> front;
	private Node<T> rear;
	int size;
	
	
	
	public Bag() {
		front = null;
		rear = null;
		size = 0;
	}
	
	
	
	// Attempts to add element to this collection.
	// Returns true if successful, false otherwise.
	public boolean add(T element) {
		
		Node<T> node = new Node(element);
		
		if ( size == 0) {
			front = rear = node;
		} else {
			rear.next = node;
			node.prev = rear;
			rear = node;
		}
		size++;
		
		return true;
		
		//change old rear "next value" to new element
		//change previous to old rear
		//change next to null
	}

	
	
	// Returns an element e from this collection such that e.equals(target).
	// If no such e exists, returns null.
	public T get(T target) {
		
		Node<T> nodePtr = front;
		while ( nodePtr != null) {
			if ( nodePtr.data.equals(target)) {
				return nodePtr.data;
			}
			nodePtr = nodePtr.next;
		}
		
		return null;
	}

	
	
	// Returns true if this collection contains an element e such that
	// e.equals(target) { return true; } otherwise returns false.
	public boolean contains(T target) {
		return get(target) != null;
	}

	
	
	// Removes an element e from this collection such that e.equals(target)
	// and returns true. If no such e exists, returns false.
	public boolean remove(T target) {
		return false;
	}

	
	
	// Returns true if this collection is full { return true; } otherwise, returns
	// false.
	public boolean isFull() {
		return false;
	}

	
	
	// Returns true if this collection is empty { return true; } otherwise, returns
	// false.
	public boolean isEmpty() {
		return size == 0;
	}

	
	
	// Returns the number of elements in this collection.
	public int size() {
		return size;
	}

	
	
	// If this bag is not empty, removes and returns a random element of the
	// bag { return true; } otherwise returns null.
	public T grab() {
		T result = null;
		
		//SPECIAL CASE - IF SIZE IS ZERO
		if ( size == 0) return null; 
		
		//SPECIAL CASE - IF SIZE IS 1
		else if (size == 1) {
			result = front.data;
			front = rear = null;
			return result;
		}
		
		//VARIABLES
		int randIndex = (int) (Math.random() * size) + 1;
		Node<T> nodePtr = front;
		
		//COUNTING THROUGH THE LINKED LIST
		for ( int i = 1; i < randIndex; i++) {
			nodePtr = nodePtr.next;
		}
		
		//SPECIAL CASE - IF WE REMOVE THE FRONT
		if ( nodePtr == front ) {
			result = nodePtr.data;
			front = front.next;
			front.prev = null;
			size--;
			return result;
		}
		
		//SPECIAL CASE - IF WE REMOVE THE REAR
		if ( nodePtr == rear ) {
			result = nodePtr.data;
			rear = rear.prev;
			rear.next = null;
			size--;
			return result;
		}
		
			
		nodePtr.prev.next = nodePtr.next;
		nodePtr.next.prev = nodePtr.prev;
		size--;
		
		return nodePtr.data;
	}
	
	

	// Returns a count of all elements e in this collection such that
	// e.equals(target).
	public int count(T target) {
		return 0;
	}

	
	
	// Removes all elements e from this collection such that e.equals(target)
	// and returns the number of elements removed.
	public int removeAll(T target) {
		return 0;
	}

	
	
	// Empties this bag so that it contains zero elements.
	public void clear() {

	}
	
	
	
	private class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;
		
		private Node(T item) {
			data = item;
			next = prev = null;
		}
		
		private Node( T item, Node<T> newNext, Node<T> newPrev) {
			data = item;
			next = newNext;
			prev = newPrev;
		}
	}
}





