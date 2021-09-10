package com.studywithus.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Calendar implements Serializable {

  // 이달의 채용공고
  private List<Calendar> JobsCalendarList;

  // 이달의 시험일정
  private List<Calendar> ExamCalendarList;

  // 채용공고 or 시험일정 제목
  private String title;

  // 채용공고 or 시험일정 내용
  private String content;

  // 채용공고 시작일
  private String startDate;

  // 채용공고 종료일
  private String endDate;

  // 시험일정
  private String examDate;

  public List<Calendar> getJobsCalendarList() {
    return JobsCalendarList;
  }

  public void setJobsCalendarList(List<Calendar> jobsCalendarList) {
    JobsCalendarList = jobsCalendarList;
  }

  public List<Calendar> getExamCalendarList() {
    return ExamCalendarList;
  }

  public void setExamCalendarList(List<Calendar> examCalendarList) {
    ExamCalendarList = examCalendarList;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
