package com.studywithus.domain;

import java.sql.Date;

public class DeleteRequestForm {
  private int no;
  private int studyNo;
  private String reason;
  private String remarks;
  private Date registeredDate;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
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
