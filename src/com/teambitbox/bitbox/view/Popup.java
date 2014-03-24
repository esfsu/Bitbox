package com.teambitbox.bitbox.view;

import android.app.AlertDialog;

public abstract class Popup extends CustomView {
	protected AlertDialog.Builder builder;

	abstract void setLayout();
	
	public void showLayout(){
		builder.show();
	}

}
