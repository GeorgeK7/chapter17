import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {
	Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
	public static class BinaryTreeNode<T> {
		// Contains the value of the node
		private T value;
		// Shows whether the current node has parent
		boolean hasParent;
		// Contains the left child of the node
		private BinaryTreeNode<T> leftChild;
		// Contains the right child of the node
		private BinaryTreeNode<T> rightChild;
		/**
		 * Constructs a binary tree node.
		 * @param value - the value of the node.
	 	   @param leftChild - the left child of the node.
		 * @param rightChild - the right child of the node.
		 */
		public BinaryTreeNode(T value, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
			if (value == null) {
				throw new IllegalArgumentException("Cannot insert null value!");
			}
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		/**
		 * Constructs a binary tree node with no children.
		 * @param value - the value of the node.
		 */
		public BinaryTreeNode(T value) {
			this(value, null, null);
		}
		/**
		 * @return the value of the node.
		 */
		public T getValue() {
			return this.value;
		}
		/**
		 * Sets the value of the node.
		 * @param value - the value to be set.
		 */
		public void setValue(T value) {
			this.value = value;
		}
		/**
		 * @return the left child or null if it does not exists.
		 */
		public BinaryTreeNode<T> getLeftChild() {
			return this.leftChild;
		}
		/**
		 * Sets the left child.
		 * @param value - the new left child to be set.
		 */
		public void setLeftChild(BinaryTreeNode<T> value) {
			if (value == null || value.hasParent) {
				throw new IllegalArgumentException();
			}
			value.hasParent = true;
			this.leftChild = value;
		}
		/**
		 * @return the right child or null if it does not exists.
		 */
		public BinaryTreeNode<T> getRightChild() {
			return this.rightChild;
		}
		/**
		 * Sets the right child.
		 * @param value - the new right child to be set.
		 */
		public void setRightChild(BinaryTreeNode<T> value) {
			if (value == null || value.hasParent) {
				throw new IllegalArgumentException();
			}
			value.hasParent = true;
			this.rightChild = value;
		}
		public boolean isLeaf(BinaryTreeNode<T> value2) {
	        if(value2.getRightChild() == null && value2.getLeftChild() == null){
	           return true;
	        }
	        return false;
		}
	}
// The root of the tree
	public BinaryTreeNode<T> root;
	/**
	 * Constructs the tree.
	 * @param value - the value of the node.
	 * @param children - the children of the node.
	 */
	public BinaryTree(T value, BinaryTree<T> leftChild,BinaryTree<T> rightChild) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		BinaryTreeNode<T> leftChildNode = leftChild != null ? leftChild.root : null;
		BinaryTreeNode<T> rightChildNode = rightChild != null ? rightChild.root : null;
		this.root = new BinaryTreeNode<T>(value, leftChildNode, rightChildNode);
	}
	/**
	 * Constructs the tree.
	 * @param value - the value of the node.
	 */
	public BinaryTree(T value) {
		this(value, null, null);
	}
	/**
	 * @return the root of the tree.
	 */
	public BinaryTreeNode<T> getRoot() {
		return this.root;
	}
	/**
	 * @return the left child of the root.
	 */
	public BinaryTreeNode<T> getLeftChildNode() {
		if (this.root != null) {
			return this.root.getLeftChild();
	}
	return null;
	}
	/**
	 * @return the right child of the root.
	 */
	public BinaryTreeNode<T> getRightChildNode() {
		if (this.root != null) {
			return this.root.getRightChild();
		}
	return null;
	}
	/**
	 * Traverses binary tree in pre-order manner.
	 * @param root - the binary tree to be traversed.
	 */
	private void printPreOrder(BinaryTreeNode<T> root) {
		if (root == null) {
			return;
		}
//		Left -> Root -> Right
		// 1. Visit the left child.
		printPreOrder(root.getLeftChild());
		// 2. Visit the root of this subtree.
		System.out.print(root.getValue() + " ");
		// 3. Visit the right child.
		printPreOrder(root.getRightChild());
	}
	private void printInOrder(BinaryTreeNode<T> root) {
		if (root == null) {
			return;
		}
//		Root -> Left -> Right
		// 1. Visit the root of this subtree.
		System.out.print(root.getValue() + " ");
		// 2. Visit the left child.
		printInOrder(root.getLeftChild());
		// 3. Visit the right child.
		printInOrder(root.getRightChild());
	}
	private void printPostOrder(BinaryTreeNode<T> root) {
		if (root == null) {
			return;
		}
//		Left -> Right -> Root
		// 1. Visit the left child.
		printPostOrder(root.getLeftChild());
		// 2. Visit the right child.
		printPostOrder(root.getRightChild());
		// 3. Visit the root of this subtree.
		System.out.print(root.getValue() + " ");
	}
	public void traversePreOrder() {
		printPreOrder(this.root);
		System.out.println();
	}
	public void traverseInOrder() {
		printInOrder(this.root);
		System.out.println();
	}
	public void traversePostOrder() {
		printPostOrder(this.root);
		System.out.println();
	}
	public void zad4(BinaryTreeNode<T> root) {
		queue.add(root);

		while (!queue.isEmpty()) {
			BinaryTreeNode<T> value = queue.poll();
			if (value.getLeftChild().isLeaf(value) && value.getRightChild().isLeaf(value)) {
				System.out.println(value.getValue());
			}
			if (value.getLeftChild() != null ) {
				queue.add(value.getLeftChild());		
			}
			if (value.getRightChild() != null) {
				queue.add(value.getRightChild());
			}
		}
	}
	public void kamzad4() {
		zad4(this.root);
	}
	public static void main(String[] args) {
		// Create the binary tree from the sample.
		BinaryTree<Integer> binaryTree1 =
				new BinaryTree<Integer>(14,
						new BinaryTree<Integer>(19,
								new BinaryTree<Integer> (23),
									new BinaryTree<Integer> (6,
										new BinaryTree<Integer>(10),
											new BinaryTree<Integer>(21))),
												new BinaryTree<Integer>(15,
														new BinaryTree<Integer>(3),
															null));
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(1, new BinaryTree<Integer>(2, new BinaryTree<Integer>(4, new BinaryTree<Integer>(8), null), new BinaryTree<Integer>(5, new BinaryTree<Integer>(9), null)), new BinaryTree<Integer>(3, new BinaryTree<Integer>(6, new BinaryTree<Integer>(10), null), new BinaryTree<Integer>(7, new BinaryTree<Integer>(11), null)));
		// Traverse and print the tree in pre-order manner.
		binaryTree.traversePreOrder();
		System.out.println("________________________________________");
//		Traverse and print the tree in in-order manner.
		binaryTree.traverseInOrder();
		System.out.println("________________________________________");
//		Traverse and print the tree in post-order manner.
		binaryTree.traversePostOrder();
		System.out.println("________________________________________");
	}
}