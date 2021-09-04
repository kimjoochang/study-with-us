package com.studywithus.menu;

import com.studywithus.handler.FreeStudyAddHandler;

public class FreeStudyAddMenu extends Menu {

  FreeStudyAddHandler freeStudyAddHandler;

  public FreeStudyAddMenu(FreeStudyAddHandler freeStudyAddHandler) {
    super("등록");
    this.freeStudyAddHandler = freeStudyAddHandler;
  }

  @Override 
  public void execute() {
    freeStudyAddHandler.execute(); 
  } 
}
