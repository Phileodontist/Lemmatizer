
package Words;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * NAME: Philip Leo Pascual
 * ID: A13193252
 * LOGIN: cs12walf
 */

/**
 * Title: HashTable
 * Description: Implements a hash table
 * 
 * @author Philip Leo Pascual
 * @version 1.0
 * @since Mar 4, 2017
 */
public class HashTable implements IHashTable
{
	private static double loadFactor = 2.0 / 3;
	@SuppressWarnings("rawtypes")
	private LinkedList[] hashTable;
	private int longestList; // Number of elements in the longest list
	private int nelems; // Number of element stored in the hash table
	private int expand; // Number of times that the table has been expanded
	private int collision; // Number of collisions since last expansion
	private String statsFileName; // FilePath for the file to write statistics
									// upon every rehash
	private boolean printStats = false; // Boolean to decide whether to write
										// statistics to file or not after
										// rehashing

	/**
	 * Constructor for hash table
	 * 
	 * @param Initial size of the hash table
	 */
	public HashTable(int size)
	{
		hashTable = new LinkedList[size];
	}

	/**
	 * Constructor for hash table
	 * 
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName)
	{
		hashTable = new LinkedList[size];
		printStats = true;
		statsFileName = fileName;

		collision = 0;
		longestList = 0;
		// Set printStats to true and statsFileName to fileName
	}

	/**
	 * Insert the string value into the hash table
	 * 
	 * @param value value to insert
	 * @throws NullPointerException if value is null
	 * @return true if the value was inserted, false if the value was already
	 *         present
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean insert(String value) throws NullPointerException
	{
		value = value.toLowerCase();

		// Throws exception if value is null
		throwNull(value);

		// If hash value points at empty index, add LinkedList
		addLinkedList(value);

		// If hashTable has the value already
		if (contains(value))
		{
			return false;
		}

		// Checks if hashTable is 2/3 full
		overLoadFactor();

		// If hash value points at empty index, add LinkedList
		addLinkedList(value);

		// Retrieves LinkedList at hash value(index) in table
		LinkedList<String> bucketList = hashTable[hash(value)];

		// Adds string to the index's LinkedList
		listAppend(bucketList, value);
		nelems++;

		return true;
	}

	/**
	 * Delete the given value from the hash table
	 * 
	 * @param value value to delete
	 * @throws NullPointerException if value is null
	 * @return true if the value was deleted, false if the value was not found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(String value)
	{
		value = value.toLowerCase();

		// Throws exception if value is null
		throwNull(value);

		LinkedList<String> bucketList = hashTable[hash(value)];

		// Removes the value from the specified bucket
		removeVal(bucketList, value);

		return false;
	}

	/**
	 * Check if the given value is present in the hash table
	 * 
	 * @param value value to look up
	 * @throws NullPointerException if value is null
	 * @return true if the value was found, false i;f the value was not found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(String value)
	{
		value = value.toLowerCase();

		// Throws exception if value is null
		throwNull(value);

		LinkedList<String> bucketList = hashTable[hash(value)];

		if (bucketList == null)
		{
			return false;
		}

		return listSearch(bucketList, value);
	}

	/**
	 * Print the contents of the hash table. Print nothing if table is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void printTable()
	{
		// Print table if not table isn't empty
		if (!(nelems == 0))
		{
			// Iterates through array
			for (int i = 0; i < hashTable.length; i++)
			{
				if (hashTable[i] == null)
				{
					System.out.println(i + ": ");
					continue;
				}
				// Retrieves indexs LinkedList
				LinkedList<String> bucket = hashTable[i];
				Iterator<String> iter = bucket.iterator();

				System.out.print(i + ": ");

				if (bucket.isEmpty())
				{
					continue;
				}

				// Grabs next element if true
				while (iter.hasNext())
				{
					String value = iter.next();

					System.out.print(value);

					if (!iter.hasNext())
					{
						System.out.println(" ");
					}

					if (iter.hasNext())
					{
						System.out.print(", ");
					}

				}

			}
		}
	}

	/**
	 * Return the number of elements currently stored in the hash table
	 * 
	 * @return nelems
	 */
	@Override
	public int getSize()
	{
		return nelems;
	}

	/** Helper Methods */

	/**
	 * Calculates the hash value of passed in value
	 * 
	 * @param value - passed in string
	 * @return hash value(index) within the hash table
	 */
	private int hash(String value)
	{
		int hashVal = 0;
		value = value.toLowerCase();

		// Calculates the hash value
		for (int i = 0; i < value.length(); i++)
		{
			int letter = value.charAt(i);
			hashVal = (hashVal * 31 + letter) % hashTable.length;
		}

		return hashVal;
	}

	/**
	 * Hash method for new hash table
	 * 
	 * @param newTable - new hash table
	 * @param value - passed in string
	 * @return - hash value of the passed in string
	 */
	private int hashNewTable(LinkedList<String>[] newTable, String value)
	{
		int hashVal = 0;
		value = value.toLowerCase();

		// Calculates the hash value
		for (int i = 0; i < value.length(); i++)
		{
			int letter = value.charAt(i);
			hashVal = (hashVal * 31 + letter) % newTable.length;
		}
		
		return hashVal;

	}

