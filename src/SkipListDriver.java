/**
 * @author AkshaySathe
 * The Driver program for the SkipList Implementation class
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SkipListDriver {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Scanner sc = null;

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
		String operation = "";
		long operand = 0;
		int modValue = 9907;
		long result = 0;
		Long returnValue = null;
		SkipListImplementation<Long> mSkipList = new SkipListImplementation();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Add": {
				operand = sc.nextLong();
				mSkipList.add(operand);
				result = (result + 1) % modValue;
				break;
			}
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = mSkipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
//			case "FindIndex": {
//				int intOperand = sc.nextInt();
//				returnValue = mSkipList.findIndex(intOperand);
//				if (returnValue != null) {
//					result = (result + returnValue) % modValue;
//				}
//				break;
//			}
			case "First": {
				returnValue = mSkipList.first();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = mSkipList.last();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = mSkipList.floor(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Remove": {
				operand = sc.nextLong();
				if (mSkipList.remove(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			case "Contains": {
				operand = sc.nextLong();
				if (mSkipList.contains(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(result + " " + elapsedTime);

	}

}
