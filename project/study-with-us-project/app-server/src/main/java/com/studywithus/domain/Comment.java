package com.studywithus.domain;

import java.sql.Date;

public class Comment extends Content{
  private int communityNo;
  private Date registeredDate;

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getCommunityNo() {
    return communityNo;
  }

  public void setCommunityNo(int communityNo) {
    this.communityNo = communityNo;
  }

}
