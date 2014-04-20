/*
 * EditID3Activity class
 *
 * Activity used to edit metadata of song files 
 * 
 * 
 * Eric Fernandez
 */

package com.teambitbox.bitbox;

import java.util.ArrayList;

import com.teambitbox.bitbox.view.BitboxApp;
import com.teambitbox.bitbox.view.SelectedSongsSingleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Id3;
import com.teambitbox.bitbox.model.Song;
import com.teambitbox.bitbox.model.Id3Data;
import com.teambitbox.bitbox.model.FileEditor;

public class EditID3Activity extends Activity {
  
  private EditText editSongTitleInputField;
  private EditText editArtistNameInputField;
  private EditText editAlbumArtistInputField;
  private EditText editGenreInputField;
  private EditText editComposerInputField;
  private EditText editTrackBeginningInputField;
  private EditText editDiscBeginningInputField;
  private EditText editYearInputField;
  private Button confirmButton;
  private Button cancelButton;
  protected BitboxApp singletonHolder;
  private FileEditor mEditor;
  private final String MULTIVAL = "<Multi>";
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.edit_id3_layout);
    setViewIds();
    singletonHolder = (BitboxApp)getApplication();
    mEditor = new FileEditor(singletonHolder.getBaseContext());
    // loop to check if the song list has the correct songs
    for (int start = 0; start < SelectedSongsSingleton.getInstance().getSelectedSongs().size(); start++){
  	  Log.d("Edit ID3", "Current selected song number is :" + start);
  	  Log.d("Edit ID3", (SelectedSongsSingleton.getInstance().getSelectedSongs().get(start).getSongName()));
    }
  
    /* TODO: 1) create a method called setListeners and move these listeners to that method once
     *          the editors are working correctly
     *       2) create a method to compare artists names */
    
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

    // Change the metadata!
    confirmButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
             
        // Create the Id3 data list
        ArrayList<Id3Data> id3List = new ArrayList<Id3Data>();
        Id3Data id3Val;
        if (!editSongTitleInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.SONG_NAME, editSongTitleInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editArtistNameInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.ARTIST, editArtistNameInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editAlbumArtistInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.ALBUM_ARTIST, editAlbumArtistInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editGenreInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.GENRE, editGenreInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editComposerInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.COMPOSER, editComposerInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editTrackBeginningInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.TRACK_NUM, editTrackBeginningInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editDiscBeginningInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.DISC_NUM, editDiscBeginningInputField.getText().toString());
          id3List.add(id3Val);
        }
        if (!editYearInputField.getText().toString().equalsIgnoreCase(MULTIVAL)) {
          id3Val = new Id3Data(Id3.YEAR, editYearInputField.getText().toString());
          id3List.add(id3Val);
        }
        
        Log.d("Edit", "Starting");
        
        // Do the dirty work
        mEditor.editId3Data(SelectedSongsSingleton.getInstance().getSelectedSongs(), id3List);
        
        Log.d("Edit", "Complete");
        
        // Return from whence we came
        finish();
      }
    });

    cancelButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
        // ignore updates; goes back to my music activity
        
        finish();
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
    editDiscBeginningInputField = (EditText) findViewById(R.id.discBeginningField);
    editYearInputField = (EditText) findViewById(R.id.yearInputField);
    confirmButton = (Button) findViewById(R.id.confirmButton);
    cancelButton = (Button) findViewById(R.id.cancelButton);
    
    // Set text if it's the same for all fields
    if (SelectedSongsSingleton.getInstance().getSelectedSongs().size() > 1)
    {
      // set initial comparison string
      String songName = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getSongName();
      String artist = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getArtist();
      String albumArtist = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getAlbumArtist();
      String genre = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getGenre();
      String composer = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getComposer();
      String trackNum = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getTrackNum();
      String discNum = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getDiscNum();
      String year = SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getYear();
      
      // compare against all other songs
      // if same, keep value, if different, notify user 
      for (Song song : SelectedSongsSingleton.getInstance().getSelectedSongs())
      {
        songName = (songName == song.getSongName()) ? song.getSongName() : MULTIVAL;
        artist = (artist == song.getArtist()) ? song.getArtist() : MULTIVAL;
        albumArtist = (albumArtist == song.getAlbumArtist()) ? song.getAlbumArtist() : MULTIVAL;
        genre = (genre == song.getGenre()) ? song.getGenre() : MULTIVAL;
        composer = (composer == song.getComposer()) ? song.getComposer() : MULTIVAL;
        trackNum = (trackNum == song.getTrackNum()) ? song.getTrackNum() : MULTIVAL;
        discNum = (discNum == song.getDiscNum()) ? song.getDiscNum() : MULTIVAL;
        year = (year == song.getYear()) ? song.getYear() : MULTIVAL;
      }
      
      editSongTitleInputField.setText(songName);
      editArtistNameInputField.setText(artist);
      editAlbumArtistInputField.setText(albumArtist);
      editGenreInputField.setText(genre);
      editComposerInputField.setText(composer);
      editTrackBeginningInputField.setText(trackNum);
      editDiscBeginningInputField.setText(discNum);
      editYearInputField.setText(year);
    }
    else
    {
      editSongTitleInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getSongName());
      editArtistNameInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getArtist());
      editAlbumArtistInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getAlbumArtist());
      editGenreInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getGenre());
      editComposerInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getComposer());
      editTrackBeginningInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getTrackNum());
      editDiscBeginningInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getDiscNum());
      editYearInputField.setText(SelectedSongsSingleton.getInstance().getSelectedSongs().get(0).getYear());
    }
  }
  
  @Override
  public void onBackPressed() {
	  SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
      // ignore updates; goes back to my music activity
      
      finish();
  }
  
}
