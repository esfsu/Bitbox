/*
 * BitboxApp Class
 *
 * Objects of this class are used to access and initialize Singletons
 * 
 * 
 * 09/04/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import android.app.Application;


public class BitboxApp extends Application {

	@Override
  public void onCreate(){
    super.onCreate();
     
    /* Initialize the singletons so their instances
     * are bound to the application process.*/
    initSingletons();
  }

	protected void initSingletons()
  {
    // Initialize the instance of SelectedSongsSingleton
    SelectedSongsSingleton.initInstance();
    
    // TO-DO: Initialize the instance of SettingsSingleton
  }
}
