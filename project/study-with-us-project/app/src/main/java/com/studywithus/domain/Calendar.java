package com.studywithus.domain;

@SuppressWarnings("serial")
public class Calendar extends Content {

  // 캘린더 종류
  private String kind;

  // 채용공고 시작일
  private String startDate;

  // 채용공고 종료일
  private String endDate;

  // 시험일
  private String examDate;

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

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
