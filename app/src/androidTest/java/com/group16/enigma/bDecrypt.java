package com.group16.enigma;

import com.group16.enigma.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class bDecrypt extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public bDecrypt() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.group16.enigma.MainActivity'
		solo.waitForActivity(com.group16.enigma.MainActivity.class, 2000);
        //Set default small timeout to 18497 milliseconds
		Timeout.setSmallTimeout(18497);
        //Click on Chat
		solo.clickOnText(java.util.regex.Pattern.quote("Chat"));
        //Click on michaellasheng@gmailcom
		solo.clickOnText(java.util.regex.Pattern.quote("michaellasheng@gmailcom"));
        //Wait for activity: 'com.group16.enigma.ChatActivity'
		assertTrue("com.group16.enigma.ChatActivity is not found!", solo.waitForActivity(com.group16.enigma.ChatActivity.class));
        //Enter the text: 'TEST'
		solo.clearEditText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.user_key));
		solo.enterText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.user_key), "TEST");
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Decode
		solo.clickOnView(solo.getView(com.group16.enigma.R.id.action_decode_message));
        //Assert that: ' Hey' is shown
		assertTrue("' Hey' is not shown!", solo.waitForView(solo.getView(com.group16.enigma.R.id.messageTextView)));
        //Assert that: ' Hey' is shown
		assertTrue("' Hey' is not shown!", solo.waitForView(solo.getView(com.group16.enigma.R.id.messageTextView)));

	}
}
