package com.studywithus.menu;

import com.studywithus.handler.FreeStudyUpdateHandler;

public class FreeStudyUpdateMenu extends Menu {

  FreeStudyUpdateHandler freeStudyUpdateHandler;

  public FreeStudyUpdateMenu(FreeStudyUpdateHandler freeStudyUpdateHandler) {
    super("등록");
    this.freeStudyUpdateHandler = freeStudyUpdateHandler;
  }

  @Override 
  public void execute() {
    freeStudyUpdateHandler.execute(); 
  } 
}
