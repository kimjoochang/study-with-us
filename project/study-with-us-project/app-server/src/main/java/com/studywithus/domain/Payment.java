package com.studywithus.domain;

import java.sql.Date;

public class Payment {
  private int studyNo;
  private Member mentee;
  private int paymentMethod; // 결제 수단
  private Date paymentDate; // 결제일

  // 기본 = 0
  // 무료스터디 참여상태 (유료 - 결제완료) = 1 
  // 무료스터디 탈퇴상태 (유료 - 결제취소)= 2
  private int status; 

  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }
  public int getPaymentMethod() {
    return paymentMethod;
  }
  public void setPaymentMethod(int paymentMethod) {
    this.paymentMethod = paymentMethod;
  }
  public Date getPaymentDate() {
    return paymentDate;
  }
  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public Member getMentee() {
    return mentee;
  }
  public void setMentee(Member mentee) {
    this.mentee = mentee;
  }
}
