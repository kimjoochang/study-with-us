package com.studywithus.domain;

public class Calendar extends Content {
  private static final long serialVersionUID = 1L;

  private String startDate; // 채용공고 시작일
  private String endDate; // 채용공고 종료일
  private String examDate; // 시험일

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getExamDate() {
    return examDate;
  }

  public void setExamDate(String examDate) {
    this.examDate = examDate;
  }
}
