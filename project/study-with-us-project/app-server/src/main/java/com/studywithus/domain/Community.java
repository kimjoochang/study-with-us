package com.studywithus.domain;

import java.sql.Date;

public class Community extends Content {
  public final String SMALLTALK_COMMUNITY = "스몰톡";
  public final String INFO_COMMUNITY = "정보";
  public final String QA_COMMUNITY = "질문";


  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요
  private int category; // 정보, 질문, 스몰톡
  private AttachmentFile communityFile;

  public AttachmentFile getCommunityFile() {
    return communityFile;
  }

  public void setCommunityFile(AttachmentFile communityFile) {
    this.communityFile = communityFile;
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

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

}
