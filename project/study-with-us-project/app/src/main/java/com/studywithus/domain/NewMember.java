package com.studywithus.domain;

public class NewMember {

  private static String adminId = "admin";
  private static String adminPwd = "admin";
  private String name;
  private String id;
  private String pwd;

  public static String getAdminId() {
    return adminId;
  }

  public static void setAdminId(String adminId) {
    NewMember.adminId = adminId;
  }

  public static String getAdminPwd() {
    return adminPwd;
  }

  public static void setAdminPwd(String adminPwd) {
    NewMember.adminPwd = adminPwd;
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

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
}
