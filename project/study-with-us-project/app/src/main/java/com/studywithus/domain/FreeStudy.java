package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class FreeStudy implements Serializable {

  private int no;
  private String title;
  private Member writer;
  private int onOffLine;
  private String area;
  private String explanation;
  private String rule;
  private Date registeredDate;
  private int like;
  private int viewCount;
  private List<FreeStudy> freeInterest;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public int getOnOffLine() {
    return onOffLine;
  }

  public void setOnOffLine(int onOffLine) {
    this.onOffLine = onOffLine;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public String getRule() {
    return rule;
  }

  public void setRule(String rule) {
    this.rule = rule;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public List<FreeStudy> getFreeInterest() {
    return freeInterest;
  }

  public void setFreeInterest(List<FreeStudy> freeInterest) {
    this.freeInterest = freeInterest;
  }
}
