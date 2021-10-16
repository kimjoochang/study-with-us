package com.studywithus.domain;

public class Comment extends Content{
  private int communityNo;
  private String email;

  public int getCommunityNo() {
    return communityNo;
  }
  public void setCommunityNo(int communityNo) {
    this.communityNo = communityNo;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
