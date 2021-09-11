package com.studywithus.domain;

//커뮤니티 게시글 구성요소
@SuppressWarnings("serial")
public class Community extends Content {

  private int viewCount; // 조회수
  private int like; // 좋아요

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
