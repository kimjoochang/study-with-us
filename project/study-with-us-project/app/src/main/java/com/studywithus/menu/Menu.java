package com.studywithus.menu;

public abstract class Menu {

  public static final int ACCESS_LOGOUT = 0x01; // 비회원
  public static final int ACCESS_GENERAL = 0x02; // 회원
  public static final int ACCESS_MEMBER = 0x04; // 팀원
  public static int ACCESS_LEADER = 0x08; // 팀장
  public static final int ACCESS_MENTOR = 0x10; // 멘토
  public static final int ACCESS_ADMIN = 0x20; // 관리자

  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_LOGOUT | ACCESS_GENERAL | ACCESS_MEMBER | ACCESS_LEADER | ACCESS_MENTOR | ACCESS_ADMIN);
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public abstract void execute();
}
