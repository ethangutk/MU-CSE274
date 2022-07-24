import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable> {

	// ======================================================================================
	// Properties
	private BinaryNode root;

	// ======================================================================================
	// Constructors
	public BinarySearchTree() {
		root = null;
	}

	// Constructor that takes an array of items and populates the tree
	public BinarySearchTree(T[] items) {
		for ( T item : items ) {
			add(item);
		}
	}

	// ======================================================================================
	// Methods
	public boolean add(T data) { // Implement recursively and do NOT allow duplicates
		if (this.isEmpty()) {
			root = new BinaryNode(data);
		}
		return add(root, data);
	}

	private boolean add(BinaryNode node, T newData) {
		if (node.data.compareTo(newData) == 0)
			return false;
		if (node.data.compareTo(newData) > 0) {
			if (node.left != null)
				return add(node.left, newData);
			else {
				node.left = new BinaryNode(newData);
				return true;
			}
		} else {
			if (node.right != null)
				return add(node.right, newData);
			else {
				node.right = new BinaryNode(newData);
				return true;
			}
		}
	}

	public String inOrder() {
		String result = "";

		result = inOrder(root);
		return result.trim();
	}

	private String inOrder(BinaryNode node) {
		String result = "";
		if (node != null) {
			result += inOrder(node.left);
			result += node.data.toString() + " ";
			result += inOrder(node.right);
		}
		return result;
	}

	public ArrayList<T> inOrderTraversal() {
		ArrayList<T> result = new ArrayList<T>();
		return inOrderTraversal(root, result);
	}

	private ArrayList<T> inOrderTraversal(BinaryNode node, ArrayList<T> result) {
		if (node == null)
			return null;
		inOrderTraversal(node.left, result);
		result.add(node.data);
		inOrderTraversal(node.right, result);
		return result;
	}

	public ArrayList<T> breadthFirstTraversal() {
		ArrayList<T> result = new ArrayList<T>();
		LinkedQueue<BinaryNode> queue = new LinkedQueue<BinaryNode>();
		
		if (root == null) return result;
		
		queue.enqueue(root);
		while ( ! queue.isEmpty() ) {
			BinaryNode node = queue.dequeue();
			result.add(node.data);
			if ( node.left  != null ) queue.enqueue(node.left);
			if ( node.right != null ) queue.enqueue(node.right);
		}
		
		return result;
	}

	// Returns whether the tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	// returns the largest value of all the leaves
	// If the tree is empty, throw an IllegalStateException()
	// Note, this is different than max as this is the max
	// of all leaf nodes

	public T maxLeaf() {
		if ( root == null ) return null;
		return maxLeaf(root);
	}
	
	private T maxLeaf( BinaryNode node ) {
		while ( node.right != null ) {
			node = node.right;
		}
		
		if ( node.isLeaf() ) return node.data;
		else return maxLeaf(node.left);
	}

	// ======================================================================================
	// Inner Node Class
	private class BinaryNode {
		private T data;
		private BinaryNode left, right;

		private BinaryNode(T data) {
			this.data = data;
			left = right = null;
		}

		boolean isLeaf() {
			return (left == null) && (right == null);
		}
	}
}
