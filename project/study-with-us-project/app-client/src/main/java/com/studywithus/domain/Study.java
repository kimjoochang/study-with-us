package com.studywithus.domain;

import java.sql.Date;

public class Study extends Content {
  private String area; // 지역
  private int onOffLine; // 오프라인 = 0 , 온라인 = 1 
  private Date registeredDate; // 스터디 등록일
  private int viewCount; // 조회수
  private int maxMembers; // 스터디 최대 모집인원 수
  private Date startDate; // 스터디 시작일
  private Date endDate; // 스터디 종료일
  private int studyStatus; // 모집중 = 0, 진행중 = 1, 진행완료 = 2
  private int likes;
  // 기본값 = 0, 
  // 삭제요청(유료) = 1 
  // 삭제(무료)/삭제요청 승인(가격이 0 이상) = 2
  private int deleteStatus;
  private int price; // 유료 스터디 가격

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public int getOnOffLine() {
    return onOffLine;
  }

  public void setOnOffLine(int onOffLine) {
    this.onOffLine = onOffLine;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getMaxMembers() {
    return maxMembers;
  }

  public void setMaxMembers(int maxMembers) {
    this.maxMembers = maxMembers;
  }

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

  public int getStudyStatus() {
    return studyStatus;
  }

  public void setStudyStatus(int studyStatus) {
    this.studyStatus = studyStatus;
  }

  public int getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(int deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}
