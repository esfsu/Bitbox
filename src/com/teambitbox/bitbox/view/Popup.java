package com.teambitbox.bitbox.view;

import android.app.AlertDialog;
import android.content.Context;

public abstract class Popup {
	protected AlertDialog.Builder builder;
	private Context context;
	
	public void setContext(Context c){
		context = c;	
	}

	public Context getContext(){
		return context;
	}

	abstract void setLayout();
	
	public void showLayout(){
		builder.show();
	}
	
}
