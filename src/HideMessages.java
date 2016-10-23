import java.util.*;
import java.lang.*;

public class HideMessages {

	private String originalMessage;	// User's message
	private String jumbledMessage;	// User's message, jumbled up
	private byte[] deBytesMessage;	// Decrypted message as byte array
	private Crypto crypto;			// Crypto object
	private Random r;				// Random object 
	private String[] words;			// User's message as a collection of Strings

	// 1-arg constructor, crypto object must be initialzed beforehand
	public HideMessages(Crypto crypto) {
		this.crypto = crypto;
		this.initializeCrypto();
		this.originalMessage = crypto.getMessage();
		this.deBytesMessage = crypto.sendEncryptedTextAsBytes();
		this.resetMessage();
		this.r = new Random();
		this.words = this.originalMessage.split("\\s+");
	}

	// Revert jumbled message back to default (byte array as a String)
	public void resetMessage() {
		this.jumbledMessage = crypto.sendEncryptedTextAsString();
	}

	// Return user's message
	public String getMessage() {
		return this.originalMessage;
	}

	// 
	public String getJumbledMessage() {
		return this.jumbledMessage;
	}

	// Set up the Crypto member field 
	public void initializeCrypto() {
		this.crypto.initializeCipherEn();
		this.crypto.setEncryptedText();
	}

	// Reverse the user's message
	public void reverseMessage() {
		char[] letters = this.originalMessage.toCharArray();
		int start = 0;
		int end = letters.length - 1;
		char temp;
		while (end > start) {
			temp = letters[start];
			letters[start] = letters[end];
			letters[end] = temp;
			end--;
			start++;
		}
		this.jumbledMessage = String.valueOf(letters);
	}

	// Turn the message into all one letter
	public void setToAllOneLetter() {
		StringBuilder returnString = new StringBuilder(this.originalMessage);
		char randomLetter = this.chooseRandomLetter();
		for (int i = 0; i < this.originalMessage.length(); i++) {
			if (!this.isWhiteSpace(returnString.charAt(i))) {
				returnString.setCharAt(i, randomLetter);
			}
		}
		this.jumbledMessage = returnString.toString();
	}

	// Turn the message into random numbers
	public void setToRandomNumbers() {
		char randomIntChar;
		int randomInt;
		StringBuilder returnString = new StringBuilder(this.originalMessage);
		for (int i = 0; i < this.originalMessage.length(); i++) {
			if (!this.isWhiteSpace(returnString.charAt(i))) { 
				randomInt = this.r.nextInt(10);
				randomIntChar = Character.forDigit(randomInt, 10);
				returnString.setCharAt(i, randomIntChar);
				}
		}
		this.jumbledMessage = returnString.toString();
	}

	/* Turn the message into a collection of random characters from the English
	   alphabet
	*/
	public void setToChaos() {
		StringBuilder returnString = new StringBuilder(this.originalMessage);
		for (int i = 0; i < returnString.length(); i++) {
			if (!this.isWhiteSpace(returnString.charAt(i))) {
				returnString.setCharAt(i, this.chooseRandomLetter());
			}
		}
		this.jumbledMessage = returnString.toString();
	}

	// Return a random letter of the alphabet
	public char chooseRandomLetter() {
		char randomLetter = (char)(this.r.nextInt(26) + 'a');
		return randomLetter;
	}

	// Print the jumbled message to the console
	public void printJumbledMessage() {
		System.out.println(this.jumbledMessage);
	}

	// Determine if a character is whitespace
	public boolean isWhiteSpace(char letter) {
		return letter == ' ';
	}

}