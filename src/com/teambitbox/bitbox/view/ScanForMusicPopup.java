package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ScanForMusicPopup extends Popup {
	
	private String inputDirectoryString =  "";

	public ScanForMusicPopup(Activity currentActivity, Context context) {
	  setCurrentActivity(currentActivity);
  	setCurrentContext(context);
  	this.builder = new AlertDialog.Builder(getCurrentContext());
  	this.setLayout();
	  this.showLayout();
	  
  }
  
	@Override
	public void setLayout() {
		
    builder.setMessage(R.string.scanDirectoryTitle)
           .setPositiveButton(R.string.okOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 scanSelectionDialog();
               }
           })
           .setNegativeButton(R.string.cancelOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   // User cancelled the dialog
               }
           });
    builder.create();		
	}
	
	private void scanSelectionDialog(){
		builder = new AlertDialog.Builder(getCurrentContext()); // builds an alert dialog using the Builder class
		
	// inflate and set the layout for the dialog
		LayoutInflater inflater = (LayoutInflater) getCurrentContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE ); 
		View selectionDialog = inflater.inflate(R.layout.scan_selection_dialog_layout, null);
    builder.setView(selectionDialog);
	      
		EditText directoryInputView = (EditText) selectionDialog.findViewById(R.id.directoryInput);
		
		// listener for change of text event
		directoryInputView.addTextChangedListener(new TextWatcher() {
			@Override
      public void afterTextChanged(Editable s) {
           setDirectoryInput(s.toString());
      }

     @Override
     public void beforeTextChanged(CharSequence s, int start, int count, int after) {
     }

     @Override
     public void onTextChanged(final CharSequence s, int start, int before, int count) {             
     }
    });
    
    // add action buttons
    builder.setPositiveButton(R.string.scanDirectoryOption, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   
              	  // if the user didn't enter a directory name, a toast message will appear.
                  if (getDirectoryInput().equals("")) {
                    Toast.makeText(getCurrentContext(), "You did not enter a directory", Toast.LENGTH_SHORT).show();
                  }
                  else{
                  	
                    progressBarDialog();
                  }
               }
           })
           .setNegativeButton(R.string.scanDeviceOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 progressBarDialog();  
               }
           });  
    
    builder.create();
    showLayout(); // displays popup
	}
	
	private void progressBarDialog(){
		final ProgressDialog progressDialogPopup = new ProgressDialog(getCurrentContext()); // initializes progressDialog popup
    progressDialogPopup.setMax(100); // sets the maximum amount of the progress bar
    
    progressDialogPopup.setMessage("Scanning...");
    
    // set the title of the progress bar popup depending on the scan selection of the user
    if ( getDirectoryInput().equals(""))
      progressDialogPopup.setTitle(getDirectoryInput() + " is been scanned for music");
    else
      progressDialogPopup.setTitle("Device is been scanned for music");

    progressDialogPopup.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // style of progress bar
    // set cancel button
    progressDialogPopup.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    progressDialogPopup.show();
    
    
    // everything below is an example; a controller method needs to be implemented
    
    // create handler to increment progress bar by one
    final Handler handle = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        progressDialogPopup.incrementProgressBy(1);
      }
    };
    
    // increment by 1 if progress bar has not reach maximum
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (progressDialogPopup.getProgress() <= progressDialogPopup.getMax()) {
            Thread.sleep(200);
            handle.sendMessage(handle.obtainMessage());
            if (progressDialogPopup.getProgress() == progressDialogPopup.getMax()) {
            	progressDialogPopup.dismiss();
            }
          }
        } catch (Exception e) {
            e.printStackTrace();
          } 
      }
    }).start();
   
   
	}
	
	private void resultsDialog()
	{
		builder = new AlertDialog.Builder(getCurrentContext());
    builder.setTitle("x songs were found!")
        
           .setPositiveButton(R.string.okOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 progressBarDialog();  
               }
           })
           .setNegativeButton(R.string.cancelOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   // User cancelled the dialog
               }
           })
           .setItems(R.array.songsFoundListExample, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
               // The 'which' argument contains the index position
               // of the selected item
           }
    });
    builder.create();
  	showLayout();
	}
	
	//
  private String getDirectoryInput() {
		return this.inputDirectoryString;
	}

	private void setDirectoryInput(String directory) {
		this.inputDirectoryString = directory;
	}
}

