package com.studywithus.domain;

import java.sql.Date;

public class DeleteRequestForm {
  private int no;
  private Study study;
  private String reason;
  private String remarks;
  private Date registeredDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public Study getStudy() {
    return study;
  }
  public void setStudy(Study study) {
    this.study = study;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public String getRemarks() {
    return remarks;
  }
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
