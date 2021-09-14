package com.studywithus.domain;

import java.sql.Date;

public class Community extends Content {
  private static final long serialVersionUID = 1L;

  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요

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
