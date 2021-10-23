package com.studywithus.domain;

import java.sql.Date;

public class Payment {

  private int payNo;
  private int memberNo;
  private Member mentee;
  private int studyNo; // 결제한 유료 스터디 게시글 번호
  private String paymentMethod; // 결제 수단
  private Date paymentDate; // 결제일
  private Study study;

  // 기본 = 0
  // 무료스터디 참여상태 (유료 - 결제완료) = 1 
  // 무료스터디 탈퇴상태 (유료 - 결제취소)= 2
  private int status; 

  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }
  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
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
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public int getPayNo() {
    return payNo;
  }
  public void setPayNo(int payNo) {
    this.payNo = payNo;
  }
  public Study getStudy() {
    return study;
  }
  public void setStudy(Study study) {
    this.study = study;
  }
  public Member getMentee() {
    return mentee;
  }
  public void setMentee(Member mentee) {
    this.mentee = mentee;
  }
}
