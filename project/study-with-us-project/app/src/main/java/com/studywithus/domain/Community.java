package com.studywithus.domain;

import java.sql.Date;

@SuppressWarnings("serial")
public class Community extends Content {

  // 게시글 작성자
  private Member writer;

  // 게시글 등록일
  private Date registeredDate;

  // 조회수
  private int viewCount;

  // 좋아요
  private int like;

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
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
