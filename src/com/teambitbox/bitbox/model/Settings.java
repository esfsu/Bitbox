/*
 * Settings
 * An enum class to provided consistent references to app settings
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

public enum Settings {
  SORT_TYPE("sortType"),
  SORT_ORDER("sortOrder"),
  MULTI_SELECT("selectMultiple"),
  SHOW_DETAILS("showDetailedList"),
  IS_SCANNED("isDeviceScanned");

  private Settings(final String name) {
      mName = name;
  }

  private final String mName;

  @Override
  public String toString() {
      return mName;
  }
}
