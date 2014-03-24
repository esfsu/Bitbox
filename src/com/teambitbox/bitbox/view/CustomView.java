package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.content.Context;

public class CustomView {

	private Context currentContext;
	private Activity currentActivity;
	
	public void setCurrentContext(Context c) {
		this.currentContext = c;	
	}

	public Context getCurrentContext() {
		return currentContext;
	}
	
	public void setCurrentActivity(Activity a) {
		this.currentActivity = a;	
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}
}
