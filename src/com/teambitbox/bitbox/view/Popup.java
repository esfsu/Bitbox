/*
 * Popup Class
 *
 * This class is a parent class for all the Popup views
 * 
 * 02/03/2014
 * Eric Fernandez
 */


package com.teambitbox.bitbox.view;

import android.app.AlertDialog;

public abstract class Popup extends CustomView {
	protected AlertDialog.Builder builder;
	protected AlertDialog popup;

	abstract void setLayout();
	
	public void showLayout(){
		popup.show();
	}

}
