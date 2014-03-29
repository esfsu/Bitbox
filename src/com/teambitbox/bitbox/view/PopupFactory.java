package com.teambitbox.bitbox.view;

import android.app.Activity;
import android.content.Context;

public class PopupFactory {

	public static Popup createPopup(PopupType pt, Activity currentActivity,
			Context c) {
		switch (pt) {
		case SCANFORMUSIC:
			return new ScanForMusicPopup(currentActivity, c);
			/*
			 * case SCANFORMISSINGDATA: return new
			 * ScanForMissingDataPopup(context);
			 */
		case EDIT:
			return new EditPopup(currentActivity, c);
			/*
			 * case UNDO: return new UndoPopup(context);
			 */
		default:
			return null;
		}
	}
}
