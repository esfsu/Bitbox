package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditPopup extends Popup {
	
	public EditPopup(Activity currentActivity, Context context) {
  	setCurrentContext(context);
	  setCurrentActivity(currentActivity);
	  this.setLayout();
  
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
		
		editIdTagsOption.setOnClickListener(new OnClickListener() { 
	     @Override
	     public void onClick(View arg0) {
	       
	     }
	   });
	}

}
