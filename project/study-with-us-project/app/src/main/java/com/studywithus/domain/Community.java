package com.studywithus.domain;

import java.sql.Date;

public class Community extends Content {
  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요
  private int myPostNo; // // 나의 게시글 번호

  //private List<Member> members;//회원별 게시글 조회용 리스트


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

  public int getMyPostNo() {
    return myPostNo;
  }

  public void setMyPostNo(int myPostNo) {
    this.myPostNo = myPostNo;
  }

  //	public List<Member> getMembers() {
  //		return members;
  //	}
  //
  //	public void setMembers(List<Member> members) {
  //		this.members = members;
  //	}
}

