package com.studywithus.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuGroup extends Menu {

  List<Menu> childs = new ArrayList<>();
  int size;
  boolean disablePrevMenu;
  String prevMenuTitle = "이전";

  private static class PrevMenu extends Menu {
    public PrevMenu() {
      super("");
    }
    @Override
    public void execute() {
    }
  }
  static PrevMenu prevMenu = new PrevMenu();

  public MenuGroup(String title) {
    super(title);
  }
  @Override
  public void execute() {
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  public Menu getMenu(String title) { 
    for (Menu menu : childs) {
      if (menu.title.equals(title)) {
        return menu;
      }
    }
    return null;
  }



}
