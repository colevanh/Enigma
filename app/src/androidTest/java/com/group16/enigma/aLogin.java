package com.group16.enigma.test;

import com.group16.enigma.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class aLogin extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public aLogin() {
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
        //Set default small timeout to 10761 milliseconds
		Timeout.setSmallTimeout(10761);
        //Enter the text: 'arminv94@yahoo.com'
		solo.clearEditText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.field_email));
		solo.enterText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.field_email), "arminv94@yahoo.com");
        //Enter the text: 'password'
		solo.clearEditText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.field_password));
		solo.enterText((android.widget.EditText) solo.getView(com.group16.enigma.R.id.field_password), "password");
        //Click on Signin
		solo.clickOnView(solo.getView(com.group16.enigma.R.id.email_sign_in_button));
        //Wait for activity: 'com.group16.enigma.MainActivity'
		assertTrue("com.group16.enigma.MainActivity is not found!", solo.waitForActivity(com.group16.enigma.MainActivity.class));
	}
}
