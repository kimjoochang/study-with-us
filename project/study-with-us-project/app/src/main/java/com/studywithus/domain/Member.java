package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Member implements Serializable {

  private static String adminId = "admin";
  private static String adminPassword = "admin";
  private int userAccessLevel; // 권한
  private String name; // 회원 이름
  private String email; // 회원 이메일
  private String id; // 회원 아이디
  private String password; // 회원 비밀번호
  private String phoneNumber; // 회원 핸드폰번호
  private Date registeredDate; // 회원 가입일

  // 무료 스터디 관심목록
  private List<Study> freeInterest;

  // 유료 스터디 관심목록
  private List<Study> chargeInterest;

  // [삭제해도 될듯]
  //  public static final int ACCESS_GENERAL = 0x02; // 회원
  //  public static final int ACCESS_MEMBER= 0x04; // 팀원
  //  public static int ACCESS_LEADER = 0x08; // 팀장
  //  public static final int ACCESS_MENTOR = 0x010; // 멘토
  //  public static final int ACCESS_ADMIN = 0x20; // 관리자

  public int getUserAccessLevel() {
    return userAccessLevel;
  }

  public void setUserAccessLevel(int userAccessLevel) {
    this.userAccessLevel = userAccessLevel;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }


  public List<Study> getFreeInterest() {
    return freeInterest;
  }

  public void setFreeInterest(List<Study> freeInterest) {
    this.freeInterest = freeInterest;
  }

  public List<Study> getChargeInterest() {
    return chargeInterest;
  }

  public void setChargeInterest(List<Study> chargeInterest) {
    this.chargeInterest = chargeInterest;
  }
}
