package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SongListView {
  // array of string for file names example
  private String[] filenames = new String[] { "Android", "iPhone", "WindowsMobile",
	            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	            "Android", "iPhone", "WindowsMobile" };
  
  private ListView filelist;
  private Activity currentActivity;
  private Context currentContext;
 
  public SongListView(Activity currentActivity, Context currentContext){
	  setContext(currentContext);
	  setActivity(currentActivity);
	  filelist = (ListView) currentActivity.findViewById(R.id.listView); // initializes ListView
    filelist.setAdapter(new SongListAdapter(this)); // sets adapter for ListView
    
    filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
	    
      // TODO: Selection of List Item Event  
	    	
	    }        
    });
    
  }
 
  private void setActivity(Activity a){
		this.currentActivity = a;	
	}

	public Activity getActivity(){
		return currentActivity;
	}
	
	private void setContext(Context c){
		this.currentContext = c;	
	}

	public Context getContext() {
		return currentContext;
	}
	
	public String[] getFilenames() {
		return filenames;
  }

	private void setFilenames(String[] filenames) {
		this.filenames = filenames;
	}
	
	public String getElement(int position){
		return this.filenames[position];
	}
	
}
