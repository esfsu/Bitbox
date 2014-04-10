/*
 * EditID3Activity class
 *
 * Activity used to edit metadata of song files 
 * 
 * 
 * Eric Fernandez
 */

package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.BitboxApp;
import com.teambitbox.bitbox.view.SelectedSongsSingleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditID3Activity extends Activity{
  
  private EditText editSongTitleInputField;
  private EditText editArtistNameInputField;
  private EditText editAlbumArtistInputField;
  private EditText editGenreInputField;
  private EditText editComposerInputField;
  private EditText editTrackBeginningInputField;
  private EditText editTrackEndInputField;
  private EditText editDiscBeginningInputField;
  private EditText editDiscEndInputField;
  private EditText editYearInputField;
  private Button confirmButton;
  private Button cancelButton;
  protected BitboxApp singletonHolder;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.edit_id3_layout);
    setViewIds();
    singletonHolder = (BitboxApp)getApplication();
    final Intent myIntent = new Intent(this, MyMusicActivity.class);
    // loop to check if the song list has the correct songs
    for (int start = 0; start < SelectedSongsSingleton.getInstance().getSelectedSongs().size(); start++){
  	  Log.d("Edit ID3", "Current selected song number is :" + start);
  	  Log.d("Edit ID3", (SelectedSongsSingleton.getInstance().getSelectedSongs().get(start).getSongName()));
    }
  
    /* TODO: 1) create a method called setListeners and move these listeners to that method once
     *          the editors are working correctly
     *       2) create a method to compare artists name
     *       3) set listener for cancel button
     *       4) override "back" action */
    
    // edit song title field
    editSongTitleInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit artist field
    editArtistNameInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit album artist field
    editAlbumArtistInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit genre field
    editGenreInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit first composer field
    editComposerInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit first track field
    editTrackBeginningInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit second track field
    editTrackEndInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit first disc field
    editDiscBeginningInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit second disc field
    editDiscEndInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    // edit year
    editYearInputField.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        // call metadata editor method
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
          int after) {
        // save old metadata information
      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before,
          int count) {
      }
    });

    confirmButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        // save updates
        startActivity(myIntent);
      }
    });

    cancelButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
        // ignore updates; goes back to my music activity
        
        startActivity(myIntent);
      }
    });
  }

  private void setViewIds() {
    editSongTitleInputField = (EditText) findViewById(R.id.editSongTitleField);
    editArtistNameInputField = (EditText) findViewById(R.id.editArtistField);
    editAlbumArtistInputField = (EditText) findViewById(R.id.editAlbumArtistField);
    editGenreInputField = (EditText) findViewById(R.id.editGenreField);
    editComposerInputField = (EditText) findViewById(R.id.editComposerField);
    editTrackBeginningInputField = (EditText) findViewById(R.id.trackBeginningField);
    editTrackEndInputField = (EditText) findViewById(R.id.trackEndField);
    editDiscBeginningInputField = (EditText) findViewById(R.id.discBeginningField);
    editDiscEndInputField = (EditText) findViewById(R.id.discEndField);
    editYearInputField = (EditText) findViewById(R.id.yearInputField);
    confirmButton = (Button) findViewById(R.id.confirmButton);
    cancelButton = (Button) findViewById(R.id.cancelButton);
  }
  @Override
  public void onBackPressed() {
     onDestroy();
  }
}
