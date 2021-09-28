package com.studywithus.domain;

import java.sql.Date;

public class Schedule extends Content {
  private Date startDate; // 시험일 && 시작일
  private Date endDate; // 종료일

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
