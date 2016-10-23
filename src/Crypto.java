import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
import java.util.*;
import java.lang.*;

public class Crypto {

	private KeyGenerator keyGen;
	private SecretKey myKey;
	private Cipher myCipher;
	private String stringMessage;
	private byte[] stringBytes;
	private byte[] stringBytesEncrypted;
	private byte[] stringBytesDecrypted;
	private String[] keyAlgos = {"AES", "DES", "DESede", "HmacSHA1", "HmacSHA256"};
	private String algoKey = "DES";

	public Crypto(String message) {
		try {
			this.stringMessage = message;
			this.keyGen = KeyGenerator.getInstance(this.algoKey);
			this.generateKey();
			this.myCipher = Cipher.getInstance(this.algoKey);
			this.setByteArray(this.stringMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getMessage() {
		return this.stringMessage;
	}

	private boolean checkKey(String key) {
		for (int i = 0; i < this.keyAlgos.length; i++) {
			if ( key == this.keyAlgos[i] ) {
				return true;
			}
		}

		return false;
	}

	private void generateKey() {
		try {
			this.myKey = this.keyGen.generateKey();
		} catch (NullPointerException e) {
			// TODO: Implement solution for no key
			e.printStackTrace();
		} 
	}
	
	private String chooseAlgoKey() {
		Random randomNum = new Random();
		int min = 0;
		int max = 4;
		int index = randomNum.nextInt(max - min + 1) + min;
		return this.keyAlgos[index];
	}

	private void setByteArray(String message) {
		try {
			this.stringBytes = this.stringMessage.getBytes("UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] getByteArray() {
		return this.stringBytes;
	}

	public void initializeCipherEn()   {
		try {
			this.myCipher.init(Cipher.ENCRYPT_MODE, myKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializeCipherDe()  {
		try {
			this.myCipher.init(Cipher.DECRYPT_MODE, myKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEncryptedText() {
		try {
			this.stringBytesEncrypted = this.myCipher.doFinal(this.stringBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDecryptedText() {
		try {
			this.stringBytesDecrypted = this.myCipher.doFinal(this.stringBytesEncrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] sendEncryptedTextAsBytes() {
		return this.stringBytesEncrypted;
	}

	public String sendEncryptedTextAsString() {
		String text = new String(this.stringBytesEncrypted);
		return text;
	}

	public String sendDecryptedTextAsString() {
		String text = new String(this.stringBytesDecrypted);
		return text;
	}

	private void printEncryptedText() {
		System.out.println(this.sendEncryptedTextAsString());
	}
}