package com.studywithus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.NewMember;
import com.studywithus.handler.AuthLoginHandler;
import com.studywithus.handler.AuthLogoutHandler;
import com.studywithus.handler.InterestChargeAddHandler;
import com.studywithus.handler.InterestChargeDeleteHandler;
import com.studywithus.handler.InterestFreeAddHandler;
import com.studywithus.handler.InterestFreeDeleteHandler;
import com.studywithus.menu.Menu;
import com.studywithus.menu.MenuGroup;
import com.studywithus.util.Prompt;

public class App {
  List<NewMember> memberList = new LinkedList<>();
  List<FreeStudy> freeInterestList = new ArrayList<>();
  List<ChargeStudy> chargeInterestList = new ArrayList<>();

  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();
  InterestFreeAddHandler interestFreeAddHandler = new InterestFreeAddHandler(freeInterestList);
  InterestFreeDeleteHandler interestFreeDeleteHandler = new InterestFreeDeleteHandler(freeInterestList);
  InterestChargeAddHandler interestChargeAddHandler = new InterestChargeAddHandler(chargeInterestList);
  InterestChargeDeleteHandler interestChargeDeleteHandler = new InterestChargeDeleteHandler(chargeInterestList);

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

    MenuGroup interestMenu = new MenuGroup("관심목록");
    mainMenuGroup.add(interestMenu);

    MenuGroup freeInterestMenu = new MenuGroup("무료스터디 관심목록");
    interestMenu.add(freeInterestMenu);

    freeInterestMenu.add(new Menu("조회") {
      @Override
      public void execute() {
        interestFreeAddHandler.execute(); 
      }});

    freeInterestMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        interestFreeDeleteHandler.execute(); 
      }});

    MenuGroup chargeInterestMenu = new MenuGroup("유료스터디 관심목록");
    interestMenu.add(chargeInterestMenu);

    chargeInterestMenu.add(new Menu("조회") {
      @Override
      public void execute() {
        interestFreeAddHandler.execute(); 
      }});

    chargeInterestMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        interestFreeDeleteHandler.execute(); 
      }});


    return mainMenuGroup;
  }
}