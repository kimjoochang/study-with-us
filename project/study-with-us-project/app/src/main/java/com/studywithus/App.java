package com.studywithus;

import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class App {
  List<NewMember> memberList = new LinkedList<>();

  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }
  
  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authLoginHandler.execute(); 
      }
    });

    mainMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        authLogoutHandler.execute(); 
      }
    });

    MenuGroup freeStudyMenu = new MenuGroup("무료 스터디");
    mainMenuGroup.add(freeStudyMenu);

    freeStudyMenu.add(new Menu("생성") {
      @Override 
      public void execute() {
      freeStudyAddHandler.execute(); 
    }});

    freeStudyMenu.add(new Menu("조회") {
      @Override 
      public void execute() {
      freeStudyListHandler.execute(); 
    }});

    freeStudyMenu.add(new Menu("상세보기") {
      @Override 
      public void execute() {
      freeStudyDetailHandler.execute(); 
    }});

    freeStudyMenu.add(new Menu("수정") {
      @Override 
      public void execute() {
      freeStudyUpdateHandler.execute(); 
    }});

    freeStudyMenu.add(new Menu("삭제") {
      @Override 
      public void execute() {
      freeStudyDeleteHandler.execute(); 
    }});

    return mainMenuGroup;
  }
}