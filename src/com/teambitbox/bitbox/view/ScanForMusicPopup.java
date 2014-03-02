package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

public class ScanForMusicPopup extends Popup {
	
  public ScanForMusicPopup(Context context) {
  	this.setContext(context);
	  this.setLayout();
	  this.showLayout();  
  }
  
	@Override
	public void setLayout() {
		//the build an alert dialog using the Builder class
    builder = new AlertDialog.Builder(getContext());
    builder.setMessage(R.string.scanDirectoryTitle)
           .setPositiveButton(R.string.yesOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 progressBarDialog();
                 resultsDialog();
               }
           })
           .setNegativeButton(R.string.noOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   // User cancelled the dialog
               }
           });
    builder.create();		
	}
	
	private void progressBarDialog(){
		final ProgressDialog progressDialogPopup = new ProgressDialog(getContext()); // Initializes progressDialog popup
    progressDialogPopup.setMax(100); // sets the maximum amount
    progressDialogPopup.setMessage("Scanning...");
    progressDialogPopup.setTitle("x directory is been Scanned for music");
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
		AlertDialog.Builder resultDialog = new AlertDialog.Builder(getContext());
    builder.setTitle("x songs were found!")
        
           .setPositiveButton(R.string.yesOption, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                 progressBarDialog();  
               }
           })
           .setNegativeButton(R.string.noOption, new DialogInterface.OnClickListener() {
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
    resultDialog.create();
  	resultDialog.show();
    
	}
}

