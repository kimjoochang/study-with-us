package com.studywithus.menu;

import com.studywithus.handler.FreeStudyDeleteHandler;

public class FreeStudyDeleteMenu extends Menu {

  FreeStudyDeleteHandler freeStudyDeleteHandler;

  public FreeStudyDeleteMenu(FreeStudyDeleteHandler freeStudyDeleteHandler) {
    super("등록");
    this.freeStudyDeleteHandler = freeStudyDeleteHandler;
  }

  @Override 
  public void execute() {
    freeStudyDeleteHandler.execute(); 
  } 
}
