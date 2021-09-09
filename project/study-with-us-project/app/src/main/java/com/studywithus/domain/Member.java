package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Member implements Serializable {

  private static String adminId = "admin";
  private static String adminPassword = "admin";
  private int no;
  private String name;
  private String id;
  private String password;
  private Date registeredDate;

  @Override
  public String toString() {
    return "[팀장 / 멘토 = " + name + "]";
  }

  //  @Override
  //  public String toString() {
  //    return "Member [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + "]";
  //  }

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

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
