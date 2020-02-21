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
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class ExceptionalBankTester {
	/**
	 * This method checks whether the ExceptionalBank constructor throws an
	 * IllegalArgumentException with appropriate error message, when it is passed a
	 * zero or a negative capacity. This test must fail if another kind of exception
	 * is thrown for such test scenario.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testExceptionalBankConstructor() {
		try {
			// create an exceptional bank with a negative capacity
			ExceptionalBank bank = new ExceptionalBank(-10);
			System.out.println("Problem detected. The constructor call of the ExceptionalBank class did not "
					+ "throw an IllegalArgumentException when it is passed a negative capacity.");
			return false; // return false if no exception has been thrown
		} catch (IllegalArgumentException e1) {
			// check that the caught IllegalArgumentException includes
			// an appropriate error message
			if (e1.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
				System.out.println("Problem detected. The IllegalArgumentException thrown by the constructor "
						+ "call of the ExceptionalBank class when it is passed a negative capacity "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than IllegalArgumentException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "constructor of the ExceptionBank class with a negative argument. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true; // test passed
	}

	/**
	 * This method checks whether the ExceptionalBank.addCoin() method throws an
	 * IllegalArgumentException with an appropriate error message, when it is passed
	 * a null reference.
	 *
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoin() {
		try {
			// create an exceptional bank with default capacity 10
			ExceptionalBank bank1 = new ExceptionalBank();
			bank1.addCoin(null);
			System.out.println("Problem detected. The method call to the addCoin() did not "
					+ "throw an IllegalArgumentException when null is passed to addCoin(c).");
			return false; // return false if no exception thrown
		} catch (IllegalArgumentException e) {
			if (e.getMessage() == null // test method should not throw
					// a NullPointerException,but must return false if e.getMessage is null
					|| !e.getMessage().contains("WARNING! You cannot add a null reference to this bank.")) {
				System.out.println("Problem detected. The IllegalArgumentException thrown by the addCoin(c) "
						+ "call of the ExceptionalBank class when it is passed a null reference "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e1) {
			// an exception other than IllegalArgumentException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoin() method of the ExceptionBank class with a null reference. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e1.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		return true;
	}

	/**
	 * This method checks whether ExceptionalBank.addCoins() method throws an
	 * IllegalArgumentException with an appropriate error message when it is passed
	 * a null reference as input argument.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsIllegalArgumentException() {
		try {
			// create an exceptional bank with default capacity 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins(null);
			System.out.println("Problem detected. The method call to bank.addCoins() did not "
					+ "throw an IllegalArgumentException when it is passed a null reference.");
			return false; // return false if no exception has been thrown
		} catch (IllegalArgumentException e1) {
			// check that the caught IllegalArgumentException includes
			// an appropriate error message
			if (e1.getMessage() == null // test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage()
							.contains("WARNING! The addCoins() method does not accept a null reference as input.")) {
				System.out.println("Problem detected. The IllegalArgumentException thrown by addCoins() "
						+ "call of the ExceptionalBank class when it is passed a null reference "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than IllegalArgumentException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method of the ExceptionBank class with a null reference. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true; // test passed
	}

	/**
	 * This method checks whether ExceptionalBank.addCoins() method throws a
	 * DataFormatException with an appropriate error message when it is passed an
	 * incorrectly formatted string object, for instance "quarter: five", or ": 6",
	 * or "DIME:-5"
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsInvalidDataFormat() {
		// Scenario 1 - testing with command "quarter:five"
		try {
			// create an exceptional bank with default capacity of 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins("quarter:five");
			System.out.println("Problem detected. The constructor call of the ExceptionalBank class did not "
					+ "throw a DataFormatException when it is passed an incorrectly formatted command.");
			return false; // return false if no exception has been thrown
		} catch (DataFormatException e1) {
			// check that the caught DataFormatException includes
			// an appropriate error message
			if (e1.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().contains("The format of the command line quarter:five is incorrect")) {
				System.out.println("Problem detected. The DataFormatException thrown by the addCoins() method "
						+ "call of the ExceptionalBank class when it is passed an invalid data format command "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than DataFormatException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method from the ExceptionBank class with an invalid data format command. "
					+ "A DataFormatException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		// Scenario 2 - testing with command: "DIME: -5"
		try {
			// create an exceptional bank with default capacity of 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins("DIME: -5");
			System.out.println("Problem detected. The constructor call of the ExceptionalBank class did not "
					+ "throw a DataFormatException when it is passed an incorrectly formatted command.");
			return false; // return false if no exception has been thrown
		} catch (DataFormatException e1) {
			// check that the caught DataFormatException includes
			// an appropriate error message
			if (e1.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().contains("The format of the command line DIME: -5 is incorrect")) {
				System.out.println("Problem detected. The DataFormatException thrown by the addCoins() method "
						+ "call of the ExceptionalBank class when it is passed an invalid data format command "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than DataFormatException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method from the ExceptionBank class with an invalid data format command. "
					+ "A DataFormatException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		// Scenario 3 - testing with command: ": 8"
		try {
			// create an exceptional bank with default capacity of 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins(": 8");
			System.out.println("Problem detected. The constructor call of the ExceptionalBank class did not "
					+ "throw a DataFormatException when it is passed an incorrectly formatted command.");
			return false; // return false if no exception has been thrown
		} catch (DataFormatException e1) {
			// check that the caught DataFormatException includes
			// an appropriate error message
			if (e1.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().contains("The format of the command line : 8 is incorrect")) {
				System.out.println("Problem detected. The DataFormatException thrown by the addCoins() method "
						+ "call of the ExceptionalBank class when it is passed an invalid data format command "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than DataFormatException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method from the ExceptionBank class with an invalid data format command. "
					+ "A DataFormatException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true; // test passed

	}

	/**
	 * This method checks whether ExceptionalBank.addCoins() method throws a
	 * NoSuchElementException with an appropriate error message when it is passed a
	 * String object with a correct format (meaning "string:positive_number"), but
	 * with a coin name not defined in the enum Coin's constants. For instance, when
	 * it is passed "blabla:10".
	 *
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsNoSuchElement() {
		// Scenario 1 - testing with command: "blabla:10"
		try {
			// create an exceptional bank with default capacity 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins("blabla:10");
			System.out.println("Problem detected. The addCoins() call from the ExceptionalBank class did not "
					+ "throw a NoSuchElementException when it is passed an incorrectly formatted command.");
			return false; // return false if no exception has been thrown
		} catch (NoSuchElementException e1) {
			// check that the caught NoSuchElementException includes
			// an appropriate error message
			if (e1.getMessage() == null // test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().contains("The coin name provided in the command line")) {
				System.out.println("Problem detected. The NoSuchElementException thrown by the addCoins() method "
						+ "call of the ExceptionalBank class when it is passed an invalid data format command "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than NoSuchElementException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method from the ExceptionBank class with an invalid data format command. "
					+ "A NoSuchElementException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		// Scenario 2 - testing with command: "CS300:10"
		try {
			// create an exceptional bank with default capacity 10
			ExceptionalBank bank = new ExceptionalBank();
			bank.addCoins("CS300:10");
			System.out.println("Problem detected. The addCoins() call from the ExceptionalBank class did not "
					+ "throw a NoSuchElementException when it is passed an incorrectly formatted command.");
			return false; // return false if no exception has been thrown
		} catch (NoSuchElementException e1) {
			// check that the caught NoSuchElementException includes
			// an appropriate error message
			if (e1.getMessage() == null // test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().contains("The coin name provided in the command line")) {
				System.out.println("Problem detected. The NoSUchElementException thrown by the addCoins() method "
						+ "call of the ExceptionalBank class when it is passed an invalid data format command "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than NoSuchElementException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins() method from the ExceptionBank class with an invalid data format command. "
					+ "A NoSuchElementException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true; // test passed
	}

	/**
	 * This method checks whether the ExceptionalBank.addCoins() works appropriately
	 * when it is passed a String with a valid format.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsValidDataFormat() {
		// Scenario - 1 testing with valid command: "DIME = 4"
		try {
			ExceptionalBank bank = new ExceptionalBank(20);
			bank.addCoins("DIME: 4");
			// checking for final balance, size, expansions remaining and string output of
			// contents after adding 4 dimes
			if (!bank.getCoins().equals("(DIME, 10) (DIME, 10) (DIME, 10) (DIME, 10)") || bank.getBalance() != 40
					|| bank.getSize() != 4 || bank.getExpansions() != 2) {
				System.out.println(
						"Problem detected. Your output for a method call on addCoins() doesn't match the expected output for a valid command.");
				return false;
			}
		} catch (Exception e1) {
			System.out.println("Problem detected. An unexpected exception has been thrown");
			return false; // return false if any type of exception thrown
		}

		// Scenario - 2: testing with valid command: "niCKeL: 2"
		try {
			ExceptionalBank bank2 = new ExceptionalBank(20);
			bank2.addCoins("niCKeL:2");
			// checking for final balance, size, expansions remaining and string output of
			// contents after adding 4 dimes
			if (!bank2.getCoins().equals("(NICKEL, 5) (NICKEL, 5)") || bank2.getBalance() != 10 || bank2.getSize() != 2
					|| bank2.getExpansions() != 2) {
				System.out.println(
						"Problem detected. Your output for a method call on addCoins() doesn't match the expected output for a valid command.");
				return false;
			}
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown for a valid command");
			return false; // return false if type of exception thrown
		}
		return true;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method loads
	 * appropriately without throwing any exception when it is passed a found file.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsFileFound() {
		try {
			// create a new exceptional bank with default capacity 10
			ExceptionalBank bank = new ExceptionalBank();
			File file = new File("sample.txt"); // create a new file title "sample.txt"

			// addition one dime, one penny and one quarter
			bank.addCoin(Coin.DIME);
			bank.addCoin(Coin.PENNY);
			bank.addCoin(Coin.QUARTER);

			// save contents of bank in "sample.txt" using saveBankSummary()
			bank.saveBankSummary​(file);
			bank.empty(); // call empty() to avoid adding coins twice
			bank.loadCoins​(file); // reading contents from saved file
			// check if coin count is correct, if not return false
			if (bank.getSize() != 3) {
				System.out.println(
						"Problem detected. Your output for a method call on loadCoins() doesn't match the expected output for a valid argument.");
				return false; // return false if size isn't 3
			}

		} catch (Exception e) {
			// return false if any exception is thrown because the arguments are valid and
			// output should be correct
			System.out.println(
					"Problem detected. Your output for a method call on loadCoins() doesn't match the expected output for a valid argument.");
			return false;
		}
		return true;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method throws a
	 * FileNotFoundException when it is passed a non found file.
	 *
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsFileNotFound() {
		try {
			// create an exceptional bank with default capacity 10
			ExceptionalBank bank2 = new ExceptionalBank();
			// add three coins: dime, quarter, nickel
			bank2.addCoin(Coin.DIME);
			bank2.addCoin(Coin.QUARTER);
			bank2.addCoin(Coin.NICKEL);
			File file = new File("NoFile.txt"); // create a non-existing file
			bank2.loadCoins​(file); // call loadCoins() passing a non-existing file
			return false; // return false if no exception thrown
		} catch (FileNotFoundException e) {
			// check that the caught FileNotFoundException includes
			// an appropriate error message
			if (e.getMessage() == null // test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e.getMessage().contains("not found")) {
				System.out.println("Problem detected. The FileNotFoundException thrown by the loadCoins() method "
						+ "call of the ExceptionalBank class when it is passed a non-existing file "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e1) {
			// an exception other than FileNotFoundException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "constructor of the ExceptionBank class with an invalid data format argument. "
					+ "A FileNotFoundException was expected to be thrown. " + "But, it was NOT the case.");
			e1.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		return true;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method throws a
	 * NullPointerException when it is passed a null reference.
	 *
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsNullReference() {
		try {
			ExceptionalBank bank = new ExceptionalBank();
			File file = new File("sample1.txt");
			file = null;
			bank.loadCoins​(file);
			return false; // return false if no exception is thrown
		} catch (NullPointerException e) {
			// check that the caught NullPointerException includes
			// an appropriate error message
			if (e.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e.getMessage().contains("null")) {
				System.out.println("Problem detected. The NullPointerException thrown by the loadCoins() method "
						+ "call of the ExceptionalBank class when it is passed an null reference "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e1) {
			// an exception other than NullPointerException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "loadCoins() method of the ExceptionBank class with an null reference. "
					+ "A NullPointerException was expected to be thrown. " + "But, it was NOT the case.");
			e1.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		return true;
	}

	/**
	 * This method checks whether the ExceptionalBank.removeCoin() method throws a
	 * NoSuchElementException with an appropriate error message, when it is called
	 * on an empty bank.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testRemoveCoinEmptyBank() {
		try {
			// created an exceptional bank with capacity 10
			ExceptionalBank bank1 = new ExceptionalBank();
			// added a dime and a nickel to make size 2
			bank1.addCoin(Coin.DIME);
			bank1.addCoin(Coin.NICKEL);
			bank1.empty();// emptied the bank to ensure size is 0
			bank1.removeCoin(); // call removeCoin() on an empty bank to test the method
			return false; // return false if exception is not thrown
		} catch (NoSuchElementException e) {
			// check that the caught NoSuchElementException includes
			// an appropriate error message
			if (e.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e.getMessage().contains("WARNING! This bank is empty. Unable to remove a coin.")) {
				System.out.println("Problem detected. The NoSuchElementException thrown by the removeCoin() method "
						+ "call of the ExceptionalBank class when it is called on an empty bank "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e1) {
			// an exception other than NoSuchElementException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "removeCoin() method of the ExceptionBank class with an empty bank. "
					+ "A NoSuchElementException was expected to be thrown. " + "But, it was NOT the case.");
			e1.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}

		return true;
	}

	/**
	 * This method checks whether the ExceptionalBank constructor creates without
	 * errors an empty exceptional bank with capacity 20 when it is passed 20 as
	 * input parameter.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGoodExceptionalBankConstructor() {
		// Scenario 1 - capacity is 20
		try {
			// create an ExceptionalBank with capacity 20
			ExceptionalBank bank1 = new ExceptionalBank(20);
			// check if capacity of bank created is 20
			if (bank1.capacity() != 20) {
				return false; // return false if not
			}

		} catch (Exception e) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "constructor of the ExceptionBank class with bank with no coins. "
					+ "A bank with capacity 20 was expected to be made. " + "But, it was NOT the case.");
			e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false; // exception thrown thus return false
		}

		// Scenario 2 - capacity is 10
		try {
			ExceptionalBank bankTest = new ExceptionalBank(10);
			// check if capacity of bank created is 10
			if (bankTest.capacity() != 10) {
				return false; // return false if not
			}
		} catch (Exception e) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "constructor of the ExceptionBank class with bank with no coins. "
					+ "A bank with capacity 10 was expected to be made. " + "But, it was NOT the case.");
			e.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true;
	}

	/**
	 * This method calls the test methods implemented in this class and prints their
	 * boolean output
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println("testExceptionalBankConstructor(): " + testExceptionalBankConstructor());
		System.out.println("testAddCoin(): " + testAddCoin());
		System.out.println("testAddCoinsIllegalArgumentException(): " + testAddCoinsIllegalArgumentException());
		System.out.println("testAddCoinsInvalidDataFormat(): " + testAddCoinsInvalidDataFormat());
		System.out.println("testAddCoinsValidDataFormat(): " + testAddCoinsValidDataFormat());
		System.out.println("testAddCoinsNoSuchElement(): " + testAddCoinsNoSuchElement());
		System.out.println("testLoadCoinsFileFound(): " + testLoadCoinsFileFound());
		System.out.println("testLoadCoinsFileNotFound(): " + testLoadCoinsFileNotFound());
		System.out.println("testLoadCoinsNullReference(): " + testLoadCoinsNullReference());
		System.out.println("testRemoveCoinEmptyBank(): " + testRemoveCoinEmptyBank());
		System.out.println("testGoodExceptionalBankConstructor(): " + testGoodExceptionalBankConstructor());

	}
}
