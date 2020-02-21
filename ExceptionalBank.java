//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ExceptionalBankTester
// Files: ExceptionalBank.java, ExceptionalBankTester.java, Coin.java
// Course: CS 300
//
// Author: Aneesh Patil
// Email: apatil6@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: 
// Partner Email: 
// Partner Lecturer's Name: 
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understood the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: TA - Adarsh Mittal
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements unit test methods for ExceptionalBank application
 * 
 * @author Aneesh Patil
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.PrintWriter;

/**
 * This class implements an expanded version of elastic bank application
 * 
 */
public class ExceptionalBank {
	private Coin[] coins; // array which stores all coins held in this elastic bank
	private int size; // size of this elastic bank
	private int expansionsLeft; // number of expansions left for this elastic bank
	private static Random rand = new Random(100); // random integers generator

	/**
	 * Creates a new elastic bank object with a given initial capacity
	 * 
	 * @param initialCapacity initial capacity of this elastic bank
	 * @throws java.lang.IllegalArgumentException if initial capacity 0 or less
	 */
	public ExceptionalBank(int initialCapacity) {

		if (initialCapacity <= 0) {
			throw new IllegalArgumentException(
					"WARNING! The initial capacity of a bank must be a non-zero positive integer.");
		}
		coins = new Coin[initialCapacity];
		this.expansionsLeft = 2;
	}

	/**
	 * Creates a new elastic bank object with an initial capacity equal to 10
	 */
	public ExceptionalBank() {
		this(10);
	}

	/**
	 * Returns the capacity of this elastic bank
	 * 
	 * @return the capacity of this elastic bank
	 */
	public int capacity() {
		return coins.length;
	}

	/**
	 * Returns the expansions left for this elastic bank
	 * 
	 * @return the expansions left for this elastic bank
	 */
	public int getExpansions() {
		return this.expansionsLeft;
	}

	/**
	 * Returns the number of coins held in this elastic bank
	 * 
	 * @return the size of this elastic bank
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Returns the value in cents of coins held in this elastic bank
	 * 
	 * @return the balance of this elastic bank
	 */
	public int getBalance() {
		int balance = 0;
		// add the value of each coin held in this bank to balance, then return it
		for (int i = 0; i < size; i++) {
			balance += coins[i].value();
		}
		return balance;
	}

