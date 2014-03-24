package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class OptionButtonsFactory extends CustomView {
   
	 private ImageView scanForMusicOptionButton;
	 private ImageView scanForMissingDataOptionButton;
	 private ImageView editOptionButton;
	 private ImageView undoOptionButton;
	 
	 public OptionButtonsFactory(Activity currentActivity, Context currentContext){
		 
		 setCurrentActivity(currentActivity);
		 setCurrentContext(currentContext);
		 scanForMusicOptionButton = (ImageView) getCurrentActivity().findViewById(R.id.scanDirectoryButton);
		 scanForMissingDataOptionButton = (ImageView) getCurrentActivity().findViewById(R.id.scanForMissingDataButton);
		 editOptionButton = (ImageView) getCurrentActivity().findViewById(R.id.editButton);
		 undoOptionButton = (ImageView) getCurrentActivity().findViewById(R.id.undoButton);
     
		 this.setOnClickListeners(currentContext);
		 
	 }
	 
	 private void setOnClickListeners(final Context currentContext){
		 
		 scanForMusicOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	       PopupFactory.createPopup(PopupType.SCANFORMUSIC, getCurrentActivity(), currentContext);
	     }
	   });
		 
		 editOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	     PopupFactory.createPopup(PopupType.EDIT, getCurrentActivity(), currentContext);
	     }
	   });
		 
		 /* The popups for these options have not been implemented yet
		 scanForMissingDataOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	     PopupFactory.createPopup(PopupType.SCANFORMISSINGDATA, currentContext);
	     }
	   });
		 
		 
		 
		 undoOptionButton.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	     PopupFactory.createPopup(PopupType.UNDO, currentContext);
	     }
	   });
	   */
	 }

}
