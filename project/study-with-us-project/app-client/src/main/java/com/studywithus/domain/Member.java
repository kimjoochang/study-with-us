package com.studywithus.domain;

import java.sql.Date;
import java.util.List;

public class Member {
  private int userAccessLevel; // 권한
  private int no; // 회원 번호
  private int status; // 회원 번호
  private String name; // 회원 이름
  private String email; // 회원 이메일
  private String password; // 회원 비밀번호
  private String photo; // 회원 첨부파일
  private String phoneNumber; // 회원 휴대폰 번호
  private Date registeredDate; // 회원 가입일
  private Date lastDate; // 회원 가입일
  private List<Study> freeInterest; // 무료 스터디 관심목록
  private List<Study> chargeInterest; // 유료 스터디 관심목록
  private List<Payment> payment; // 유료 스터디 결제목록
  private boolean mentor; // 멘토 여부

  @Override
  public String toString() {
    return "Member [userAccessLevel=" + userAccessLevel + ", no=" + no + ", name=" + name
        + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
        + ", registeredDate=" + registeredDate + ", freeInterest=" + freeInterest
        + ", chargeInterest=" + chargeInterest + ", payment=" + payment + ", mentor=" + mentor
        + "]";
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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
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

  public List<Payment> getPayment() {
    return payment;
  }

  public void setPayment(List<Payment> payment) {
    this.payment = payment;
  }

  public boolean isMentor() {
    return mentor;
  }

  public void setMentor(boolean mentor) {
    this.mentor = mentor;
  }
}
