package com.studywithus.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.util.Prompt;

public class MenuGroup extends Menu {

  static Stack<Menu> breadCrumb = new Stack<>();
  ArrayList<Menu> childs = new ArrayList<>();

  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";

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

  public MenuGroup(String title, int accessScope) {
    super(title, accessScope);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public MenuGroup(String title, boolean disablePrevMenu, int accessScope) {
    super(title, accessScope);
    this.disablePrevMenu = disablePrevMenu;
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  public void add(Menu child) {
    childs.add(child);
  }

  public void remove(Menu child) {
    childs.remove(child);
  }

  public Menu getMenu(String title) { 
    for (Menu menu : childs) {
      if (menu.title.equals(title)) {
        return menu;
      }
    }
    return null;
  }

  @Override 
  public void execute() {

    breadCrumb.push(this);

    while (true) {
      List<Menu> menuList = getMenuList();
      printBreadCrumbMenuTitle();
      printMenuList(menuList);

      try {
        Menu menu = selectMenu(menuList);

        if (menu == null) {
          System.out.println("무효한 메뉴 번호입니다.");
          continue;
        }

        if (menu instanceof PrevMenu) {
          breadCrumb.pop();
          return;
        }

        menu.execute();

      } catch (Exception e) {
        System.out.println("--------------------------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        e.printStackTrace();
        System.out.println("--------------------------------------------------------------");
      }
    }
  }

  private String getBreadCrumb() {
    String path = "";

    for (int i = 0; i < breadCrumb.size(); i++) {
      if (path.length() > 0) {
        path += " / ";
      }
      Menu menu = breadCrumb.get(i); 
      path += menu.title;
    }

    return path;
  }

  private List<Menu> getMenuList() {
    ArrayList<Menu> menuList = new ArrayList<>();
    for (Menu menu : childs) {
      if ((menu.accessScope & AuthLoginHandler.getUserAccessLevel()) > 0 ) {
        menuList.add(menu);
      }
    }
    return menuList;
  }

  private void printBreadCrumbMenuTitle() {
    System.out.printf("\n[%s]\n", getBreadCrumb());
  }

  private void printMenuList(List<Menu> menuList) {
    int i = 1;

    for (Menu menu : menuList) {
      System.out.printf("%d. %-20s\n", i++, menu.title);
    }

    if (!disablePrevMenu) {
      System.out.printf("0. %s\n", this.prevMenuTitle);
    }
  }

  private Menu selectMenu(List<Menu> menuList) {
    System.out.println(" ");
    int menuNo = Prompt.inputInt("선택 > ");
    System.out.println(" ");

    if (menuNo < 0 || menuNo > menuList.size()) {
      return null;
    }

    if (menuNo == 0 && !disablePrevMenu) {
      return prevMenu; // 호출한 쪽에 '이전 메뉴' 선택을 알리게 위해 
    } 

    return menuList.get(menuNo - 1);
  }
}
