package com.group16.enigma;

import com.group16.enigma.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class cLogout extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public cLogout() {
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
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageView.class, 0));
        //Click on Log Out
		solo.clickInList(1, 2);
        //Wait for activity: 'com.group16.enigma.SignInActivity'
		assertTrue("com.group16.enigma.SignInActivity is not found!", solo.waitForActivity(com.group16.enigma.SignInActivity.class));
	}
}
