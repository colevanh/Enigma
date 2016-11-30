package com.group16.enigma;

import android.util.Base64;
import android.util.Log;

import org.junit.Test;

import java.security.GeneralSecurityException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EnigmaUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void uniqueHash(){
        String friend1 = "person1@enigma.com";
        String friend2 = "person2@enigma.com";
        String friend3 = "fake@enigma.com";
        int hash1 = FriendsListAdapter.hash(friend1,friend2);
        int hash2 = FriendsListAdapter.hash(friend2,friend1);

        assertEquals(hash1, hash2);

        int fakeHash = FriendsListAdapter.hash(friend1, friend3);
        assertNotEquals(hash1, fakeHash);
    }


    @Test
    public void DPReturn(){
        String DP = "bee.png";
        int drawable = FriendsListAdapter.getDPDrawable(DP);

        assertEquals(drawable,  R.drawable.bee);

        int drawable2 = FriendsListAdapter.getDPDrawable(null);
        assertEquals(drawable2, R.drawable.bird);
    }
}