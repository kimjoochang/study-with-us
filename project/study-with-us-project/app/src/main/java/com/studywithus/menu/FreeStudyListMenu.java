package com.studywithus.menu;

import com.studywithus.handler.FreeStudyListHandler;

public class FreeStudyListMenu extends Menu {

  FreeStudyListHandler freeStudyListHandler;

  public FreeStudyListMenu(FreeStudyListHandler freeStudyListHandler) {
    super("등록");
    this.freeStudyListHandler = freeStudyListHandler;
  }

  @Override 
  public void execute() {
    freeStudyListHandler.execute(); 
  } 
}
