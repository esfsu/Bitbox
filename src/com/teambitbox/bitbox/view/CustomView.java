/*
 * CustomView Class
 *
 * This class is the parent class of all the Views in the application.It contains the attributes 
 * for the current context and activity of a specific view.
 * 
 * 26/02/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.content.Context;

public class CustomView {

	private Context mCurrentContext; // defines current Context for the View object
	private Activity mCurrentActivity; // // defines current Activity for the View object
	
	// Accessors and Mutators
	
	public void setCurrentContext(Context c) {
		mCurrentContext = c;	
	} // end setCurrentContext

	public Context getCurrentContext() {
		return mCurrentContext;
	} // end getCurrentContext
	
	public void setCurrentActivity(Activity a) {
		mCurrentActivity = a;	
	}// end setCurrentActivity

	public Activity getCurrentActivity() {
		return mCurrentActivity;
	}// end getCurrentActivity
} // end CustomView class

