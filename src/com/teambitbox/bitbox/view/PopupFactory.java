package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopupFactory {
  
  public static Popup createPopup(PopupType pt, Context c){
    switch(pt){
      case SCANFORMUSIC:
        return new ScanForMusicPopup(c);
      /*case SCANFORMISSINGDATA:
    	  return new ScanForMissingDataPopup(context);  
      case EDIT:
      	return new EditPopup(context);
      case UNDO:
      	return new UndoPopup(context);*/  
      default:
        return null;
      }
  }
}  

  

  
