package com.studywithus.domain;

import java.sql.Date;

public class MentorApplication {
  private int no;
  private Member applicant;
  private String selfIntroduction; 
  private String subject; 
  private Date registeredDate; 
  private int status; // 0 = 미정, 1 = 승인, 2 = 거절
  private String remarks;

  public Member getApplicant() {
    return applicant;
  }

  public void setApplicant(Member applicant) {
    this.applicant = applicant;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getSelfIntroduction() {
    return selfIntroduction;
  }

  public void setSelfIntroduction(String selfIntroduction) {
    this.selfIntroduction = selfIntroduction;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
