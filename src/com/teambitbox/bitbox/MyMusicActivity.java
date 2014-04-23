package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.*;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


public class MyMusicActivity extends Activity implements OnQueryTextListener {

  final Context currentContext = this; // defines the context to be used in popups
  private BitboxApp singletonInitializer;
  
  private static final int RESULT_SETTINGS = 1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    singletonInitializer = (BitboxApp)getApplication();
    SelectedSongsSingleton.initInstance();
    SpannableString s = new SpannableString(" BITBOX");
    s.setSpan(new TypefaceSpan(this, "8-Bit-Wonder.ttf"), 0, s.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
 
    // Update the action bar title with the TypefaceSpan instance
    ActionBar actionBar = getActionBar();
    actionBar.setTitle(s);    
    updateView();
  }
  @Override
  public void onResume() {
	  super.onResume();
	  SelectedSongsSingleton.getInstance().getSelectedSongs().clear();
	  updateView();
      Log.e("DEBUG", "OnResume");
     
    
  }

  @Override
  public void onPause() {
	super.onPause();
    Log.e("DEBUG", "OnPause");
    
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    getMenuInflater().inflate(R.menu.my_music_screen_action_buttons, menu);
   
    MenuItem searchItem = menu.findItem(R.id.action_search); // search action button item
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    // Configure the search info and add any event listeners
    // Set up ShareActionProvider's default share intent
   
     
    //setupSearchView(searchView);
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
   // Handle presses on the action bar items
   switch (item.getItemId()) {
       case R.id.action_settings:
    	   Intent i = new Intent(this, SettingsActivity.class);
           startActivityForResult(i, RESULT_SETTINGS);
           return true;
       default:
           return super.onOptionsItemSelected(item);
   }
  }
 
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      switch (requestCode) {
      case RESULT_SETTINGS:
          updateView();
          break;

      }

  }
  
  public void updateView() {
      SharedPreferences sharedPrefs = PreferenceManager
              .getDefaultSharedPreferences(this);
      SelectedSongsSingleton.getInstance().mainScreenViewObjectTest = new MyMusicView(this, currentContext);
  }

   
  
  
  // this method sets the SearchView attributes
  private void setupSearchView(SearchView searchView) {
    searchView.setIconifiedByDefault(false);
    searchView.setOnQueryTextListener(this);
    //searchView.setSubmitButtonEnabled(true); 
    searchView.setQueryHint("Search Here");
  }
  
  // This method filters the ListView when the text is changed in the search field 
  // Right now it only returns the correct output if the ArrayAdapter is used with an ArrayList of String objects 
  public boolean onQueryTextChange(String searchedItem) {
    if (searchedItem.isEmpty()) {
    	Log.d("MainScreenActivity", "onQueryTextChange Empty");
    	SelectedSongsSingleton.getInstance().mainScreenViewObjectTest.getMainSongListView().getSongListWidget().clearTextFilter();
    } else {
    	Log.d("MainScreenActivity", "onQueryTextChange " + searchedItem.toString());
    	SelectedSongsSingleton.getInstance().mainScreenViewObjectTest.getMainSongListView().getSongListAdapter().getFilter().filter(searchedItem.toString());
    }
    return true;
  }
  
  // this method is use when the user submits the text in the search field
  public boolean onQueryTextSubmit(String query) {
    return false;
  }
}   

    

