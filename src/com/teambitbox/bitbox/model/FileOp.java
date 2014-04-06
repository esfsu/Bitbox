/*
 * FileOp
 * An enum class to provided consistent references file operations.
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

public enum FileOp {
  RENAME("rename"),
  EDITID3("editId3"),
  MOVE("move"),
  UNDO("undo");
  
  private FileOp(final String name) {
      mName = name;
  }

  private final String mName;

  @Override
  public String toString() {
      return mName;
  }
}
