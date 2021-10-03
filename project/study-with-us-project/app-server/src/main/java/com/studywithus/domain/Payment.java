package com.studywithus.domain;

import java.sql.Date;

public class Payment {

  private int paidStudyNo; // 결제한 유료 스터디 게시글 번호
  private String paymentMethod; // 결제 수단
  private Date paymentDate; // 결제일
  // [10.03 추가]
  private String title; // 결제한 유료 스터디 제목
  private String mentorName; // 결제한 유료 스터디 멘토
  private int price; // 결제한 가격

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMentorName() {
    return mentorName;
  }

  public void setMentorName(String mentorName) {
    this.mentorName = mentorName;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPaidStudyNo() {
    return paidStudyNo;
  }

  public void setPaidStudyNo(int paidStudyNo) {
    this.paidStudyNo = paidStudyNo;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }
}