	/**
	 * Searches through a LinkedList if hash table contains the value
	 * 
	 * @param bucket - LinkedList in the hash table
	 * @param value - passed in string
	 * @return true if hash table contains the value, false otherwise
	 */
	private boolean listSearch(LinkedList<String> bucket, String value)
	{
		Iterator<String> iter = bucket.iterator();

		// Grabs next element if true
		while (iter.hasNext())
		{
			String val = iter.next();

			if (val.equals(value))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Adds the value to the specified LinkedList
	 * 
	 * @param bucket - LinkedList in hash table
	 * @param value - passed in string
	 */
	private void listAppend(LinkedList<String> bucket, String value)
	{
		bucket.add(value);

		// Increment collision if true
		if (bucket.size() > 1)
		{
			collision++;
		}

	}

	/**
	 * Searches through LinkedList for a match, removes element if found
	 * 
	 * @param value - passed in string
	 */
	private void removeVal(LinkedList<String> bucket, String value)
	{
		Iterator<String> iter = bucket.iterator();

		// Grabs next value if true
		while (iter.hasNext())
		{
			String val = iter.next();

			// Removes element if found
			if (val.equals(value))
			{
				nelems--;
				bucket.remove(val);
				return;
			}
		}
	}

	/**
	 * Throws NullPointerException if value is null
	 * 
	 * @param value - passed in string
	 */
	private void throwNull(String value)
	{
		// Throw Exception if true
		if (value.equals(null))
		{
			throw new NullPointerException();
		}
	}

	/**
	 * Finds the longest LinkedList
	 */
	@SuppressWarnings("unchecked")
	private void longestList()
	{
		// Iterates through the hash table array
		for (int i = 0; i < hashTable.length; i++)
		{
			LinkedList<String> bucket = hashTable[i];

			if (bucket == null)
			{
				continue;
			}

			if (bucket.isEmpty())
			{
				continue;
			}

			if (bucket.size() > longestList)
			{
				longestList = bucket.size();
			}

		}
	}

	/**
	 * Rehashes all of the elements within the old hash table to a new one
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		int multiplier = 2;
		int doubleSize = hashTable.length * multiplier;
		LinkedList<String>[] newTable = new LinkedList[doubleSize];

		for (int i = 0; i < hashTable.length; i++)
		{
			if (hashTable[i] == null)
			{
				continue;
			}

			LinkedList<String> bucket = hashTable[i];
			Iterator<String> iter = bucket.iterator();

			if (bucket.isEmpty())
			{
				continue;
			}

			// Grabs element within the LinkedList
			while (iter.hasNext())
			{
				String value = iter.next();

				// If index is null, initialize new LinkedList
				if (newTable[hashNewTable(newTable, value)] == null)
				{
					LinkedList<String> bucket2 = new LinkedList<>();
					newTable[hashNewTable(newTable, value)] = bucket2;
				}

				// Adds value to newTable/ index's LinkedList
				listAppend(newTable[hashNewTable(newTable, value)], value);
			}
		}
		
		// newTable becomes the current hashTable
		hashTable = newTable;
		
	}

	/**
	 * Checks if the table is over load factor
	 */
	private void overLoadFactor()
	{

		// If the loadFactor(2/3) of the table size has been reached
		if (calculateLoadFactor() >= loadFactor)
		{
			longestList();
			expand++;

			// Prints to file if true
			if (printStats == true)
			{
				printStatistics();
			}

			// Resize table and rehash elements
			rehash();
		}
	}

	/**
	 * Writes the statistics of the hash table to a file
	 */
	private void printStatistics()
	{
		FileOutputStream fos = null;
		File file;
		PrintStream ps;
		
		NumberFormat formatter = new DecimalFormat("#0.00");

		String stats = (expand + " resizes, " + "load factor " + formatter
				.format(calculateLoadFactor()) + "," + " " + collision
				+ " collisions," + " " + longestList + " longest chain");

		try
		{
			file = new File(statsFileName);
			fos = new FileOutputStream(file, true);
			ps = new PrintStream(fos);

			if (!file.exists())
			{
				file.createNewFile();
			}

			ps.println(stats);
			ps.close();
			fos.close();

		}
		catch (IOException e)
		{

		}

	}

	/**
	 * Adds a LinkedList into index specified by hash value if null
	 * 
	 * @param value - passed in string
	 */
	private void addLinkedList(String value)
	{
		// If index in table is null, add new List
		if (hashTable[hash(value)] == null)
		{
			LinkedList<String> bucket = new LinkedList<>();
			hashTable[hash(value)] = bucket;
		}
	}

	/**
	 * Calculates the hash tables capacity
	 * 
	 * @return the amount of space thats left
	 */
	private double calculateLoadFactor()
	{
		return Math.round((nelems * 100.0)) / (hashTable.length * 100.0);
	}

	/* Getter methods */

	/**
	 * Retrieves the LinkedList at the specified index
	 * 
	 * @param index - position in hashTable
	 * @return - the LinkedList at said position
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<String> getBucket(int index)
	{
		return hashTable[index];
	}

	/**
	 * Retrieves the hash value of a string
	 * 
	 * @param value - passed in string
	 * @return - hash value of string
	 */
	public int gethashValue(String value)
	{
		return hash(value);
	}

	/**
	 * Retrieves the hashTables length
	 * 
	 * @return - length of the hashTable
	 */
	public int gethashLength()
	{
		return hashTable.length;
	}

	// TODO - Helper methods can make your code more efficient and look neater.
	// We expect AT LEAST the following helper methods in your code
	// rehash() to expand and rehash the items into the table when load factor
	// goes over the threshold.
	// printStatistics() to print the statistics after each expansion. This
	// method will be called from insert/rehash only if printStats=true

}
