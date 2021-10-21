package com.studywithus.domain;

import java.sql.Date;

public class Member {
  private int no; // 회원 번호
  private String name; // 회원 이름
  private String email; // 회원 이메일
  private String password; // 회원 비밀번호
  private String phoneNumber; // 회원 휴대폰 번호
  private Date registeredDate; // 회원 가입일
  private int status; // 회원 상태
  private Date lastDate; // 회원 마지막 접속일
  private int userAccessLevel; // 권한
  private AttachmentFile memberFile;

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
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public Date getLastDate() {
    return lastDate;
  }
  public void setLastDate(Date lastDate) {
    this.lastDate = lastDate;
  }
  public int getUserAccessLevel() {
    return userAccessLevel;
  }
  public void setUserAccessLevel(int userAccessLevel) {
    this.userAccessLevel = userAccessLevel;
  }
  public AttachmentFile getMemberFile() {
    return memberFile;
  }
  public void setMemberFile(AttachmentFile memberFile) {
    this.memberFile = memberFile;
  }

}
