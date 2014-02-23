package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Popup {
  
  
  public void createPopup(int popupType, Context context){
  
    switch(popupType){
      case 1:
        ScanForMusicPopup dialog1 = new ScanForMusicPopup(context);
        break;
      case 2:
    	ScanForMissingDataPopup dialog2 = new ScanForMissingDataPopup(context);  
    	break;
      case 3:
      	EditPopup dialog3 = new EditPopup(context);
        break;
      case 4:
      	UndoPopup dialog4 = new UndoPopup(context);  
      	break; 
      }
  }
}  
  
  class ScanForMusicPopup {
    ScanForMusicPopup(Context context){  
	  Dialog scanForMusicDialog = new Dialog(context); 
	  // initialize all widgets in the popup
    }
  }
  
  class ScanForMissingDataPopup {  
	ScanForMissingDataPopup(Context context){  
      Dialog scanForMissingDialog = new Dialog(context);
      // initialize all widgets in the popup
	}
  }
  
  class EditPopup {  
    EditPopup(Context context){  
	  Dialog settingsDialog = new Dialog(context);
	  // initialize all widgets in the popup
    }
  }
      
  class UndoPopup {  
    UndoPopup(Context context){  
      Dialog undoDialog = new Dialog(context);
      // initialize all widgets in the popup
    }
  }
  
