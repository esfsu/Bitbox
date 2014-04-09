/*
 * SettingsPopup class
 *
 * Popup for the Settings option 
 * 
 * 
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import java.util.ArrayList;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SettingsPopup extends Popup {

	private ArrayList mSelectedItems; // which items have been selected in the checkbox list
	private SongListView mSongList; // used to change the row layout depending on the settings
	
	public SettingsPopup (Activity currentActivity, Context currentContext, SongListView songlist){
		setCurrentActivity(currentActivity);
		setCurrentContext(currentContext);
		setLayout();
		showLayout();
	}
	
	@Override
	void setLayout() {
		mSelectedItems = new ArrayList();  // Where we track the selected items
    builder = new AlertDialog.Builder(getCurrentActivity());
    // Set the dialog title
    builder.setTitle(R.string.settingsTitle)
    // Specify the list array, the items to be selected by default (null for none),
    // and the listener through which to receive callbacks when items are selected
           .setMultiChoiceItems(R.array.settingOptions, null,
                      new DialogInterface.OnMultiChoiceClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which,
                       boolean isChecked) {
                   if (isChecked) {
                       // If the user checked the item, add it to the selected items
                       mSelectedItems.add(which);
                   } else if (mSelectedItems.contains(which)) {
                       // Else, if the item is already in the array, remove it 
                       mSelectedItems.remove(Integer.valueOf(which));
                   }
               }
           })
    // Set the action buttons
           .setPositiveButton(R.string.okOption, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   // User clicked OK, so save the mSelectedItems results somewhere
                   // or return them to the component that opened the dialog
                   
               }
           })
           .setNegativeButton(R.string.cancelOption, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   
               }
           });

    popup = builder.create();
		
	}

	public SongListView getSongListView() {
		return mSongList;
	}

	public void setSongListView(SongListView songList) {
		mSongList = songList;
	}

}
