///////////////////////////////////////////////////////////////////////////////
//
// Title:    COVID Tracker Tester
// Course:   CS 300 Fall 2020
//
// Author:   Connor Phillips
// Email:    cjphillips4@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////////////////////////////////////////////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
public class COVIDTrackerTester {
	/**
	 * Checks for possible errors when executing the addTest method
	 * @return True if no errors occur, false otherwise
	 */
	public static boolean testAddTest() {
		String[] pos = new String[2];
		String[] neg = new String[2];
		// two empty arrays
		if (!COVIDTracker.addTest(pos, neg, "AB1234", false) || neg[0] == null)
			return false;
		if (!COVIDTracker.addTest(pos, neg, "CD2345", true) || neg[0] == null)
			return false;
		// two arrays with space
		if (!COVIDTracker.addTest(pos, neg, "CD2345", false) || neg[1] == null)
			return false;
		// one full array but adding to one with space
		if (!COVIDTracker.addTest(pos, neg, "EF3456", true) || neg[1] == null)
			return false;
		// one array with spece but adding to the one that is full
		String[] pos2 = new String[2];
		if (COVIDTracker.addTest(pos2, neg, "EF3456", false))
			return false;
		// two full arrays
		if (COVIDTracker.addTest(pos, neg, "EF3456", true))
			return false;
		return true;
	}

	/**
	 * Checks for possible errors when executing the removeIndividual method
	 * @return True if no errors occur, false otherwise
	 */
	public static boolean testRemoveIndividual() {
		String[] pos = new String[3];
		String[] neg = new String[3];
		// Testing on empty arrays
		if (COVIDTracker.removeIndividual(pos, neg, "111"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		// Removing the first and only element of an array
		if (!COVIDTracker.removeIndividual(pos, neg, "111"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "111", false);
		// Removing the same ID from 2 different arrays
		if (!COVIDTracker.removeIndividual(pos, neg, "111"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "112", true);
		// Removing the last element in a full array
		if (!COVIDTracker.removeIndividual(pos, neg, "112"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "111", false);
		COVIDTracker.addTest(pos, neg, "111", false);
		COVIDTracker.addTest(pos, neg, "111", false);
		// Removing everything from 2 full arrays
		if (!COVIDTracker.removeIndividual(pos, neg, "111"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		COVIDTracker.addTest(pos, neg, "112", true);
		COVIDTracker.addTest(pos, neg, "113", false);
		COVIDTracker.addTest(pos, neg, "114", false);
		// Removing an element from arrays with items and space
		if (!COVIDTracker.removeIndividual(pos, neg, "113"))
			return false;
		return true;
	}

	/**
	 * Checking for errors in output of getPopStats
	 * @return True if no errors occur, false otherwise
	 */
	public static boolean testGetPopStats() {
		String[] pos = new String[3];
		String[] neg = new String[3];
		// Checking if output is correct with empty arrays
		if (!COVIDTracker.getPopStats(pos, neg).equals("Total tests: " + 0 
				+ "\nTotal individuals tested: " + "0"+ "\nPercent positive tests: " + 0.0 
				+ "%\nPercent positive individuals: " + 0.0 + "%"))
			return false;
		COVIDTracker.addTest(pos, neg, "111", true);
		// Checking to see if output of one element is correct
		if (!COVIDTracker.getPopStats(pos, neg).equals("Total tests: " + 1 
				+ "\nTotal individuals tested: " + "1"
				+ "\nPercent positive tests: " + 100.0 + "%\nPercent positive individuals: " 
				+ 100.0 + "%"))
			return false;
		// Checking to see if additional tests are added if the output is still correct
		COVIDTracker.addTest(pos, neg, "112", false);
		if (!COVIDTracker.getPopStats(pos, neg).equals("Total tests: " + 2 
				+ "\nTotal individuals tested: " + "2"
				+ "\nPercent positive tests: " + 50.0 + "%\nPercent positive individuals: " 
				+ 50.0 + "%"))
			return false;
		COVIDTracker.addTest(pos, neg, "112", false);
		COVIDTracker.addTest(pos, neg, "112", false);
		COVIDTracker.addTest(pos, neg, "113", true);
		COVIDTracker.addTest(pos, neg, "114", true);
		// Checking output with 2 full arrays
		if (!COVIDTracker.getPopStats(pos, neg).equals("Total tests: " + 6 
				+ "\nTotal individuals tested: " + "4"
				+ "\nPercent positive tests: " + 50.0 + "%\nPercent positive individuals: " 
				+ 75.0 + "%"))
			return false;
		return true;
	}

	/**
	 * Checking for errors in output of getIndivdualStats
	 * @return True if no errors occur, false otherwise
	 */
	public static boolean testGetIndividualStats() {
		String[] pos = new String[3];
		String[] neg = new String[3];
		// Checking if output is correct with empty arrays
		if (!COVIDTracker.getIndividualStats(pos, neg, "111")
				.equals("Total tests: " + 0 + "\nPositive: " + 0 + "\nNegative: " + 0))
			return false;
		COVIDTracker.addTest(pos, neg, "114", true);
		// Checking if output is correct with one element
		if (!COVIDTracker.getIndividualStats(pos, neg, "114")
				.equals("Total tests: " + 1 + "\nPositive: " + 1 + "\nNegative: " + 0))
			return false;
		COVIDTracker.addTest(pos, neg, "114", true);
		COVIDTracker.addTest(pos, neg, "114", false);
		// Checking if output is correct with one element that is repeated
		if (!COVIDTracker.getIndividualStats(pos, neg, "114")
				.equals("Total tests: " + 3 + "\nPositive: " + 2 + "\nNegative: " + 1))
			return false;
		// Checking if output is correct with 2 full arrays
		COVIDTracker.addTest(pos, neg, "114", true);
		COVIDTracker.addTest(pos, neg, "116", false);
		COVIDTracker.addTest(pos, neg, "117", false);
		if (!COVIDTracker.getIndividualStats(pos, neg, "114")
				.equals("Total tests: " + 4 + "\nPositive: " + 3 + "\nNegative: " + 1))
			return false;
		return true;
	}

	/**
	 * Main method that tests tester methods
	 */
	public static void main(String[] args) {
		System.out.println(testAddTest());
		System.out.println(testRemoveIndividual());
		System.out.println(testGetPopStats());
		System.out.println(testGetIndividualStats());
	}
}