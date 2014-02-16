package com.teambitbox.bitbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        
        filelist = (ListView) findViewById(R.id.listView); // initializes ListView
       
        
        filelist.setAdapter(new SongListAdapter()); // sets adapter for ListView
		
        
		filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
	      @Override
	      public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) 
	      {
	    	
			}        
	      
	    });

    }
    
    // extends ArrayAdapter Class; this adapter is used to create the Text Views for each list item
    class SongListAdapter extends ArrayAdapter<String> {
		    SongListAdapter() {
		      super(MainScreenActivity.this, R.layout.rowlayout, filenames);
		    }
		    
		    public View getView(int position, View convertView,
		                        ViewGroup parent) {
		      View row=convertView;
		      
		      if (row == null) {                          
		        LayoutInflater inflater=getLayoutInflater();
		        
		        row=inflater.inflate(R.layout.rowlayout, parent, false);
		      }
		      
		      // initializes textView
		      TextView label = (TextView)row.findViewById(R.id.fileNameLabel);
		      
		      // uses filenames array to set the text for each list item depending on position
		      label.setText(filenames[position]);
		      
		      return(row);

    }
    
    
    
}
}   

    

