/*
 * EditPopup Class
 *
 * This view class is used to create the popup for the Edit option. 
 * 
 * 
 * 05/04/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.EditID3Activity;
import com.teambitbox.bitbox.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPopup extends Popup {

	private String fileDestination = null; // where the file will go
	
	public EditPopup(Activity currentActivity, Context context) {
  	setCurrentContext(context);
	  setCurrentActivity(currentActivity);
	  setLayout();
  	showLayout();	  
  }

	@Override
	void setLayout() {
		builder = new AlertDialog.Builder(getCurrentContext());
		LayoutInflater inflater = (LayoutInflater) getCurrentContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View editDialog = inflater.inflate(R.layout.edit_dialog_layout, null);

		builder.setView(editDialog);
		builder.setTitle("Select Edit Option");
		Button editFileNameOption = (Button) editDialog.findViewById(R.id.editFileNameOptionButton);
		Button editIdTagsOption = (Button) editDialog.findViewById(R.id.editIdTagOptionButton);
		Button moveFileOption = (Button) editDialog.findViewById(R.id.moveFileOptionButton);
		popup = builder.create();

		editIdTagsOption.setOnClickListener(new OnClickListener() { 
	    @Override
	    public void onClick(View arg0) {
	    	popup.dismiss();
	    	Intent myIntent = new Intent(getCurrentActivity(), EditID3Activity.class);
	    	getCurrentActivity().startActivity(myIntent);
	    }
	  });
		
    moveFileOption.setOnClickListener(new OnClickListener() { 
	    @Override
	    public void onClick(View arg0) {
	    	Log.d("EditPopup", "move file");
	      popup.dismiss();
	    	showMoveFileDialog();
	    }   
	  });
   
	}

	private void showMoveFileDialog()
	{
		builder = new AlertDialog.Builder(getCurrentContext());
		LayoutInflater inflater = (LayoutInflater) getCurrentContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View moveDialog = inflater.inflate(R.layout.move_song_dialog_layout, null);

		builder.setView(moveDialog);
		builder.setTitle("Select File Destination");
		popup = builder.create();
		Button cancelOption = (Button) moveDialog.findViewById(R.id.cancelMoveButton);
		Button moveToDefaultMusicDirectoryOption = (Button) moveDialog.findViewById(R.id.moveToDefaultMusicDirectoryButton);
		Button moveToSelectedDirectoryOption = (Button) moveDialog.findViewById(R.id.moveToSelectedDirectoryButton);
		EditText fileDestinationInputField = (EditText) moveDialog.findViewById(R.id.fileDestinationInputField);
	
	  // listener for change of text event
		fileDestinationInputField.addTextChangedListener(new TextWatcher() {
		  @Override
	    public void afterTextChanged(Editable s) {
	      fileDestination = s.toString();
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    @Override
	    public void onTextChanged(final CharSequence s, int start, int before, int count) {             
	    }
	  });
		
		
		cancelOption.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	     	 SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
	    	 popup.dismiss();
	     }
	  });
		
    moveToDefaultMusicDirectoryOption.setOnClickListener(new OnClickListener() { 
	    @Override
	    public void onClick(View arg0) {
	      if (fileDestination.isEmpty())
	      {
	    	  Toast.makeText(getCurrentContext(), "You did not enter a directory", Toast.LENGTH_SHORT).show();
	    	  SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
	      }
	      /*else if (directory does not exist)
	      {
	    	   Toast.makeText(getCurrentContext(), "Invalid directory", Toast.LENGTH_SHORT).show();
	      }*/
	      else
	      {
	    	  // use method to move to default directory here
	     	  SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
	    	  popup.dismiss();
	      } 
	    } 
	  });
   
   moveToSelectedDirectoryOption.setOnClickListener(new OnClickListener() { 
     @Override
     public void onClick(View arg0) {
    	 // use method to move to selected directory here
    	 
    	 SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
    	 popup.dismiss(); 
     }
   });
   
 	 showLayout();
	}
	
}
