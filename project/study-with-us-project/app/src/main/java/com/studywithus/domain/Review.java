package com.studywithus.domain;

import java.sql.Date;

public class Review extends Content {

  private String id;
  private String review;
  private int score;
  private Date registeredDate;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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
}
