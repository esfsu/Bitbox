/*
 * OptionButtonFactory class
 *
 * This class is used to build the buttons with their respective listeners.
 * 
 * 02/03/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.EditID3Activity;
import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OptionButtonsFactory extends CustomView {
   
	// private Button scanForMusicOptionButton;
	//  private Button scanForMissingDataOptionButton;
	 private Button editOptionButton;
	// private Button undoOptionButton;
	 
	 public OptionButtonsFactory(Activity currentActivity, Context currentContext){
		 
		 setCurrentActivity(currentActivity);
		 setCurrentContext(currentContext);
		// scanForMusicOptionButton = (Button) getCurrentActivity().findViewById(R.id.scanDirectoryButton);
		// scanForMissingDataOptionButton = (Button) getCurrentActivity().findViewById(R.id.scanForMissingDataButton);
		 editOptionButton = (Button) getCurrentActivity().findViewById(R.id.editButton);
		// undoOptionButton = (Button) getCurrentActivity().findViewById(R.id.undoButton);
		 
		 setOnClickListeners(getCurrentContext());
	 }
	 
	 private void setOnClickListeners(final Context currentContext){
		 
	   editOptionButton.setOnClickListener(new OnClickListener() { 
		 @Override
		 public void onClick(View arg0) {
			  
		   if (!(SelectedSongsSingleton.getInstance().getSelectedSongs().isEmpty())) {
		     Intent myIntent = new Intent(getCurrentActivity(), EditID3Activity.class);
		     getCurrentActivity().startActivity(myIntent);  
		     // PopupFactory.createPopup(PopupType.EDIT, getCurrentActivity(), currentContext);
		   }
		   else {
			 Toast.makeText(currentContext,
			   "You did not select a song.",
				  Toast.LENGTH_SHORT).show();
		   }
		 }
	   });
		 
		 /* These features will not be used
		 scanForMusicOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	       PopupFactory.createPopup(PopupType.SCANFORMUSIC, getCurrentActivity(), currentContext);
	     }
	   });
		 
		
		 
		 undoOptionButton.setOnClickListener(new OnClickListener() { 
       @Override
       public void onClick(View arg0) {
         PopupFactory.createPopup(PopupType.UNDO, getCurrentActivity(), currentContext);
       }
     });*/
		 
		 /*The popups for these options have not been implemented yet
		 scanForMissingDataOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	     PopupFactory.createPopup(PopupType.SCANFORMISSINGDATA, getCurrentActivity(), currentContext);
	     }
	   });
		 
	   */
	 }

}
