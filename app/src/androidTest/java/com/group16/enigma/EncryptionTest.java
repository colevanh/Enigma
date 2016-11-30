package com.group16.enigma;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.GeneralSecurityException;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EncryptionTest {
    @Test
    public void EncryptandDecrypt() throws Exception {
        String secretKey = "Enigma";
        String message = "Hello world";
        Aes.SecretKeys keys;
        String hardcodeSalt = "VMq4oX7rxhRS8r0vwWEc2uspgsu/rpF7G+mw3vgtUzrAcsNGHQJb+DKXDrolxUTBGpq8XAkKRUWN5ZeSRUi7dSYavjO/gwErrDv2sDzoEaTvQCXLNpAhm6cwxLidAw3nTOY/wITpY4DiZzbV8bMatUjhCRPsujBHZY8CqD0oTbU=";

        try {

            keys = Aes.generateKeyFromPassword(secretKey, hardcodeSalt);
            String encryptedMessage = Aes.encrypt(message, keys).toString();
            String decryptedMessage = Aes.decryptString(new Aes.CipherTextIvMac(encryptedMessage), keys);
            assertEquals(decryptedMessage, message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void IncorrectKeyDecrypt() throws Exception {
        String fakeKey = "someincorrectkey";
        String secretKey = "Enigma";
        String message = "Hello world";
        Aes.SecretKeys keys;
        Aes.SecretKeys fakeKeys;
        String hardcodeSalt = "VMq4oX7rxhRS8r0vwWEc2uspgsu/rpF7G+mw3vgtUzrAcsNGHQJb+DKXDrolxUTBGpq8XAkKRUWN5ZeSRUi7dSYavjO/gwErrDv2sDzoEaTvQCXLNpAhm6cwxLidAw3nTOY/wITpY4DiZzbV8bMatUjhCRPsujBHZY8CqD0oTbU=";

        try {
            keys = Aes.generateKeyFromPassword(secretKey, hardcodeSalt);
            fakeKeys = Aes.generateKeyFromPassword(fakeKey, hardcodeSalt);
            String encryptedMessage = Aes.encrypt(message, keys).toString();
            String decryptedMessage = Aes.decryptString(new Aes.CipherTextIvMac(encryptedMessage), fakeKeys);
            assertNotEquals(decryptedMessage, message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
