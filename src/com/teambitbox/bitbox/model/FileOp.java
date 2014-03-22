package com.teambitbox.bitbox;

public enum FileOp {
  RENAME("rename"),
  EDITID3("editId3"),
  MOVE("move"),
  UNDO("undo");
  
  private FileOp(final String name) {
      this.mName = name;
  }

  private final String mName;

  @Override
  public String toString() {
      return mName;
  }
}
