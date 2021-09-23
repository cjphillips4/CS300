//////////////////////////////////////////////////////////////////////////////
//
// Title:    COVID Tracker
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
//////////////////////////////////////////////////////////////////////////////
public class COVIDTracker 
{
	/**
	 * Adds ID to the appropriate test array if there is room.
	 * @param pos   The current array of positive tests
	 * @param neg   The current array of negative tests
	 * @param id    The tested individual’s unique identifier String
	 * @param isPos true if the test was positive, false otherwise
	 * @return true if the new record was added, false otherwise
	 */
	public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) 
	{
		//Checks to see if test is positive and if it is, adds it to the pos list
		if (isPos) {
			for (int i = 0; i < pos.length; i++) 
			{
				if (pos[i] == null) 
				{
					pos[i] = id;
					return true;
				}
			}
			return false;
		}
		//Looks for acceptable location for test to be added in neg list
		for (int i = 0; i < neg.length; i++) 
		{
			if (neg[i] == null) 
			{
				neg[i] = id;
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes all elements in both arrays that match a certain String, compacts to
	 * the arrays so that there are no nulls between elements.
	 * 
	 * @param pos The current array of positive tests
	 * @param neg The current array of negative tests
	 * @param id  The id to be searched for and deleted
	 * @return true if the id was found and deleted, false otherwise
	 */
	public static boolean removeIndividual(String[] pos, String[] neg, String id) 
	{
		boolean success = false;
		int size = getSize(pos);
		int i = 0;
		//Iterates through pos to find if a String matches given id
		while (i < pos.length && pos[i] != null) 
		{
			if (pos[i].equals(id)) 
			{
				// Call helper method to shift array elements back one space at index
				condenseArray(pos, i);
				// setting the former last id in the array to null
				pos[--size] = null;
				success = true;
				continue;
			}
			i++;
		}
		i = 0;
		size = getSize(neg);
		//Iterates through neg to see if a String matches the given id
		while (i < neg.length && neg[i] != null) 
		{
			if (neg[i].equals(id)) 
			{
				condenseArray(neg, i);
				neg[--size] = null;
				success = true;
				continue;
			}
			i++;
		}
		return success;
	}

	/**
	 * Determines the size of the given array
	 * 
	 * @param array The array that will have its size measured
	 * @return int value of the array's size
	 */
	private static int getSize(String[] array) 
	{
		int i = 0;
		while (i < array.length && array[i] != null) 
		{
			i++;
		}
		return i;
	}

	/**
	 * Shifts the elements of the array back one space starting at a given index
	 * 
	 * @param array The array that will be condensed
	 * @param index The location in the array where items were begin shifting places
	 *              with
	 */
	private static void condenseArray(String[] array, int index) 
	{
		if (index == array.length - 1)
			array[index] = null;
		else 
		{
			while (index + 1 < array.length) 
			{
				array[index] = array[++index];
			}
		}
	}

	/**
	 * Gathers certain statistics on the population of people stored in 2 arrays
	 * 
	 * @param pos The current array of positive tests
	 * @param neg The current array of negative tests
	 * @return A String that shows the number of tests, total individuals tested,
	 *     percent positive tests, and percent positive individuals
	 */
	public static String getPopStats(String[] pos, String[] neg) 
	{
		String[] newPos = pos.clone();
		String[] newNeg = neg.clone();
		double totalPos = 0 + getSize(newPos);
		double totalNeg = 0 + getSize(newNeg);
		int totalTests = (int) (0 + totalPos + totalNeg);
		int uniqueId = 0;
		double posCounter = 0;
		//Finds the number of unique ids found while also finding the number of positive individuals
		while (newPos[0] != null) 
		{
			removeIndividual(newPos, newNeg, newPos[0]);
			uniqueId++;
			posCounter++;
		}
		//Looks for more unique ids in the neg array
		while (newNeg[0] != null) 
		{
			removeIndividual(newPos, newNeg, newNeg[0]);
			uniqueId++;
		}
		double posTestRate = 0;
		double uniquePosRate = 0;
		//Checks to see if the lists were empty to help with String output
		if (totalTests > 0)
			posTestRate = (totalPos / totalTests) * 100;
		if (uniqueId > 0)
			uniquePosRate = (posCounter / uniqueId) * 100;
		return "Total tests: " + totalTests + "\nTotal individuals tested: " + uniqueId 
	        + "\nPercent positive tests: "+ posTestRate + "%\nPercent positive individuals: "
		    + "" + uniquePosRate + "%";
	}

	/**
	 * Gathers data on testing using a given id
	 * 
	 * @param pos The current array of positive tests
	 * @param neg The current array of negative tests
	 * @param id  The String that serves as a unique identifier for each individual
	 * @return A String that shows the number of tests an individual has taken and
	 *         how many were positive or negative
	 */
	public static String getIndividualStats(String[] pos, String[] neg, String id) 
	{
		int posTests = 0;
		int negTests = 0;
		int i = 0;
		//Counts number of positive tests for the individual
		while (i < pos.length && pos[i] != null) 
		{
			if (pos[i].equals(id))
				posTests++;
			i++;
		}
		i = 0;
		//Counts number of negative tests for the individual
		while (i < neg.length && neg[i] != null) 
		{
			if (neg[i].equals(id))
				negTests++;
			i++;
		}
		return "Total tests: " + (posTests + negTests) + "\nPositive: " + posTests + "\nNegative: " 
		    + negTests;
	}
}