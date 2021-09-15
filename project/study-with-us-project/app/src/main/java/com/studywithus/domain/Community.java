package com.studywithus.domain;

import java.sql.Date;

public class Community extends Content {

  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요

  @Override
  public String toCsvString() {
    return String.format("%s,%d,%d",
        this.getRegisteredDate(),
        this.getViewCount(),
        this.getLike());
  }

  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    this.setRegisteredDate(Date.valueOf(values[0]));
    this.setViewCount(Integer.valueOf(values[1]));
    this.setLike(Integer.valueOf(values[2]));
  }

  public static Community valueOfCsv(String csv) {

    String[] values = csv.split(",");

    Community cmn = new Community();
    cmn.setRegisteredDate(Date.valueOf(values[0]));
    cmn.setViewCount(Integer.valueOf(values[1]));
    cmn.setLike(Integer.valueOf(values[2]));

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

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

}
