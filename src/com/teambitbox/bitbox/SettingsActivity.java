package com.teambitbox.bitbox;

import com.teambitbox.bitbox.view.SelectedSongsSingleton;
import com.teambitbox.bitbox.view.TypefaceSpan;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.Spannable;
import android.text.SpannableString;

public class SettingsActivity extends PreferenceActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        SpannableString s = new SpannableString(" BITBOX");
        s.setSpan(new TypefaceSpan(this, "8-Bit-Wonder.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
     
        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(s);
        addPreferencesFromResource(R.xml.settings);
 
    }
}
