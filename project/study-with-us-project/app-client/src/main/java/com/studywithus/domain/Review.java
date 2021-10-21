package com.studywithus.domain;

import java.sql.Date;

public class Review extends Content {
  private int studyNo;
  private double score;
  private Date registeredDate;

  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }
  public double getScore() {
    return score;
  }
  public void setScore(double score) {
    this.score = score;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
