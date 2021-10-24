package com.studywithus.menu;

public abstract class Menu {

  public static final int ACCESS_LOGOUT = 1; // 비회원 (1)
  public static final int ACCESS_GENERAL = 2; // 회원  (2)
  public static final int ACCESS_MEMBER = 4; // 팀원   (4)
  public static final int ACCESS_LEADER = 8; // 팀장   (8)
  public static final int ACCESS_MENTOR = 16; // 멘토   (16)
  public static final int ACCESS_ADMIN = 32; // 관리자  (32)
  public static final int ACCESS_MENTEE = 64; // 멘티   (64)

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
