package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.teambitbox.bitbox.R;

public class UndoPopup extends Popup {
  
  public UndoPopup(Activity currentActivity, Context context) {
    setCurrentActivity(currentActivity);
    setCurrentContext(context);
    setLayout();
    showLayout();
  }

  @Override
  void setLayout() {
    builder = new AlertDialog.Builder(getCurrentContext());
    builder.setTitle(R.string.undoTitle);
    builder.setMessage(R.string.undoMessage)
           .setPositiveButton(R.string.okOption,
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  // call undo action
                 
                }
              })
           .setNegativeButton(R.string.cancelOption,
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  // User cancelled the dialog
                }
              });
    popup = builder.create();
    
  }

}
