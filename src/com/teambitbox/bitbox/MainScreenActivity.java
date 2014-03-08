package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;


public class MainScreenActivity extends Activity {

  final Context currentContext = this; // defines the context to be used in popups
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MainScreenView mainScreenViewObjectTest = new MainScreenView(this, currentContext);
  }
  
}   

    

