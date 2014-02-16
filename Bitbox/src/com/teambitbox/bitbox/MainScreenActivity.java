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
    String[] filenames = new String[] { "Android", "iPhone", "WindowsMobile",
	            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	            "Android", "iPhone", "WindowsMobile" };
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        
        filelist = (ListView) findViewById(R.id.listView);
       
        
        filelist.setAdapter(new IconicAdapter());
		
		filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
	      @Override
	      public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) 
	      {
	    	
			}        
	      
	    });

    }
    
    class IconicAdapter extends ArrayAdapter<String> {
		    IconicAdapter() {
		      super(MainScreenActivity.this, R.layout.rowlayout, filenames);
		    }
		    
		    public View getView(int position, View convertView,
		                        ViewGroup parent) {
		      View row=convertView;
		      
		      if (row==null) {                          
		        LayoutInflater inflater=getLayoutInflater();
		        
		        row=inflater.inflate(R.layout.rowlayout, parent, false);
		      }
		      
		      TextView label=(TextView)row.findViewById(R.id.label);
		      label.setText(filenames[position]);

		      
		      
		      return(row);

    }
    
    
    
}
}   

    

