package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private class BinaryNode<T extends Comparable<? super T>> {
		public T element;
		public BinaryNode<T> leftChild;
		public BinaryNode<T> rightChild;
		
		/**
		 * Creates a new BinaryNode object.
		 * 
		 * @param element - data element to store at this node
		 * @param leftChild - this node's left child
		 * @param rightChild - this node's right child
		 */
		public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
			this.element = element;
			this.leftChild = left;
			this.rightChild = right;
		}
		
		/**
		 * Creates a new BinaryNode object.
		 * 
		 * @param element - data element to store at this node
		 */
		public BinaryNode(T element) {
			this(element, null, null);
		}
		
		/**
		 * Determines the number of nodes in the tree rooted at this node.
		 * 
		 * IS THIS EFFICIENT? How else can we implement size()?
		 * 
		 * @return the number of nodes in the tree
		 */
		public int size() {
			// count this node
			int size = 1; 
			
			// count all of the nodes in the left subtree
			if(leftChild != null)
				size += leftChild.size(); 

			// count all of the nodes in the right subtree
			if(rightChild != null)
				size += rightChild.size(); 

			return size;
		}
		
		/**
		 * Generate a copy of the tree rooted at this node.
		 * 
		 * @return the tree copy
		 */
		public BinaryNode<T> duplicate() {
			BinaryNode<T> copyLeft = null;
			
			// get copy of left subtree
			if(leftChild != null)
				copyLeft = leftChild.duplicate(); 

			// get copy of right subtree
			BinaryNode<T> copyRight = null;
			if(rightChild != null)
				copyRight = rightChild.duplicate(); 

			// combine left and right in a new node w/ element
			return new BinaryNode<T>(this.element, copyLeft, copyRight);
		}
		
		public String generateDot() {
			String ret = "\tnode" + element + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
			if(leftChild != null)
				ret += "\tnode" + element + ":f0 -> node" + leftChild.element + ":f1\n" + leftChild.generateDot();
			if(rightChild != null)
				ret += "\tnode" + element + ":f2 -> node" + rightChild.element + ":f1\n" + rightChild.generateDot();

			return ret;
		}
		
		public BinaryNode<T> recursiveAdd(T item) {
			if (item.compareTo(this.element) < 0) {
				if (this.leftChild == null)
					return this;
				this.leftChild.recursiveAdd(item);
			}
			if (item.compareTo(this.element) > 0) {
				if (this.rightChild == null)
					return this;
				this.rightChild.recursiveAdd(item);
			}
			return null;
		}
	}
	
	private BinaryNode<Type> root;
	
	private int size;
	
	/**
	 * Empty constructor for a new BinarySearchTree
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Adds the passed in item to the BinarySearchTree in its ordered place.
	 * Duplicates are not allowed.
	 * 
	 * @param item - the item to try to add to the tree
	 * @return true if the tree does not already contain the passed in item
	 */
	@Override
	public boolean add(Type item) {
		BinaryNode<Type> temp = root.recursiveAdd(item);
		
		if (item.compareTo(temp.element) < 0) {
			temp.leftChild = new BinaryNode<Type>(item);
			return true;
		}
		if (item.compareTo(temp.element) > 0) {
			temp.rightChild = new BinaryNode<Type>(item);
			return true;
		}
	
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
