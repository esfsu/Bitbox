/*
 * PopupFactory Class
 *
 * This class is used to create Popup objects.
 * 
 * 
 * 02/03/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class PopupFactory {
	
	static BitboxApp selectedSongsHolder ;
  
  public static Popup createPopup(PopupType pt, Activity currentActivity, Context currentContext){
	  selectedSongsHolder = (BitboxApp)(currentActivity).getApplication();
	  
	  if (!(SelectedSongsSingleton.getInstance().getSelectedSongs().isEmpty())) {
	    switch(pt){
          case SCANFORMUSIC:
            return new ScanForMusicPopup(currentActivity, currentContext);
       /* case SCANFORMISSINGDATA:
    	    return new ScanForMissingDataPopup(currentActivity, currentContext); 
          case EDIT:
      	    return new EditPopup(currentActivity, currentContext);
          case UNDO:
      	    return new UndoPopup(currentActivity, currentContext);   */
          default:
            return null;
        }
	  }
	  else{
	    Toast.makeText(currentContext,
		  "Please select a song.",
		     Toast.LENGTH_SHORT).show();
		return null;
	  }
  }
}  

  

  
