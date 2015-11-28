/**
 * Implementation of the SkipList Data Structure 
 * @author AkshaySathe
 */

import java.util.Iterator;
import java.util.Random;

/**
 * SkipListImplementation class which implements the skipList interface
 * 
 * @author AkshaySathe
 * @param <T>A Generic Class which gives skip list implementation
 */
public class SkipListImplementation<T extends Comparable<? super T>> implements
		SkipList<T> {
	SkipListNode<T> head;
	int maxLevel, size;
	int listSize = 10;

	/* Constructor */
	public SkipListImplementation() {
		maxLevel = 0;
		size = 0;
		head = new SkipListNode(Long.MIN_VALUE, maxLevel);
	}

	/**
	 * Class for defining the structure of a node
	 * 
	 * @param <T>
	 */
	private class SkipListNode<T> {
		T data;

		/* array of pointers for maintaining links */
		SkipListNode<T> next[];

		/* Consrtructor */
		public SkipListNode(T input, int mNodeLevel) {
			data = input;
			next = new SkipListNode[mNodeLevel + 1];
			for (int i = 0; i <= mNodeLevel; i++)
				next[i] = null;
		}

	}

	/**
	 * Method for adding a new element in the skip list
	 * 
	 * @param x
	 *            :The element to be added to the skip list
	 */
	@Override
	public void add(T x) {
		int countLevel = 0, i;

		/* Do not add the element if it is already present */
		if (contains(x))
			return;

		SkipListNode<T> newNode[];
		SkipListNode<T> searchNode;
		SkipListNode<T> newTempNode;

		/* Setting the level of the node randomly */
		countLevel = randomToss();

		if (countLevel > maxLevel) {

			/* Create a new level of head pointer */
			newNode = new SkipListNode[countLevel + 1];
			for (i = 0; i < countLevel; i++)
				if (i <= maxLevel) {
					newNode[i] = head.next[i];
				} else {
					newNode[i] = null;
				}

			head.next = newNode;

			/* Setting the new max level */
			maxLevel = countLevel;
		}
		searchNode = head;
		newTempNode = new SkipListNode<>(x, countLevel);

		/*
		 * Iterate from the head of the skip list and continue util next node
		 * element is null or is grater than the current element
		 */
		for (i = maxLevel; i >= 0; i--) {
			while (searchNode.next[i] != null
					&& searchNode.next[i].data.compareTo(x) < 0) {
				searchNode = searchNode.next[i];
			}
			if (i <= countLevel) {
				newTempNode.next[i] = searchNode.next[i];
				searchNode.next[i] = newTempNode;
			}

		}
		size++;
	}

	/**
	 * Function that randomly calculates the level of SkipList
	 * 
	 * @return Returns the level of a node
	 */
	private int randomToss() {
		int countLevel = 0;
		boolean randomToss;
		Random rand = new Random();

		/* randomly assign boolean values to variable */
		randomToss = rand.nextBoolean();

		while (randomToss == true) {
			countLevel++;
			randomToss = rand.nextBoolean();
		}
		return countLevel;
	}

	/**
	 * Method for Printing the generated Skip list
	 */
	public void displaySkipList() {
		SkipListNode<T> ptrNode;
		for (int i = 0; i < maxLevel; i++) {
			ptrNode = head;
			while (ptrNode != null) {
				System.out.print(ptrNode.data + " ");
				ptrNode = ptrNode.next[i];
			}
			System.out.println(" ");
		}
	}

	/**
	 * Function to traverse the skiplist from maximum level to the lowest level
	 *
	 * @param traversalNode
	 *            :A node to traverse the linked list
	 * @param ele
	 *            :Element to be inserted in skiplist after traversal
	 * @return Returns the node after traversing the linked list
	 */
	private SkipListNode<T> skipListTraversal(SkipListNode<T> traversalNode,
			T ele) {
		traversalNode = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (traversalNode.next[i] != null
					&& traversalNode.next[i].data.compareTo(ele) < 0)
				traversalNode = traversalNode.next[i];
		}
		return traversalNode;
	}

	/**
	 * Method that finds the least element that is >=x or null if there is no
	 * such element
	 * 
	 * @param x
	 */
	@Override
	public T ceiling(T x) {

		if (isEmpty())
			return null;

		SkipListNode<T> traversalNode = null;
		SkipListNode<T> resultNode;
		resultNode = skipListTraversal(traversalNode, x);

		if (resultNode.next[0] == null)
			return null;
		return resultNode.next[0].data;
	}

	/**
	 * Function to check if element is present in the list or not
	 * 
	 * @param x
	 * @return Returns either true or false
	 */
	@Override
	public boolean contains(T x) {

		if (isEmpty())
			return false;

		// return findElement(x) != null;
		SkipListNode<T> traversalNode = null;
		SkipListNode<T> resultNode;
		resultNode = skipListTraversal(traversalNode, x);

		if (resultNode.next[0] != null
				&& resultNode.next[0].data.compareTo(x) == 0)
			return true;
		return false;
	}

	/**
	 * 
	 * @param mElement
	 * @return
	 */
	private SkipListNode<T> findElement(T mElement) {
		if (isEmpty())
			return null;
		SkipListNode<T> traversalNode = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (traversalNode.next[i] != null
					&& traversalNode.next[i].data.compareTo(mElement) < 1)
				if (traversalNode.next[i].data.compareTo(mElement) == 0) {
					return traversalNode.next[i];
				}
			traversalNode = traversalNode.next[i];
		}
		return null;
	}

	@Override
	public T findIndex(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method which returns the first element in the list
	 */
	@Override
	public T first() {

		if (isEmpty())
			return null;
		else
			return head.next[0].data;
	}

	@Override
	/**
	 * Function to find the greatest element <= given element
	 * @param x
	 * @return Returns the value of element <=x
	 */
	public T floor(T x) {
		if (isEmpty())
			return null;

		SkipListNode<T> traversalNode = null;
		SkipListNode<T> resultNode;
		resultNode = skipListTraversal(traversalNode, x);

		if (resultNode.next[0] != null
				&& resultNode.next[0].data.compareTo(x) == 0)
			return resultNode.next[0].data;
		else if (resultNode == head)
			return null;
		else
			return resultNode.data;
	}

	@Override
	/**
	 * Function to check if skipList is empty
	 * @return : returns true if list is empty or false otherwise 
	 */
	public boolean isEmpty() {
		return head.next[0] == null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Function which return the last element in the list
	 * 
	 * @return Returns the last value in the skipList
	 */
	@Override
	public T last() {

		if (isEmpty())
			return null;

		SkipListNode<T> searchNode = head;
		for (int i = maxLevel; i >= 0; i--) {
			while (searchNode.next[i] != null)
				searchNode = searchNode.next[i];
		}
		if (searchNode == head)
			return null;
		return searchNode.data;

	}

	@Override
	public void rebuild() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Funcion to remove an element from the skip list
	 *@param x:The element to be removed from the list
	 *@return Returns either true or false
	 */
	public boolean remove(T x) {

		if (isEmpty())
			return false;

		SkipListNode<T> traversalNode = head;
		boolean mResult = false;

		for (int i = maxLevel; i >= 0; i--) {
			while (traversalNode.next[i] != null
					&& traversalNode.next[i].data.compareTo(x) < 0) {
				traversalNode = traversalNode.next[i];
			}
			if (traversalNode.next[i] != null
					&& traversalNode.next[i].data.compareTo(x) == 0) {
				traversalNode.next[i] = traversalNode.next[i].next[i];
				mResult = true;
			}
		}
		return mResult;
	}

	@Override
	/**
	 * Function which returns the size of the skipList
	 * @return Returns the size of the list
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
