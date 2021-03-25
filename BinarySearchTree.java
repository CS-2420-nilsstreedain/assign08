package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private class BinaryNode<T extends Comparable<? super T>> {
		public T element;
		public BinaryNode<T> leftChild;
		public BinaryNode<T> rightChild;
		public BinaryNode<T> parent;

		/**
		 * Creates a new BinaryNode object.
		 * 
		 * @param element    - data element to store at this node
		 * @param leftChild  - this node's left child
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

		public String generateDot() {
			String ret = "\tnode" + element + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
			if (leftChild != null)
				ret += "\tnode" + element + ":f0 -> node" + leftChild.element + ":f1\n" + leftChild.generateDot();
			if (rightChild != null)
				ret += "\tnode" + element + ":f2 -> node" + rightChild.element + ":f1\n" + rightChild.generateDot();

			return ret;
		}

		public boolean recursiveAdd(T item) {

			if (item.compareTo(this.element) < 0) {
				if (this.leftChild == null) {
					this.leftChild = new BinaryNode<T>(item);
					this.leftChild.parent = this;
					return true;
				}
				if (this.leftChild.recursiveAdd(item))
					return true;

			}

			if (item.compareTo(this.element) > 0) {
				if (this.rightChild == null) {
					this.rightChild = new BinaryNode<T>(item);
					this.rightChild.parent = this;
					return true;
				}
				if (this.rightChild.recursiveAdd(item))
					return true;

			}

			return false;
		}

		public BinaryNode<T> recursiveFind(T item) {
			if (item.compareTo(this.element) < 0 && this.leftChild != null)
				return this.leftChild.recursiveFind(item);

			if (item.compareTo(this.element) > 0 && this.rightChild != null)
				return this.rightChild.recursiveFind(item);

			if (item.compareTo(this.element) == 0)
				return this;

			return null;
		}

		public BinaryNode<T> recursiveFirst() {
			if (this.leftChild != null)
				return this.leftChild.recursiveFirst();
			return this;
		}

		public BinaryNode<T> recursiveLast() {
			if (this.rightChild != null)
				return this.rightChild.recursiveLast();
			return this;
		}

		public void inOrder(ArrayList<T> resultArrayList) {
			if (this.leftChild != null)
				this.leftChild.inOrder(resultArrayList);

			resultArrayList.add(this.element);

			if (this.rightChild != null)
				this.rightChild.inOrder(resultArrayList);
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
	 * @param item - the item to try to add to the BinarySearchTree
	 * @return true if the BinarySearchTree does not already contain the passed in
	 *         item
	 */
	@Override
	public boolean add(Type item) {
		if (size == 0) {
			root = new BinaryNode<Type>(item);
			size++;
			return true;
		}
		if (root.recursiveAdd(item)) {
			size++;
			return true;
		}

		return false;
	}

	/**
	 * Adds each item from the collection to the BinarySearchTree. Duplicates will
	 * not be added.
	 * 
	 * @param items - the collection of items whose presence is ensured in this
	 *              BinarySearchTree
	 * @return anyAdded - true if this BinarySearchTree changed as a result of this
	 *         method call (that is, if any item in the input collection was
	 *         actually inserted); otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean anyAdded = false;

		for (Type item : items)
			if (this.add(item))
				anyAdded = true;

		return anyAdded;
	}

	/**
	 * Removes all items from this BinarySearchTree. The BinarySearchTree will be
	 * empty after this method call.
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Determines if there is an item in this BinarySearchTree that is equal to the
	 * specified item.
	 * 
	 * @param item - the item sought in this BinarySearchTree
	 * @return true if there is an item in this BinarySearchTree that is equal to
	 *         the input item; otherwise, returns false
	 */
	@Override
	public boolean contains(Type item) {
		if (size == 0)
			return false;

		if (root.recursiveFind(item) != null)
			return true;

		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this BinarySearchTree that is equal to it.
	 * 
	 * @param items - the collection of items sought in this BinarySearchTree
	 * @return true if for each item in the specified collection, there is an item
	 *         in this BinarySearchTree that is equal to it; otherwise, returns
	 *         false
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type item : items)
			if (!this.contains(item))
				return false;

		return true;
	}

	/**
	 * Returns the smallest item in this BinarySearchTree.
	 * 
	 * @throws NoSuchElementException if the BinarySearchTree is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return root.recursiveFirst().element;
	}

	/**
	 * Returns true if this BinarySearchTree contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the last (i.e., largest) item in this BinarySearchTree.
	 * 
	 * @throws NoSuchElementException if the BinarySearchTree is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return root.recursiveLast().element;
	}

	/**
	 * Ensures that this BinarySearchTree does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this BinarySearchTree
	 * @return true if this BinarySearchTree changed as a result of this method call
	 *         (that is, if the input item was actually removed); otherwise, returns
	 *         false
	 */
	@Override
	public boolean remove(Type item) {
		if (size == 0)
			return false;

		BinaryNode<Type> itemNode = root.recursiveFind(item);

		if (itemNode == null)
			return false;

		if (itemNode.leftChild == null && itemNode.rightChild == null)
			removeLeaf(itemNode);

		else if (itemNode.leftChild != null && itemNode.rightChild != null) {
			//gets the smallest node on the right side
			BinaryNode<Type> replacementNode = itemNode.rightChild.recursiveFirst();

			//sets the item node, node to delete, to the new value
			itemNode.element = replacementNode.element;
			
			//if the parent of the replacement node is NOT the node to delete,
			//then we redirect replacement node's parent to replacement node's
			//right child, as it will always have one, or it will be null.
			if (!replacementNode.parent.equals(itemNode)) {
				replacementNode.parent.leftChild = replacementNode.rightChild;
		
				//if replacement node's right child is null, replacement node's parent's left child
				//should be null
				if (replacementNode.rightChild == null)
					replacementNode.parent.leftChild = null;
				else	
				//else, if replacement node's right child exists, set its parent to replacement node's parent	
					replacementNode.rightChild.parent = replacementNode.parent;
			}
			
			//edge case for when the parent of the replacement node IS the
			//node to delete, so we don't go doing stuff on the left
			//side of item to delete where nothing should change
			else {
				//from newly deleted/changed item node, we bypass our replacement 
				//node with its child
				//can ONLY be right child from replacement because replacement is a leftmost node
				itemNode.rightChild = replacementNode.rightChild;
				//if replacement node's child is not null,
				//we set its parent to item node to ignore replacement node
				if (replacementNode.rightChild != null)
					replacementNode.rightChild.parent = itemNode;
			}
			
		} else
			bypassNode(itemNode);

		size--;
		return true;
	}

	/**
	 * Removes the specified leaf from a BinarySearchTree
	 * 
	 * @param itemNode - the leaf of the BinarySearchTree to be removed
	 */
	private void removeLeaf(BinaryNode<Type> itemNode) {
		// If the Node being removed is the root and a leaf, root is set to null
		if (itemNode.equals(root))
			root = null;
		
		// Determines whether the left of right child should be set to null
		else if (itemNode.element.compareTo(itemNode.parent.element) < 0)
			itemNode.parent.leftChild = null;
		else
			itemNode.parent.rightChild = null;
	}

	/**
	 * Bypasses a node with one child in a BinarySearchTree
	 * 
	 * @param itemNode - Node to be bypassed
	 */
	private void bypassNode(BinaryNode<Type> itemNode) {
		// Edge case for when the root is removed with bypassNode
		if (itemNode.equals(root)) {
			if (itemNode.leftChild != null) {
				root = itemNode.leftChild;
				root.parent = null;
				root.leftChild.parent = root;
			}
			else {
				root = itemNode.rightChild;
				root.parent = null;
				root.rightChild.parent = root;
			}	
		}

		// Makes sure the child of the bypassed Node is set as the correct child of the
		// parent Node
		else if (itemNode.element.compareTo(itemNode.parent.element) < 0) {

			// Checks which child exists and adds it as the child of the parent
			if (itemNode.leftChild != null) {
				itemNode.parent.leftChild = itemNode.leftChild;
				itemNode.parent.leftChild.parent = itemNode.parent;
				//makes itemNode's child's parent skip over itemNode
			}	
			else {
				itemNode.parent.leftChild = itemNode.rightChild;
				itemNode.parent.leftChild.parent = itemNode.parent;
				//makes itemNode's child's parent skip over itemNode
			}	
		} else {

			// Checks which child exists and adds it as the child of the parent
			if (itemNode.leftChild != null) {
				itemNode.parent.rightChild = itemNode.leftChild;
				itemNode.parent.rightChild.parent = itemNode.parent;
				//makes itemNode's child's parent skip over itemNode
			}	
			else {
				itemNode.parent.rightChild = itemNode.rightChild;
				itemNode.parent.rightChild.parent = itemNode.parent;
				//makes itemNode's child's parent skip over itemNode
			}	
		}
	}

	/**
	 * Removes all items in the BinarySearchTree that match items from the passed in
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean wasChanged = false;
		for (Type item : items)
			if (remove(item))
				wasChanged = true;
		return wasChanged;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> resultArrayList = new ArrayList<>();
		
		if (size == 0)
			return resultArrayList;
		
		root.inOrder(resultArrayList);
		
		return resultArrayList;
	}

	/**
	 * Write a DOT representation of this BST to file.
	 * 
	 * @param filename
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Tree {\n\tnode [shape=record]\n");

			if (root == null)
				out.println("");
			else
				out.print(root.generateDot());

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
