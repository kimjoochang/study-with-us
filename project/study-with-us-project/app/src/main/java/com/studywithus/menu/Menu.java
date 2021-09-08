package com.studywithus.menu;

public abstract class Menu {

  public static final int ACCESS_LOGOUT = 0x01;
  public static int ACCESS_GENERAL = 0x02;
  public static final int ACCESS_LEADER = 0x04;
  public static final int ACCESS_MENTOR = 0x08;
  public static final int ACCESS_ADMIN = 0x10;

  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_LOGOUT | ACCESS_GENERAL | ACCESS_LEADER | ACCESS_MENTOR | ACCESS_ADMIN);
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public abstract void execute();
}
