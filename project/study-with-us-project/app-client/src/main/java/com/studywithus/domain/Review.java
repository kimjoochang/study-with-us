package com.studywithus.domain;

import java.sql.Date;

public class Review extends Content {

  private String writerEmail;
  private String review;
  private int score;
  private Date registeredDate;
  private int studyNo;

  public String getWriterEmail() {
    return writerEmail;
  }

  public void setWriterEmail(String writerEmail) {
    this.writerEmail = writerEmail;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getStudyNo() {
    return studyNo;
  }

  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }

}
