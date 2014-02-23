package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.Popup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;


public class MainScreenActivity extends Activity {

  private ListView filelist;
	
  // string for file names
  private String[] filenames = new String[] { "Android", "iPhone", "WindowsMobile",
	            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	            "Android", "iPhone", "WindowsMobile" };
  private ImageView scanForMusicOptionButton;
  private ImageView scanForMissingDataOptionButton;
  private ImageView editOptionButton;
  private ImageView undoOptionButton;
  final Context context = this; // defines the context to be used in popups
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_screen);
        
    filelist = (ListView) findViewById(R.id.listView); // initializes ListView
       
    filelist.setAdapter(new SongListAdapter()); // sets adapter for ListView
		
    filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	  @Override
	  public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
	    
      // TODO: Selection of List Item Event  
	    	
	  }        
    });

    // set ID and Listeners for each button
    scanForMusicOptionButton.setImageResource(R.id.scanDirectoryButton);
    scanForMusicOptionButton.setOnClickListener(new OnClickListener() { 
      @Override
      public void onClick(View arg0) {
        Popup dialog = new Popup();
        dialog.createPopup(1, context);
      }
    });
    
    scanForMissingDataOptionButton.setImageResource(R.id.scanForMissingDataButton);
    scanForMissingDataOptionButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        Popup dialog = new Popup();
        dialog.createPopup(2, context);
      }
    });
    
    editOptionButton.setImageResource(R.id.editButton);
    editOptionButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        Popup dialog = new Popup();
        dialog.createPopup(3, context);
      }
    });
    
    undoOptionButton.setImageResource(R.id.undoButton);
    undoOptionButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View arg0) {
        Popup dialog = new Popup();
        dialog.createPopup(4, context);
      }
    });
  }
    
  // extends ArrayAdapter Class; this adapter is used to create the Text Views for each list item
  class SongListAdapter extends ArrayAdapter<String> {
    
	// Adapter Constructor
	SongListAdapter() {
	  super(MainScreenActivity.this, R.layout.row_layout, filenames);
	}
		    
    @Override
	public View getView(int position, View convertView,ViewGroup parent) {
	  View row=convertView;
	  
	  if (row == null) {                          
		LayoutInflater inflater=getLayoutInflater();
		row=inflater.inflate(R.layout.row_layout, parent, false);
	  }
		      
	  // initializes textView
	  TextView label = (TextView)row.findViewById(R.id.fileNameLabel);
	  
	  // uses filenames array to set the text for each list item depending on position
	  label.setText(filenames[position]);
	
	  return(row);
    } 
  }
  
  
}   

    

