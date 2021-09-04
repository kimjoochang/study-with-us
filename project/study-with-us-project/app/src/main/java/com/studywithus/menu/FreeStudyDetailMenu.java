package com.studywithus.menu;

import com.studywithus.handler.FreeStudyDetailHandler;

public class FreeStudyDetailMenu extends Menu {

  FreeStudyDetailHandler freeStudyDetailHandler;

  public FreeStudyDetailMenu(FreeStudyDetailHandler freeStudyDetailHandler) {
    super("등록");
    this.freeStudyDetailHandler = freeStudyDetailHandler;
  }

  @Override 
  public void execute() {
    freeStudyDetailHandler.execute(); 
  } 
}
