package com.studywithus.domain;

import java.io.Serializable;

//커뮤니티 게시글 구성요소
@SuppressWarnings("serial")
public class Community extends Board implements Serializable {

  private int viewCount; // 조회수
  private int like; // 좋아요

  //이게 모람.................
  @Override
  public String toString() {
    try {
      return "Community [no=" + Board.getNo() + ", title=" + Board.getTitle() 
      + ", content=" + Board.getContent() + ", writer=" + Board.getWriter()
      + ", registeredDate=" + Board.getRegisteredDate() + ", viewCount=" + viewCount 
      + ", like=" + like + "]";
    } catch (Exception e) {
      e.printStackTrace();
    }
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
}
