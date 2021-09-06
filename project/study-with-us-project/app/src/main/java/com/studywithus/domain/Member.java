package com.studywithus.domain;

public class Member {

  private static String adminId = "admin";
  private static String adminPassword = "admin";
  private String name;
  private String id;
  private String password;

  public static String getAdminId() {
    return adminId;
  }

  public static void setAdminId(String adminId) {
    Member.adminId = adminId;
  }

  public static String getAdminPassword() {
    return adminPassword;
  }

  public static void setAdminPassword(String adminPassword) {
    Member.adminPassword = adminPassword;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