	/**
	 * Returns the number of coins with a specific coinName held in this bank. The
	 * coin name comparison is case insensitive
	 * 
	 * @param coinName name of a coin
	 * @return the count of coins having the provided coinName, held in this bank
	 */
	public int getSpecificCoinCount(String coinName) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (coins[i].name().equalsIgnoreCase(coinName))
				count++;
		}
		return count;
	}

	/**
	 * Returns a string representation of all the coins held in this elastic bank.
	 * Each coin is represented by the pair "(name, value)", and the string
	 * representation should contain all of these pairs in one space-separated line.
	 * For example: "(PENNY, 1) (QUARTER, 25) (PENNY, 1) (DIME, 10) (NICKEL, 5)"
	 * 
	 * @return a String representation of the contents of the bank.
	 */
	public String getCoins() {
		String contents = "";
		// traverse the coins oversize array and add each coin's string representation
		// to the string to
		// be returned
		for (int i = 0; i < size; i++) {
			contents += "(" + coins[i].name() + ", " + coins[i].value() + ")";
			if (i < size - 1)
				contents += " ";
		}
		return contents;
	}

	/**
	 * Returns a summary of this bank contents
	 * 
	 * @return an empty string if this bank is empty, and a string representation of
	 *         the summary of this bank otherwise. The summary of the bank is a set
	 *         of lines. Each line is formatted as follows "coin_name:coin_count"
	 */
	public String getSummary() {
		String summary = "";
		Coin[] values = Coin.values();
		// traverse this bank contents and update its summary
		for (int i = 0; i < values.length; i++) {
			String name = values[i].name();
			int count = getSpecificCoinCount(name);
			if (count != 0) {
				summary += name + ":" + count + "\n";
			}
		}
		return summary.trim(); // remove whitespace (spaces, new lines etc.) from the beginning and end
								// of summary and return the resulting string

	}

	/**
	 * Removes and returns a coin at a random position from this elastic bank
	 * 
	 * @return the removed coin or null if this bank is empty
	 * @throws java.util.NoSuchElementException - if this bank is empty
	 */
	public Coin removeCoin() {

		if (size == 0)
			throw new NoSuchElementException("WARNING! This bank is empty. Unable to remove a coin.");

		int randPosition = rand.nextInt(size); // get a random position from 0 .. size-1
		Coin removedCoin = coins[randPosition]; // store the coin to be removed
		// The order of the coins within this bank (a piggy bank) is not important
		// So, move the coin at the end of the coins array to the random position
		// and set that last element to null.
		coins[randPosition] = coins[size - 1];
		coins[size - 1] = null;
		size--; // update size
		return removedCoin;
	}

	/**
	 * Removes all the coins from this elastic bank
	 */
	public void empty() {
		// set all the non-null references within the coins array to null
		for (int i = 0; i < size; i++) {
			coins[i] = null;
		}
		// set the size of this bank to 0
		size = 0;
	}

	/**
	 * adds a Coin to the bank and adjusts the capacity of coins if necessary and
	 * possible
	 * 
	 * @param c coin to be added to this elastic bank
	 * @throws java.lang.IllegalArgumentException if coin (c) is null
	 * @throws java.util.zip.DataFormatException
	 * @throws java.util.NoSuchElementException
	 */
	public void addCoin(Coin c) {
		// check if the coin being entered is null, throw IllegalArgumentException if so
		if (c == (null))
			throw new IllegalArgumentException("WARNING! You cannot add a null reference to this bank.");

		// check if this bank is full
		if (size == coins.length) {
			// check whether there are expansions left
			if (this.expansionsLeft > 0) {
				// expand the capacity of this elastic bank by 10
				coins = Arrays.copyOf(coins, coins.length + 10);
				this.expansionsLeft--;
			} else { // no expansions left
				// empty this elastic bank
				empty();
			}
		}
		// add c at the end of this bank
		coins[size] = c;
		size++;
	}

	/**
	 * Adds a number of the same coin type with respect to a provided command line.
	 * The format of the command line is "coin_name:coins_count". Such command line
	 * refers to adding coins_count of coin_name to this bank. For instance,
	 * "PENNY:5", or " Penny : 5 " refer to adding 5 pennies to this bank. If the
	 * format of the provided command line is incorrect, no coins
	 * 
	 * @param command - command line to add a number of coins of the same type to
	 *                this bank
	 * 
	 * @throws java.lang.IllegalArgumentException - if command is null
	 * @throws java.util.zip.DataFormatException  - if command is incorrectly
	 *                                            formatted
	 * @throws java.util.NoSuchElementException   - if coin_name is invalidly
	 *                                            entered
	 */
	public void addCoins(java.lang.String command) throws java.util.zip.DataFormatException {
		// initialize two empty strings, which are used to store the elements in the
		// string array
		String str = "";
		String str2 = "";
		int num1 = 0; // num1 initialized to 0, which is used to test DataFormatExceptions in the
						// command and perform comparisons
		// check if command being entered is null, throw IllegalArgumentException if so
		if (command == (null))
			throw new IllegalArgumentException(
					"WARNING! The addCoins() method does not accept a null reference as input.");
		// part before the colon is assigned to index 0 and part after the array is
		// assigned to index 1 of the array
		String[] arr = command.split(":");
		// since there are only two components in the command, the array must also
		// contain only two elements. If it doesn't command is incorrectly formatted,
		// therefore throw a DataFormatException
		if (arr.length != 2) {
			throw new DataFormatException("The format of the command line " + command + " is incorrect.");
		}
		try {
			// store index 0 and index 1 strings in str and str2 respectively.
			str = arr[0].trim();
			str2 = arr[1].trim(); // eliminate unnecessary whitespace
			// if the part before the colon is empty, throw a DataFormatException (coin_name
			// doesn't exist)
			if (str.isEmpty() || str2.isEmpty()) {
				throw new DataFormatException("The format of the command line " + command + " is incorrect.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// an ArrayIndexOutOfBoundsException implies the command is incorrectly
			// formatted, hence throw a DataFormatException
			throw new DataFormatException("The format of the command line " + command + " is incorrect.");
		}

		try {
			num1 = Integer.parseInt(str2); // convert String to integer

		} catch (NumberFormatException e) {
			// handle NumberFormatException thrown by parseInt() by prompting a
			// DataFormatException for incorrect command format.
			throw new DataFormatException("The format of the command line " + command + " is incorrect.");
		}

		// number of coins being entered to the exceptional bank cannot be 0 or
		// negative. Throw DataFormatException if so.
		if (num1 <= 0) {
			throw new DataFormatException("The format of the command line " + command + " is incorrect");
		}

		Coin[] testCoin = Coin.values(); // create an array consisting of all coin values in the enumeration
		int flag = 1; // usage of flag to determine when to break the loop
		// traverses through the array checking if the command corresponds to any
		// element in testCoin
		for (int i = 0; i < testCoin.length; i++) {
			// if condition ignores case while comparing to allow different variations of
			// valid entries
			if (str.equalsIgnoreCase(testCoin[i].toString())) {
				flag = 0; // initialize flag to 0 if command is found to be equal to an element in the
							// enum
				// run for loop for "num" number of times and call addCoin() for the specific
				// coin
				// in the subject
				for (int j = 0; j < num1; j++) {
					addCoin(testCoin[i]);
				}
			}

		}
		// flag remains 1 if it doesn't enter the above if condition implying that
		// coin_name in the command doesn't exist
		if (flag == 1) {
			// throw NoSuchELementException along with error message
			throw new NoSuchElementException("The coin name provided in the command line " + command + " is invalid.");
		}

	}

	/**
	 * Load a list of coins from a file object which refers to a data file written
	 * in a specific format (a set of lines each formatted as follows
	 * "coin_name:coin_count"). Lines formatted correctly will be added as new coins
	 * to this elastic bank. Lines formatted incorrectly must be skipped (go to the
	 * next line). This method prints/displays the following message for every
	 * skipped line "WARNING! Skipping line. " + "raison_of_the error".
	 * 
	 * 
	 * @param file - file object which refers to a data file of coin_names and their
	 *             counts.
	 * @throws java.lang.NullPointerException - if file is null.
	 * @throws java.io.FileNotFoundException  - if file is not found.
	 * 
	 * 
	 */
	public void loadCoins​(java.io.File file) throws java.io.FileNotFoundException {
		// throw NullPointerException if file argument passed is null
		if (file == null) {
			throw new NullPointerException("File you're trying to read from is null");
		}
		// throw FileNotFoundException if file argument passed doesn't exist
		if (!file.exists())
			throw new FileNotFoundException("File not found");

		Scanner scanner = new Scanner(file); // create scanner object to read from file

		// perform operations while there is another line in the scanner input
		while (scanner.hasNextLine()) {
			{
				try {
					// read the line and pass the command to addCoins(). Throw DataFormatException,
					// IllegalFormatException or NoSuchElementException based on the scanner input
					addCoins(scanner.nextLine());
				} catch (DataFormatException e) {
					// throw DataFormatException if there is an incorrectly formatted command. Print
					// warning message along with details of exception
					System.out.println("WARNING! Skipping line. " + e.getMessage());
				} catch (IllegalArgumentException e1) {
					// throw IllegalArgumentException if the scanner input tries to pass a null
					// reference to addCoins(). Print warning message along with details of
					// exception
					System.out.println("WARNING! Skipping line. " + e1.getMessage());
				} catch (NoSuchElementException e2) {
					// throw NoSuchElementException if the scanner input tries to pass an argument
					// with an incorrect coin_name addCoins(). Print warning message along with
					// details of exception
					System.out.println("WARNING! Skipping line. " + e2.getMessage());
				}
			}

		}
		scanner.close();

	}

	/**
	 * Save the summary of this bank to the provided file in a specific format for
	 * each line: coin_name:coin_count. For instance, if a bank contains 2 quarters,
	 * 1 dime, 5 nickels, and 10 pennies, its contents will be saved as follows:
	 * PENNY:10 NICKEL:5 DIME:1 QUARTER:2 Note that the order of lines does not
	 * matter.
	 * 
	 * @param file - File object where a summary of the contents of this bank will
	 *             be saved
	 * 
	 * 
	 * @throws java.lang.NullPointerException - with or without error message if
	 *                                        file is null.
	 * @throws java.io.FileNotFoundException  - if file is not found
	 * 
	 */
	public void saveBankSummary​(java.io.File file) {
		// throw NullPointerException if file being passed has a null reference
		if (file == null)
			throw new NullPointerException();
		PrintWriter printwriter = null;

		try {
			//
			printwriter = new PrintWriter(file);
			// prints the contents of the exceptional bank, which is evaluated in
			// getSummary() method
			printwriter.print(getSummary());
			printwriter.flush(); // flushes the stream

		} catch (IOException e) {
			e.getMessage(); // return detailed message of error
		}

		printwriter.close(); // closes the stream

	}

}
