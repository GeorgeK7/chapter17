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
	public void PrintLevelSums() {
		Queue<BinaryTreeNode<T>> currentLevelNodes = new LinkedList<BinaryTreeNode<T>>();
		Queue<BinaryTreeNode<T>> nextLevelNodes = new LinkedList<BinaryTreeNode<T>>();
		ArrayList<Integer> sums = new ArrayList<Integer>();

		currentLevelNodes.add(this.root);
		while (currentLevelNodes.size() > 0) {
			int nextSum = 0;
			while (currentLevelNodes.size() > 0) {
				BinaryTreeNode currentNode = currentLevelNodes.poll();
				nextSum += (Integer) currentNode.getValue();
				if (currentNode.getLeftChild() != null) {
					nextLevelNodes.add(currentNode.getLeftChild());
				}
				if (currentNode.getRightChild() != null) {
					nextLevelNodes.add(currentNode.getRightChild());
				}
			}
			sums.add(nextSum);
			while (nextLevelNodes.size() > 0) {
				BinaryTreeNode currentChild = nextLevelNodes.poll();
				currentLevelNodes.add(currentChild);
			}
		}
		PrintThem(sums);
	}
	public void PrintThem(ArrayList<Integer> sums) {
		long sumCounter = 0;
		for (int sum : sums) {
			System.out.printf("\nLevel {%d} -> {%d}", sumCounter, sum);
			sumCounter++;
		}
	}
	public void toEx4() {
		zad4(this.root);
	}
}
