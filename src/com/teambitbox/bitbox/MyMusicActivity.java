package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.*;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


public class MyMusicActivity extends Activity /*implements OnQueryTextListener*/ {

  final Context currentContext = this; // defines the context to be used in popups
  private MyMusicView mainScreenViewObjectTest; // 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainScreenViewObjectTest = new MyMusicView(this, currentContext);

  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    getMenuInflater().inflate(R.menu.my_music_screen_action_buttons, menu);
   
    MenuItem searchItem = menu.findItem(R.id.action_search); // search action button item
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    // Configure the search info and add any event listeners
       
    //setupSearchView(searchView);
    return super.onCreateOptionsMenu(menu);
  }
  
 /* This is an experiment with the action bar. The methods below are used
  * to add the actions to the event listeners. OnQuery methods are used
  * for the Search functionality
  
  @Override
     public boolean onOptionsItemSelected(MenuItem item) {
      // Handle presses on the action bar items
      switch (item.getItemId()) {
          case R.id.action_search:
              //openSearch();
              return true;
          case R.id.action_settings:
              //openSettings();
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }
  
  // this method sets the SearchView attributes
  private void setupSearchView(SearchView searchView) {
    searchView.setIconifiedByDefault(false);
    searchView.setOnQueryTextListener(this); // uncomment the implementation of OnQueryTextListener for this to work
    //searchView.setSubmitButtonEnabled(true); 
    searchView.setQueryHint("Search Here");
  }
  
  // This method filters the ListView when the text is changed in the search field 
  // Right now it only returns the correct view if the ArrayAdapter is used with an ArrayList of String objects 
  public boolean onQueryTextChange(String searchedItem) {
    if (searchedItem.isEmpty()) {
    	Log.d("MainScreenActivity", "onQueryTextChange Empty");
    	mainScreenViewObjectTest.getMainSongListView().getSongListWidget().clearTextFilter();
    } else {
    	Log.d("MainScreenActivity", "onQueryTextChange " + searchedItem.toString());
    	// This filters the listView and shows only the searchedItem however it is not working with the Song ArrayList
    	mainScreenViewObjectTest.getMainSongListView().getSongListAdapter().getFilter().filter(searchedItem.toString());
    }
    return true;
  }
  
  // this method is use when the user submits the text in the search field
  public boolean onQueryTextSubmit(String query) {
    return false;
  }
  */
}   

    

