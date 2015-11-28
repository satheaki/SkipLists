/**
 * A driver program for tree as well as skiplist
 * @author AkshaySathe
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * A class defining the driver program for tree and skiplist
 * 
 * @author AkshaySathe
 *
 */
public class TreeListDriver {

	public static void main(String[] args) {
		Scanner sc = null;
		String operation = "";
		long operand = 0;
		int modValue = 9907;
		long resultTree = 0, resultList = 0;
		long startTimeList, startTimeTree, endTimeTree, endTimeList, elapsedTimeTree, elapsedTimeList;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}

		// Initialize the tree set.
		TreeSet<Long> tree = new TreeSet<Long>();

		startTimeTree = System.currentTimeMillis();

		// Read the file entries . Operation <operand>
		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Insert":
			case "Add":
				operand = sc.nextLong();
				tree.add(operand);
				resultTree = (resultTree + 1) % modValue;
				break;
			case "Find":
			case "Contains":
				operand = sc.nextLong();
				if (tree.contains(operand)) {
					resultTree = (resultTree + 1) % modValue;
				}
				break;
			case "Delete":
			case "Remove":
				operand = sc.nextLong();
				if (tree.remove(operand)) {
					resultTree = (resultTree + 1) % modValue;
				}
				break;
			}
		}

		endTimeTree = System.currentTimeMillis();
		elapsedTimeTree = endTimeTree - startTimeTree;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		
		/*Initialize the skiplistImplementation class*/
		SkipListImplementation<Long> mSkipList = new SkipListImplementation();
		startTimeList = System.currentTimeMillis();

		// Read the file entries . Operation <operand>
		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Insert":
			case "Add":
				operand = sc.nextLong();
				mSkipList.add(operand);
				resultList = (resultList + 1) % modValue;
				break;
			case "Find":
			case "Contains":
				operand = sc.nextLong();
				if (mSkipList.contains(operand)) {
					resultList = (resultList + 1) % modValue;
				}
				break;
			case "Delete":
			case "Remove":
				operand = sc.nextLong();
				if (mSkipList.remove(operand)) {
					resultList = (resultList + 1) % modValue;
				}
				break;
			}
		}

		endTimeList = System.currentTimeMillis();
		elapsedTimeList = endTimeList - startTimeList;

		System.out.println("Result after Tree operations:		" + resultTree + "\tTime: "
				+ elapsedTimeTree);
		System.out.println("Result after SkipList operation:	" + resultList
				+ "\tTime: " + elapsedTimeList);
	}

}
