package com.teambitbox.bitbox.view;

import android.app.AlertDialog;
import android.content.Context;

public abstract class Popup {
	protected AlertDialog.Builder builder;
	private Context context;
	
	public void setContext(Context c) {
		context = c;	
	}

	public Context getContext() {
		return context;
	}

	abstract void setLayout();
	
	public void showLayout(){
		builder.show();
	}
	
}




/*
class ScanForMissingDataPopup implements Popup {  
	ScanForMissingDataPopup(Context context){  
      Dialog scanForMissingDialog = new Dialog(context);
      // initialize all widgets in the popup
	}

	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayout(PopupType popuptype) {
		// TODO Auto-generated method stub
		
	}
}
  
class EditPopup implements Popup {  
  EditPopup(Context context){  
	  Dialog settingsDialog = new Dialog(context);
	  // initialize all widgets in the popup
    }

	@Override
	public void setContext1(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayout(PopupType popuptype) {
		// TODO Auto-generated method stub
		
	}
  }
      
class UndoPopup implements Popup {  
  UndoPopup(Context context){  
    Dialog undoDialog = new Dialog(context);
    // initialize all widgets in the popup
  }

	@Override
	public void setContext1(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayout(PopupType popuptype) {
		// TODO Auto-generated method stub
		
	}*/
